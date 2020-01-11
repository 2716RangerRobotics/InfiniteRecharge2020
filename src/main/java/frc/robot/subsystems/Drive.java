/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Drive extends SubsystemBase {
  PWMSparkMax leftMotors;
  PWMSparkMax rightMotors;
  /**
   * Creates a new Drive.
   */
  public Drive() {
    leftMotors = new PWMSparkMax(Constants.LEFT_MOTOR_CHANNEL);
    rightMotors = new PWMSparkMax(Constants.RIGHT_MOTOR_CHANNEL);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
    // local variables to hold the computed PWM values for the motors
    double leftMotorSpeed;
    double rightMotorSpeed;

    moveValue = limit(moveValue);
    rotateValue = limit(rotateValue);

    if (squaredInputs) {
      // square the inputs (while preserving the sign) to increase fine control
      // while permitting full power
      if (moveValue >= 0.0) {
        moveValue = moveValue * moveValue;
      } else {
        moveValue = -(moveValue * moveValue);
      }
      if (rotateValue >= 0.0) {
        rotateValue = rotateValue * rotateValue;
      } else {
        rotateValue = -(rotateValue * rotateValue);
      }
    }

    if (moveValue > 0.0) {
      if (rotateValue > 0.0) {
        leftMotorSpeed = moveValue - rotateValue;
        rightMotorSpeed = Math.max(moveValue, rotateValue);
      } else {
        leftMotorSpeed = Math.max(moveValue, -rotateValue);
        rightMotorSpeed = moveValue + rotateValue;
      }
    } else {
      if (rotateValue > 0.0) {
        leftMotorSpeed = -Math.max(-moveValue, rotateValue);
        rightMotorSpeed = moveValue + rotateValue;
      } else {
        leftMotorSpeed = moveValue - rotateValue;
        rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
      }
    }
    setLeftRightMotorOutputs(leftMotorSpeed, rightMotorSpeed);
  }

  public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
    if (leftMotors == null || rightMotors == null) {
      throw new NullPointerException("Null motor provided");
    }

    if (leftMotors != null) {
      leftMotors.set(limit(leftOutput));
    }

    if (rightMotors != null) {
      rightMotors.set(-limit(rightOutput));
    }
  }

  protected static double limit(double num) {
    if (num > 1.0) {
      return 1.0;
    }
    if (num < -1.0) {
      return -1.0;
    }
    return num;
  }

}
