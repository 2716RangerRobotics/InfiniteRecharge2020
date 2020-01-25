/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallIntake extends SubsystemBase {
    VictorSP tiltMotorRight;
    VictorSP tiltMotorLeft;
    final double TILT_OUT_SPEED = 0.5;
    final double TILT_IN_SPEED = -0.5;
    VictorSP upperMotor1;
    VictorSP upperMotor2;
    VictorSP lowerMotor1;
    VictorSP lowerMotor2;
    final double ROLLER_MOTOR_IN_SPEED = -0.5;
    final double ROLLER_MOTOR_OUT_SPEED = 0.5;
    enum UpperState {
      kOff,
      kIn,
      kOut,
      kSpin
    }
    enum LowerState {
      kOff,
      kIn,
      kOut,
      kSpin
    }
    /**
    * Creates a new BallIntake.
    */
    public BallIntake() {
        tiltMotorRight = new VictorSP(Constants.TILT_MOTOR_RIGHT);
        tiltMotorLeft = new VictorSP(Constants.TILT_MOTOR_LEFT);
        upperMotor1 = new VictorSP(Constants.UPPER_MOTOR_1);
        upperMotor2 = new VictorSP(Constants.UPPER_MOTOR_2);
        lowerMotor1 = new VictorSP(Constants.LOWER_MOTOR_1);
        lowerMotor2 = new VictorSP(Constants.LOWER_MOTOR_2);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intakeTiltIn() {
    tiltMotorRight.set(TILT_IN_SPEED);
    tiltMotorLeft.set(TILT_IN_SPEED * -1.0);
  }
  public void intakeTiltOut() {
    tiltMotorLeft.set(TILT_OUT_SPEED * -1.0);
    tiltMotorRight.set(TILT_OUT_SPEED);
  }
  public void setUpperMotors(UpperState state) {
    switch (state) {
      case kOff:
        upperMotor1.set(0.0);
        upperMotor2.set(0.0);
        break;
      case kIn:
        upperMotor1.set(-Constants.UPPER_MOTOR_SPEED);
        upperMotor2.set(Constants.UPPER_MOTOR_SPEED);
        break;
      case kOut:
        upperMotor1.set(Constants.UPPER_MOTOR_SPEED);
        upperMotor2.set(-Constants.UPPER_MOTOR_SPEED);
        break;
      case kSpin:
        upperMotor1.set(Constants.UPPER_MOTOR_SPEED);
        upperMotor2.set(Constants.UPPER_MOTOR_SPEED);
        break;
    }
  }

  public void setLowerMotors(LowerState state) {
    switch (state) {
      case kOff:
        lowerMotor1.set(0.0);
        lowerMotor2.set(0.0);
        break;
      case kIn:
        lowerMotor1.set(-Constants.LOWER_MOTOR_SPEED);
        lowerMotor2.set(Constants.LOWER_MOTOR_SPEED);
        break;
      case kOut:
        lowerMotor1.set(Constants.LOWER_MOTOR_SPEED);
        lowerMotor2.set(-Constants.LOWER_MOTOR_SPEED);
        break;
      case kSpin:
        lowerMotor1.set(Constants.LOWER_MOTOR_SPEED);
        lowerMotor2.set(Constants.LOWER_MOTOR_SPEED);
        break;
    }
  }

}
