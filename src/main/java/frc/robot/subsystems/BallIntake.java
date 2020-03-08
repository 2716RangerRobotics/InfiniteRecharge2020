/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallIntake extends SubsystemBase {

    VictorSPX lowerMotor1;
    VictorSPX lowerMotor2;
    DigitalInput ballCountSensor;

    
    //DoubleSolenoid tiltPnuematic;

    boolean prevBallSensor;
    int ballCount = 0;

    static final double ROLLER_MOTOR_IN_SPEED = -0.95;
    static final double ROLLER_MOTOR_OUT_SPEED = 0.95;

    public enum LowerState {
      kOff1,
      kOff2,
      kIn,
      kOut,
      kSpin
    }
    /**
    * Creates a new BallIntake.
    */
    public BallIntake() {
       lowerMotor1 = new VictorSPX(Constants.LOWER_MOTOR_1);
        lowerMotor2 = new VictorSPX(Constants.LOWER_MOTOR_2);
        ballCountSensor = new DigitalInput(Constants.BALL_COUNT_SENSOR);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // if( !ballCountSensor.get() && prevBallSensor){
    //   ballCount++;
    // }
    
    // SmartDashboard.putBoolean("Ball Count Sensor", !ballCountSensor.get());
    // SmartDashboard.putNumber("Ball Count", ballCount);
    // prevBallSensor = ballCountSensor.get();
  }

  public void resetBallCount(){
    ballCount = 0;
  }

  public void setLowerMotors(LowerState state) {
    switch (state) {
      case kOff1:
        lowerMotor1.set(ControlMode.PercentOutput, 0.0);
        break;
      case kOff2:
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
