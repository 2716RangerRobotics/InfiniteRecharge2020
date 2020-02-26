/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants;

public class HangingMechanism extends SubsystemBase {
 TalonSRX leftHangingMotor;
 TalonSRX rightHangingMotor;
//  Encoder leftHangingEncoder;
//  Encoder rightHangingEncoder;

static final double HANGING_EXTEND_POSITION = 100000.0;
static final double HANGING_RETRACT_POSITION = -100000.0;

  public HangingMechanism() {
    leftHangingMotor = new TalonSRX(Constants.CLIMBING_LEFT_MOTOR);
    leftHangingMotor.setInverted(true);
    rightHangingMotor = new TalonSRX(Constants.CLIMBING_RIGHT_MOTOR);
    leftHangingMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    rightHangingMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    leftHangingMotor.configFactoryDefault();
    rightHangingMotor.configFactoryDefault();
    resetLeftEncoder();
    resetRightEncoder();

    // tiltMotorLeft.configFactoryDefault();
    // tiltMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    // tiltMotorLeft.configVoltageCompSaturation(12.5);
    // tiltMotorLeft.enableVoltageCompensation(true);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("ClimbLeftEnc", getLeftEncoder());
    SmartDashboard.putNumber("ClimbRightEnc", getRightEncoder());
  }

public void hangingExtendDistance(){
  leftHangingMotor(ControlMode.PercentOutput, HANGING_EXTEND_POSITION);
  rightHangingMotor(ControlMode.PercentOutput, HANGING_EXTEND_POSITION);
}

  private void rightHangingMotor(ControlMode percentoutput, double hangingExtendPosition) {
    if(getRightEncoder() >= HANGING_EXTEND_POSITION){
      stopRightMotor();
    }
  }

  private void leftHangingMotor(ControlMode percentoutput, double hangingExtendPosition) {
    if(getLeftEncoder() >= HANGING_EXTEND_POSITION){
      stopLeftMotor();
    }
  }
public void hangingRetractDistance(){
    leftHangingMotorRetract(ControlMode.PercentOutput, HANGING_RETRACT_POSITION);
    rightHangingMotorRetract(ControlMode.PercentOutput, HANGING_RETRACT_POSITION);
  }
  private void rightHangingMotorRetract(ControlMode percentoutput, double hangingRetractPosition) {
    if(getRightEncoder() <= HANGING_RETRACT_POSITION){
      //stopRightMotor();
    }
  }
  private void leftHangingMotorRetract(ControlMode percentoutput, double hangingRetractPosition){
    if(getLeftEncoder() <= HANGING_RETRACT_POSITION){
      //stopLeftMotor();
  }
}

  public void resetLeftEncoder() {
    leftHangingMotor.setSelectedSensorPosition(-250);
  }
  public void resetRightEncoder() {
    rightHangingMotor.setSelectedSensorPosition(-250);
  }
  public double getLeftEncoder() {
    return leftHangingMotor.getSelectedSensorPosition();
  }
  public double getRightEncoder() {
    return rightHangingMotor.getSelectedSensorPosition();
  }
  public void setLeftMotor(double speed) {
    leftHangingMotor.set(ControlMode.PercentOutput, speed);
  }
  public void setRightMotor(double speed){
    rightHangingMotor.set(ControlMode.PercentOutput, speed);
  }
  public void stopLeftMotor() {
    leftHangingMotor.set(ControlMode.PercentOutput, 0.0);
  }
  public void stopRightMotor() {
    rightHangingMotor.set(ControlMode.PercentOutput, 0.0);
  }


public void setLeftMotor(ControlMode percentoutput, double d) {
}


public void setRightMotor(ControlMode percentoutput, double d) {
}


public boolean getLeftEncoder(ControlMode percentoutput, double d) {
	return false;
}
}
