// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {


  private final CANSparkMax m_elevatorMotor = new CANSparkMax(ElevatorConstants.ELEVATOR_MOTOR_ID, MotorType.kBrushless);
  private final SparkRelativeEncoder m_elevatorEncoder = (SparkRelativeEncoder) m_elevatorMotor
    .getEncoder(SparkRelativeEncoder.Type.kHallSensor, ElevatorConstants.COUNTS_PER_REV);

  

  /** Creates a new ExampleSubsystem. */
  public Elevator() {
    m_elevatorEncoder.setPositionConversionFactor(ElevatorConstants.METERS_PER_ROTATION); // want: meters (from rotations)
    m_elevatorEncoder.setVelocityConversionFactor(ElevatorConstants.METERS_PER_ROTATION / 60); // want: m/s (from rpm)
  }

  public double getEncoderPosition() {
    return m_elevatorEncoder.getPosition();
  }

  public double getEncoderVelocity() {
    return m_elevatorEncoder.getVelocity();
  }

  public void setVoltage(double speed) {
    m_elevatorMotor.setVoltage(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
