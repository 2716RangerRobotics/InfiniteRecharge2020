/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
  
  static final double TILT_OUT_SPEED = 0.45;
  static final double TILT_IN_SPEED = -0.45;
  static final double TILT_SCORE_POSITION = 474;
  static final double TILT_PASS_POSITION = 1200;

  private boolean prevRearLimit = false;
  private boolean prevFrontLimit = false;
  /**
   * Creates a new BallTilt.
   */
  public BallTilt() {
    tiltMotorRight = new TalonSRX(Constants.TILT_MOTOR_RIGHT);
    tiltMotorRight.configFactoryDefault();
    tiltMotorRight.configVoltageCompSaturation(12.5);
    tiltMotorRight.enableVoltageCompensation(true);
    tiltMotorRight.setInverted(true);
    tiltMotorRight.setNeutralMode(NeutralMode.Brake);

    tiltMotorLeft = new TalonSRX(Constants.TILT_MOTOR_LEFT);
    tiltMotorLeft.configFactoryDefault();
    tiltMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    tiltMotorLeft.configVoltageCompSaturation(12.5);
    tiltMotorLeft.enableVoltageCompensation(true);
    tiltMotorLeft.setInverted(false);
    tiltMotorLeft.setSensorPhase(false);
    tiltMotorLeft.setNeutralMode(NeutralMode.Brake);
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
    SmartDashboard.putBoolean("RFront Lim", !rightFrontLimit.get());
    SmartDashboard.putBoolean("LFront Lim", !leftFrontLimit.get());
    SmartDashboard.putBoolean("RRear Lim", !rightRearLimit.get());
    SmartDashboard.putBoolean("LRear Lim", !leftRearLimit.get());
    //SmartDashboard.putBoolean("intakeLimitIn", intakeLimitIn());
    //SmartDashboard.putBoolean("intakeLimitOut", intakeLimitOut());
    SmartDashboard.putNumber("TiltEnc",getLeftEncoder());
    // This method will be called once per scheduler run
    if(isRearLimit() && !prevRearLimit){
      tiltMotorLeft.setSelectedSensorPosition(0);
    }else if(isFrontLimit() && !prevFrontLimit){
      tiltMotorLeft.setSelectedSensorPosition(2260);
    }
    prevRearLimit = isRearLimit();
    prevFrontLimit = isFrontLimit();
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
  //Don't so it this way,  
  // public void intakeFrontPosition(){
  //   driveTiltMotors(ControlMode.Position, BALL_INTAKE_FRONT_POSITION);
  // }

  // public void intakeRearPosition(){
  //   driveTiltMotors(ControlMode.Position, BALL_INTAKE_REAR_POSITION);
  // }

  public void intakeTiltStop() {
    driveTiltMotors(ControlMode.PercentOutput, 0.0);
  }
  
  private void driveTiltMotors(ControlMode mode, double speed){
    if(isFrontLimit() && ( 
      (mode == ControlMode.PercentOutput && speed > 0.0)||
        (mode == ControlMode.Position && speed > getLeftEncoder()))){
      mode = ControlMode.PercentOutput;
      speed = 0.0;
    }else if (isRearLimit() && ( 
      (mode == ControlMode.PercentOutput && speed < 0.0)||
        (mode == ControlMode.Position && speed < getLeftEncoder()))){
      mode = ControlMode.PercentOutput;
      speed = 0.0;
    }
    tiltMotorLeft.set(mode, speed);
  }

  public double getLeftEncoder(){
    return tiltMotorLeft.getSelectedSensorPosition();
  }

  
}
