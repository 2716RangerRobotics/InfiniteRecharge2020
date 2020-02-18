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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallIntake extends SubsystemBase {
    TalonSRX tiltMotorRight;
    TalonSRX tiltMotorLeft;
    VictorSPX upperMotor1;
    VictorSPX upperMotor2;
    VictorSPX lowerMotor1;
    VictorSPX lowerMotor2;
    DigitalInput ballCountSensor;
    DigitalInput rightFrontLimit;
    DigitalInput leftFrontLimit;
    DigitalInput rightRearLimit;
    DigitalInput leftRearLimit;
    
    //DoubleSolenoid tiltPnuematic;

    boolean prevBallSensor;
    int ballCount = 0;

    static final double TILT_OUT_SPEED = 0.15;
    static final double TILT_IN_SPEED = -0.15;
    static final double ROLLER_MOTOR_IN_SPEED = -0.5;
    static final double ROLLER_MOTOR_OUT_SPEED = 0.5;

    public enum UpperState {
      kOff,
      kIn,
      kOut,
      kSpin
    }
    public enum LowerState {
      kOff,
      kIn,
      kOut,
      kSpin
    }
    /**
    * Creates a new BallIntake.
    */
    public BallIntake() {
        tiltMotorRight = new TalonSRX(Constants.TILT_MOTOR_RIGHT);
        tiltMotorRight.configFactoryDefault();
        tiltMotorRight.setInverted(true);

        tiltMotorLeft = new TalonSRX(Constants.TILT_MOTOR_LEFT);
        tiltMotorLeft.configFactoryDefault();
        tiltMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        tiltMotorLeft.setInverted(false);
        tiltMotorLeft.setSensorPhase(false);
        tiltMotorRight.follow(tiltMotorLeft);
        
        upperMotor1 = new VictorSPX(Constants.UPPER_MOTOR_1);
        upperMotor2 = new VictorSPX(Constants.UPPER_MOTOR_2);
        lowerMotor1 = new VictorSPX(Constants.LOWER_MOTOR_1);
        lowerMotor2 = new VictorSPX(Constants.LOWER_MOTOR_2);
        ballCountSensor = new DigitalInput(Constants.BALL_COUNT_SENSOR);
        rightFrontLimit = new DigitalInput(Constants.RIGHT_FRONT_LIMIT);
        leftFrontLimit = new DigitalInput(Constants.LEFT_FRONT_LIMIT);
        rightRearLimit = new DigitalInput(Constants.RIGHT_REAR_LIMIT);
        leftRearLimit = new DigitalInput(Constants.LEFT_REAR_LIMIT);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if( !ballCountSensor.get() && prevBallSensor){
      ballCount++;
    }
    SmartDashboard.putBoolean("Right Front Limit Switch", !rightFrontLimit.get());
    SmartDashboard.putBoolean("Left Front Limit Switch", !leftFrontLimit.get());
    SmartDashboard.putBoolean("Right Rear Limit Switch", !rightRearLimit.get());
    SmartDashboard.putBoolean("Left Rear Limit Switch", !leftRearLimit.get());
    SmartDashboard.putNumber("LeftEnc",getEncoder());
    SmartDashboard.putBoolean("Ball Count Sensor", !ballCountSensor.get());
    SmartDashboard.putNumber("Ball Count", ballCount);
    prevBallSensor = ballCountSensor.get();
    if(isRearLimit()){
      tiltMotorLeft.setSelectedSensorPosition(2200);
    }else if(isFrontLimit()){
      tiltMotorLeft.setSelectedSensorPosition(0);
    }
  }

  public void resetBallCount(){
    ballCount = 0;
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
  public void intakeTiltStop() {
    driveTiltMotors(ControlMode.PercentOutput, 0.0);
  }
  
  private void driveTiltMotors(ControlMode mode, double speed){
    if(isRearLimit() && speed > 0.0){
      speed = 0.0;
    }else if (isFrontLimit() && speed < 0.0){
      speed = 0.0;
    }
    tiltMotorLeft.set(mode, speed);
  }

  public double getEncoder(){
    return tiltMotorLeft.getSelectedSensorPosition();
  }

  public void setUpperMotors(UpperState state) {
    switch (state) {
      case kOff:
        upperMotor1.set(ControlMode.PercentOutput, 0.0);
        upperMotor2.set(ControlMode.PercentOutput, 0.0);
        break;
      case kIn:
        upperMotor1.set(ControlMode.PercentOutput, -Constants.UPPER_MOTOR_SPEED);
        upperMotor2.set(ControlMode.PercentOutput, Constants.UPPER_MOTOR_SPEED);
        break;
      case kOut:
        upperMotor1.set(ControlMode.PercentOutput, Constants.UPPER_MOTOR_SPEED);
        upperMotor2.set(ControlMode.PercentOutput, -Constants.UPPER_MOTOR_SPEED);
        break;
      case kSpin:
        upperMotor1.set(ControlMode.PercentOutput, Constants.UPPER_MOTOR_SPEED);
        upperMotor2.set(ControlMode.PercentOutput, Constants.UPPER_MOTOR_SPEED);
        break;
    }
  }

  public void setLowerMotors(LowerState state) {
    switch (state) {
      case kOff:
        lowerMotor1.set(ControlMode.PercentOutput, 0.0);
        lowerMotor2.set(ControlMode.PercentOutput, 0.0);
        break;
      case kIn:
        lowerMotor1.set(ControlMode.PercentOutput, -Constants.LOWER_MOTOR_SPEED);
        lowerMotor2.set(ControlMode.PercentOutput, Constants.LOWER_MOTOR_SPEED);
        break;
      case kOut:
        lowerMotor1.set(ControlMode.PercentOutput, Constants.LOWER_MOTOR_SPEED);
        lowerMotor2.set(ControlMode.PercentOutput, -Constants.LOWER_MOTOR_SPEED);
        break;
      case kSpin:
        lowerMotor1.set(ControlMode.PercentOutput, Constants.LOWER_MOTOR_SPEED);
        lowerMotor2.set(ControlMode.PercentOutput, Constants.LOWER_MOTOR_SPEED);
        break;
    }
  }

}
