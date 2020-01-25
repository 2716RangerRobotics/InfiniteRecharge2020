/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;

import frc.robot.Constants;

public class Drive extends SubsystemBase {
  CANSparkMax leftMotorMaster;
  CANSparkMax leftMotorFollower;
  CANSparkMax rightMotorMaster;
  CANSparkMax rightMotorFollower;
  CANEncoder rightEncoder;
  CANEncoder leftEncoder;
  /**
   * Creates a new Drive.
   */
  public Drive() {
    leftMotorMaster = new CANSparkMax(Constants.LEFT_MOTOR_MASTER, MotorType.kBrushless);
    leftMotorFollower = new CANSparkMax(Constants.LEFT_MOTOR_FOLLOWER,MotorType.kBrushless);
    rightMotorMaster = new CANSparkMax(Constants.RIGHT_MOTOR_MASTER,MotorType.kBrushless);
    rightMotorFollower = new CANSparkMax(Constants.RIGHT_MOTOR_FOLLOWER,MotorType.kBrushless);

    rightMotorMaster.setInverted(true);
    rightMotorFollower.setInverted(true);

    leftMotorFollower.follow(leftMotorMaster);
    rightMotorFollower.follow(rightMotorMaster);

    rightEncoder = rightMotorMaster.getEncoder();
    leftEncoder = leftMotorMaster.getEncoder();
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
    if (leftMotorMaster == null || leftMotorFollower == null || 
      rightMotorMaster == null || rightMotorFollower == null) {
      throw new NullPointerException("Null motor provided");
    }

    if (leftMotorMaster != null && leftMotorFollower != null) {
      leftMotorMaster.set(limit(leftOutput));
    }

    if (rightMotorMaster != null && rightMotorFollower != null) {
      rightMotorMaster.set(limit(rightOutput));
    }
  }

  public double getRightPosition(){
    return rightEncoder.getPosition();
  }
  public double getLeftPosition(){
    return leftEncoder.getPosition();
  }
  public void resetLeftEncoder(){
    leftEncoder.setPosition(0.0);
  }
  public void resetRightEncoder(){
    rightEncoder.setPosition(0.0);
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
