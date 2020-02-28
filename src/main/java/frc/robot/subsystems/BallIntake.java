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

    VictorSPX upperMotor1;
    VictorSPX upperMotor2;
    VictorSPX lowerMotor1;
    VictorSPX lowerMotor2;
    DigitalInput ballCountSensor;

    
    //DoubleSolenoid tiltPnuematic;

    boolean prevBallSensor;
    int ballCount = 0;

    static final double ROLLER_MOTOR_IN_SPEED = -0.25;
    static final double ROLLER_MOTOR_OUT_SPEED = 0.25;


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
        upperMotor1 = new VictorSPX(Constants.UPPER_MOTOR_1);
        upperMotor2 = new VictorSPX(Constants.UPPER_MOTOR_2);
        lowerMotor1 = new VictorSPX(Constants.LOWER_MOTOR_1);
        lowerMotor2 = new VictorSPX(Constants.LOWER_MOTOR_2);
        ballCountSensor = new DigitalInput(Constants.BALL_COUNT_SENSOR);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if( !ballCountSensor.get() && prevBallSensor){
      ballCount++;
    }
    
    SmartDashboard.putBoolean("Ball Count Sensor", !ballCountSensor.get());
    SmartDashboard.putNumber("Ball Count", ballCount);
    prevBallSensor = ballCountSensor.get();
  }

  public void resetBallCount(){
    ballCount = 0;
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
        upperMotor1.set(ControlMode.PercentOutput, -Constants.UPPER_MOTOR_SPEED);
        upperMotor2.set(ControlMode.PercentOutput, Constants.UPPER_MOTOR_SPEED);
        break;
      case kSpin:
        upperMotor1.set(ControlMode.PercentOutput, -Constants.UPPER_MOTOR_SPEED);
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
        lowerMotor2.set(ControlMode.PercentOutput, -Constants.LOWER_MOTOR_SPEED);
        break;
      case kOut:
        lowerMotor1.set(ControlMode.PercentOutput, Constants.LOWER_MOTOR_SPEED);
        lowerMotor2.set(ControlMode.PercentOutput, Constants.LOWER_MOTOR_SPEED);
        break;
      case kSpin:
        lowerMotor1.set(ControlMode.PercentOutput, Constants.LOWER_MOTOR_SPEED);
        lowerMotor2.set(ControlMode.PercentOutput, Constants.LOWER_MOTOR_SPEED);
        break;
    }
  }

}
