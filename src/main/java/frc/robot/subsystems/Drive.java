/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Drive extends SubsystemBase {
  CANSparkMax leftMotorMaster;
  CANSparkMax leftMotorFollower;
  CANSparkMax rightMotorMaster;
  CANSparkMax rightMotorFollower;
  CANEncoder rightEncoder;
  CANEncoder leftEncoder;
  private AHRS imu;
  public static final double FeedForward = 0.18;
  public static final double kProportion = 0.025;
  DigitalInput colorWheelLimit;

  /**
   * Creates a new Drive.
   */
  public Drive() {
    imu = new AHRS(SPI.Port.kMXP);
    leftMotorMaster = new CANSparkMax(Constants.LEFT_MOTOR_MASTER, MotorType.kBrushless);
    leftMotorFollower = new CANSparkMax(Constants.LEFT_MOTOR_FOLLOWER, MotorType.kBrushless);
    rightMotorMaster = new CANSparkMax(Constants.RIGHT_MOTOR_MASTER, MotorType.kBrushless);
    rightMotorFollower = new CANSparkMax(Constants.RIGHT_MOTOR_FOLLOWER, MotorType.kBrushless);

    rightMotorMaster.setInverted(true);
    rightMotorFollower.setInverted(true);

    leftMotorFollower.follow(leftMotorMaster);
    rightMotorFollower.follow(rightMotorMaster);

    leftMotorMaster.enableVoltageCompensation(12.5);
    rightMotorMaster.enableVoltageCompensation(12.5);

    rightEncoder = rightMotorMaster.getEncoder();
    leftEncoder = leftMotorMaster.getEncoder();

    rightEncoder.setPositionConversionFactor(1.6866);
    leftEncoder.setPositionConversionFactor(1.6866);

    leftMotorMaster.setSmartCurrentLimit(Constants.STALL_LIMIT_DRIVE, Constants.FREE_LIMIT_DRIVE);
    leftMotorFollower.setSmartCurrentLimit(Constants.STALL_LIMIT_DRIVE, Constants.FREE_LIMIT_DRIVE);

    rightMotorMaster.setSmartCurrentLimit(Constants.STALL_LIMIT_DRIVE, Constants.FREE_LIMIT_DRIVE);
    rightMotorFollower.setSmartCurrentLimit(Constants.STALL_LIMIT_DRIVE, Constants.FREE_LIMIT_DRIVE);

    leftMotorMaster.setOpenLoopRampRate(Constants.RAMP_RATE);
    leftMotorFollower.setOpenLoopRampRate(Constants.RAMP_RATE);

    rightMotorMaster.setOpenLoopRampRate(Constants.RAMP_RATE);
    rightMotorFollower.setOpenLoopRampRate(Constants.RAMP_RATE);

    // leftMotorMaster.setOpenLoopRampRate(rate);
    colorWheelLimit = new DigitalInput(Constants.EXTEND_LIMIT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // if(DriverStation.getInstance().isAutonomous()||DriverStation.getInstance().isTest()){
      SmartDashboard.putNumber("Gyro", (int)imu.getYaw());
      SmartDashboard.putNumber("DriveEnc", getLeftPosition());
      SmartDashboard.putBoolean("Wheel Contact", !colorWheelLimit.get());

    // }
  }
  
  public void setBrakeMode(boolean brakeMode){
  
    if(brakeMode){
      leftMotorMaster.setIdleMode(IdleMode.kBrake);
      leftMotorFollower.setIdleMode(IdleMode.kBrake);
      rightMotorMaster.setIdleMode(IdleMode.kBrake);
      rightMotorFollower.setIdleMode(IdleMode.kBrake);
    }else{
      leftMotorMaster.setIdleMode(IdleMode.kCoast);
      leftMotorFollower.setIdleMode(IdleMode.kCoast);
      rightMotorMaster.setIdleMode(IdleMode.kCoast);
      rightMotorFollower.setIdleMode(IdleMode.kCoast);
    }
  }
  public boolean isColorWheelLimit() {
    if(!colorWheelLimit.get()){
      return true;
    }
    else{
      return false;
    }
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
    if (leftMotorMaster == null || leftMotorFollower == null || rightMotorMaster == null
        || rightMotorFollower == null) {
      throw new NullPointerException("Null motor provided");
    }

    if (leftMotorMaster != null && leftMotorFollower != null) {
      leftMotorMaster.set(limit(leftOutput));
    }

    if (rightMotorMaster != null && rightMotorFollower != null) {
      rightMotorMaster.set(limit(rightOutput));
    }
  }

  public void turnToAngle(double targetAngle, double turnSpeed) {
    double error = targetAngle - getAngle();
    if (error > 0) {
      // arcadeDrive(0, -1.0*(FeedForward + (kProportion * error)), false);
      setLeftRightMotorOutputs(1.0*(FeedForward + (kProportion * error)),
          -1.0*(FeedForward + (kProportion * error)) );
    } else {
      //arcadeDrive(0, -1.0*(-FeedForward + (kProportion * error)), false);
      setLeftRightMotorOutputs(1.0*(-FeedForward + (kProportion * error)),
        -1.0*(-FeedForward + (kProportion * error)) );
    }
  }

  public double getAngle() {
    // SmartDashboard.putNumber("gyro", imu.getYaw());
    return imu.getYaw();
  }

  public void zeroAngle() {
    imu.zeroYaw();
  }

  public double getRightPosition() {
    return rightEncoder.getPosition();
  }

  public double getLeftPosition() {
    return leftEncoder.getPosition();
  }

  public void resetLeftEncoder() {
    leftEncoder.setPosition(0.0);
  }

  public void resetRightEncoder() {
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
