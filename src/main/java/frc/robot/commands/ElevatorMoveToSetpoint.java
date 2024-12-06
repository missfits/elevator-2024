// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;

import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ElevatorMoveToSetpoint extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Elevator m_elevator;
  private ElevatorFeedforward m_feedforward = new ElevatorFeedforward(
    ElevatorConstants.kS,
    ElevatorConstants.kG,
    ElevatorConstants.kV,
    ElevatorConstants.kA
  );
  private PIDController m_controller = new PIDController(
    ElevatorConstants.kP,
    ElevatorConstants.kI,
    ElevatorConstants.kD
  );

  private TrapezoidProfile.State m_goal;

  private TrapezoidProfile.Constraints m_constraints = new TrapezoidProfile.Constraints(ElevatorConstants.kMaxV, ElevatorConstants.kMaxA);
  private TrapezoidProfile.State m_profiledReference;
  private TrapezoidProfile m_profile = new TrapezoidProfile(m_constraints);


  /**
   * Creates a new ElevatorMoveToSetpoint commmand.
   *
   * @param elevator The subsystem used by this command.
   * @param goal The goal in (m, m/s)
   */
  public ElevatorMoveToSetpoint(Elevator elevator, TrapezoidProfile.State goal) {
    m_elevator = elevator;
    m_goal = goal;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_profiledReference = new TrapezoidProfile.State(m_elevator.getEncoderPosition(), m_elevator.getEncoderVelocity());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // recalculate the profiled reference point (the vel + pos that we want)
    m_profiledReference = m_profile.calculate(0.02, m_profiledReference, m_goal);
    
    // // calculate part of the power based on target velocity 
    double feedForwardPower = m_feedforward.calculate(m_profiledReference.velocity);

    // can also use max speed to calculate feedfoward :) 
    // double feedForwardPower = m_profiledReference.velocity / ElevatorConstants.MAX_SPEED * 12;
    
    // calculate part of the power based on target position + current position
    double PIDPower = m_controller.calculate(m_elevator.getEncoderPosition(), m_profiledReference.position);

    m_elevator.setVoltage(feedForwardPower + PIDPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_profile.isFinished(0.02);
  }
}
