/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //SparkMax IDs
    public static final int LEFT_MOTOR_MASTER = 1;
    public static final int LEFT_MOTOR_FOLLOWER = 2;
    public static final int RIGHT_MOTOR_MASTER = 3;
    public static final int RIGHT_MOTOR_FOLLOWER = 4;
    //CTRE CAN ID's
    public static final int TILT_MOTOR_RIGHT = 7;
    public static final int TILT_MOTOR_LEFT = 8;
    public static final int UPPER_MOTOR_1 = 3;
    public static final int UPPER_MOTOR_2 = 10;
    public static final int LOWER_MOTOR_1 = 4;
    public static final int LOWER_MOTOR_2 = 9;
    public static final int CLIMBING_LEFT_MOTOR = 2;
    public static final int CLIMBING_RIGHT_MOTOR = 1;
    public static final int WHEEL_MOTOR = 6;
    public static final int LIFT_MOTOR = 5;

    //PWM Channels
    public static final int YOUR_ACTUATOR_CHANNEL = 0;
    public static final int HANGING_MECH_LEFT_SERVO = 1;
    public static final int HANGING_MECH_RIGHT_SERVO = 2;

    //PCM Channels
    public static final int TILT_FORWARD_1 = 0;
	public static final int TILT_REVERSE_2 = 1;

    //motor speeds
    public static final double UPPER_MOTOR_SPEED = 0.6;
    public static final double LOWER_MOTOR_SPEED = 0.6;
    public static final double CLIMBING_MOTOR_SPEED = 0.3;
    public static final double CLIMBING_EXTENDING_MOTOR_SPEED = 0.3;
    public static final double CLIMBING_RETRACTING_MOTOR_SPEED = -0.4;
    public static final double WHEEL_MOTOR_SPEED = 0.35; //make a ramp
    public static final double LIFT_MOTOR_SPEED = 0.40;
    public static final int STALL_LIMIT_DRIVE = 40;
    public static final int FREE_LIMIT_DRIVE = 30;
    public static final int SLOW_STALL_LIMIT_DRIVE = 30;
    public static final int SLOW_FREE_LIMIT_DRIVE = 15;
    public static final double RAMP_RATE = .01;
    public static final double DRIVE_SLOW_SPEED = 0.5;
    public static final double TURN_SLOW_SPEED = 0.7;

    // //limit switch
    // public static final int GEAR_SWITCH_PORT = 0;
    // public static final int GEAR_SENSOR_DIO_ID = 1;
    public static final int BALL_COUNT_SENSOR = 2;
    public static final int RIGHT_FRONT_LIMIT = 3;
    public static final int LEFT_FRONT_LIMIT = 4;
    public static final int RIGHT_REAR_LIMIT = 5;
    public static final int LEFT_REAR_LIMIT = 6;
    public static final int BOTTOM_BASE_LIMIT = 7;
    public static final int TOP_BASE_LIMIT = 8;
    public static final int EXTEND_LIMIT = 9;

    //hanging
    public static final double HANGING_RETRACT_POSITION = 0.0;
    public static final double HANGING_EXTENDING_POSITION = 18600.0;
    public static final int HANGING_RIGHT_SERVO = 0;
    public static final int HANGING_LEFT_SERVO = 0;
//    public static final double HANGING_EXTENDING_POSITION = 100000.0;

    //ballintake
    
    //color wheel
    public static final double COLOR_WHEEL_MAX_POSITION = 50.0;
    public static final double COLOR_WHEEL_MIN_POSITION = 0.0;
}
