// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;

  }

  public static class ElevatorConstants {
    public static final int ELEVATOR_MOTOR_ID = 1;

    public static final int COUNTS_PER_REV = 42; // maybe right? from offseason-2024

    // 0.947 (sprocket radius) * 2 pi * 2 (carriage to chain movement) * m/in * 5.45 (gear ratio)
    public static final double METERS_PER_ROTATION = 1.64736586187; // tentative, should reevaluate

    // 95.4 in/s (from website) * m/in
    public static final double MAX_SPEED = 2.42316; // should be m/s; tentative, should reevaluate

    public static final double SPEED_LOWER_LIMIT = 0.0; // TEMP value. change later
    public static final double SPEED_UPPER_LIMIT = 0.0; // TEMP value. change later

    public static final double kS = 0;
    public static final double kG = 0;
    public static final double kV = 0;
    public static final double kA = 0;

    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final double kMaxV = 0;
    public static final double kMaxA = 0;


  }
}
