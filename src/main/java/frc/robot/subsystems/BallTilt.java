/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallTilt extends SubsystemBase {
  TalonSRX tiltMotorRight;
  TalonSRX tiltMotorLeft;
  DigitalInput rightFrontLimit;
  DigitalInput leftFrontLimit;
  DigitalInput rightRearLimit;
  DigitalInput leftRearLimit;
  
  static final double TILT_OUT_SPEED = 0.2;
  static final double TILT_IN_SPEED = -0.2;
  static final double TILT_IN_SPEED_STOP = 0.0;
  static final double TILT_OUT_SPEED_STOP = 0.0;
  static final double TILT_SCORE_POSITION = 474;
  static final double BALL_INTAKE_FRONT_POSITION = -400;
  static final double BALL_INTAKE_REAR_POSITION = 10000;
  static final double TILT_PASS_POSITION = 1200;
  /**
   * Creates a new BallTilt.
   */
  public BallTilt() {
    tiltMotorRight = new TalonSRX(Constants.TILT_MOTOR_RIGHT);
    tiltMotorRight.configFactoryDefault();
    tiltMotorRight.configVoltageCompSaturation(12.5);
    tiltMotorRight.enableVoltageCompensation(true);
    tiltMotorRight.setInverted(true);

    tiltMotorLeft = new TalonSRX(Constants.TILT_MOTOR_LEFT);
    tiltMotorLeft.configFactoryDefault();
    tiltMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    tiltMotorLeft.configVoltageCompSaturation(12.5);
    tiltMotorLeft.enableVoltageCompensation(true);
    tiltMotorLeft.setInverted(false);
    tiltMotorLeft.setSensorPhase(false);
    tiltMotorRight.follow(tiltMotorLeft);

    tiltMotorLeft.config_kP(0, 1.0);
    tiltMotorLeft.config_kI(0, 0.0);
    tiltMotorLeft.config_kD(0, 0.0);
    tiltMotorLeft.config_kF(0, 0.0);

    tiltMotorLeft.configPeakOutputForward(TILT_OUT_SPEED);
    tiltMotorLeft.configPeakOutputReverse(TILT_IN_SPEED);

    rightFrontLimit = new DigitalInput(Constants.RIGHT_FRONT_LIMIT);
    leftFrontLimit = new DigitalInput(Constants.LEFT_FRONT_LIMIT);
    rightRearLimit = new DigitalInput(Constants.RIGHT_REAR_LIMIT);
    leftRearLimit = new DigitalInput(Constants.LEFT_REAR_LIMIT);

    }
  

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Right Front Limit Switch", !rightFrontLimit.get());
    SmartDashboard.putBoolean("Left Front Limit Switch", !leftFrontLimit.get());
    SmartDashboard.putBoolean("Right Rear Limit Switch", !rightRearLimit.get());
    SmartDashboard.putBoolean("Left Rear Limit Switch", !leftRearLimit.get());
    //SmartDashboard.putBoolean("intakeLimitIn", intakeLimitIn());
    //SmartDashboard.putBoolean("intakeLimitOut", intakeLimitOut());
    SmartDashboard.putNumber("LeftEnc",getLeftEncoder());
    SmartDashboard.putNumber("RightEnc",getRightEncoder());
    // This method will be called once per scheduler run
    if(isRearLimit()){
      tiltMotorLeft.setSelectedSensorPosition(0);
    }else if(isFrontLimit()){
      tiltMotorLeft.setSelectedSensorPosition(2260);
    }
  }

  public boolean isFrontLimit() {
    if(!rightFrontLimit.get() || !leftFrontLimit.get()){
     return true; 
    }
    else{
      return false;
    } 
}
  public boolean isRearLimit() {
    if(!rightRearLimit.get() || !leftRearLimit.get()){
     return true; 
    }
    else{
      return false;
    } 
  }
  /**
   * Pnuematics used for tiltIn
   */
  public void intakeTiltIn() {
    driveTiltMotors(ControlMode.PercentOutput, TILT_IN_SPEED);
    
  }
  public void intakeTiltOut() {
    driveTiltMotors(ControlMode.PercentOutput, TILT_OUT_SPEED);
    
  }
  public void intakeTiltScore() {
    driveTiltMotors(ControlMode.Position, TILT_SCORE_POSITION);
  }
  public void outtakeTiltPass() {
    driveTiltMotors(ControlMode.Position, TILT_PASS_POSITION);
  }

  public void intakeFrontPosition(){
    driveTiltMotors(ControlMode.Position, BALL_INTAKE_FRONT_POSITION);
  }

  public void intakeRearPosition(){
    driveTiltMotors(ControlMode.Position, BALL_INTAKE_REAR_POSITION);
  }

  public void intakeTiltStop() {
    driveTiltMotors(ControlMode.PercentOutput, 0.0);
  }
  
  private void driveTiltMotors(ControlMode mode, double speed){
    if(isFrontLimit() && speed > 0.0){
      speed = 0.0;
    }else if (isRearLimit() && speed < 0.0){
      speed = 0.0;
    }
    tiltMotorLeft.set(mode, speed);
  }

  public double getLeftEncoder(){
    return tiltMotorLeft.getSelectedSensorPosition();
  }

  public double getRightEncoder(){
    return tiltMotorRight.getSelectedSensorPosition();
  }
  //public boolean intakeLimitIn(){
    //driveTiltMotors(ControlMode.PercentOutput, TILT_IN_SPEED_STOP);
//   if(isRearLimit()){
//     driveTiltMotors(ControlMode.PercentOutput, TILT_IN_SPEED_STOP);
//     //tiltMotorRight.set(ControlMode.PercentOutput, TILT_IN_SPEED_STOP);
//     return true;
//   }else{
//     tiltMotorLeft.set(ControlMode.PercentOutput, TILT_IN_SPEED);
//     //tiltMotorRight.set(ControlMode.PercentOutput, TILT_IN_SPEED);
//   }
     //return false;
//}
  //public boolean intakeLimitOut(){
    //driveTiltMotors(ControlMode.PercentOutput, TILT_OUT_SPEED_STOP);
  // if(isFrontLimit()){
  //   tiltMotorLeft.set(ControlMode.PercentOutput, TILT_OUT_SPEED_STOP);
  //   //tiltMotorRight.set(ControlMode.PercentOutput, TILT_OUT_SPEED_STOP);
  //   return true;
  // }else{
  //   tiltMotorLeft.set(ControlMode.PercentOutput, TILT_OUT_SPEED);
  //   //tiltMotorRight.set(ControlMode.PercentOutput, TILT_OUT_SPEED);
  // }
    //return false;
//}
}
