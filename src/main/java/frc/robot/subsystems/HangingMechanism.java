/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants;

public class HangingMechanism extends SubsystemBase {
 TalonSRX leftHangingMotor;
 TalonSRX rightHangingMotor;
//  Encoder leftHangingEncoder;
//  Encoder rightHangingEncoder;

  public void HangingMechanism() {
    leftHangingMotor = new TalonSRX(Constants.CLIMBING_LEFT_MOTOR);
    rightHangingMotor = new TalonSRX(Constants.CLIMBING_RIGHT_MOTOR);
    leftHangingMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    rightHangingMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void resetLeftEncoder() {
    leftHangingMotor.setSelectedSensorPosition(0);
  }
  public void resetRightEncoder() {
    rightHangingMotor.setSelectedSensorPosition(0);
  }
  public double getLeftEncoder() {
    return leftHangingMotor.getSelectedSensorPosition();
  }
  public double getRightEncoder() {
    return rightHangingMotor.getSelectedSensorPosition();
  }
  public void setLeftMotor() {
    leftHangingMotor.set(ControlMode.PercentOutput, Constants.CLIMBING_MOTOR_SPEED);
  }
  public void setRightMotor(){
    rightHangingMotor.set(ControlMode.PercentOutput, Constants.CLIMBING_MOTOR_SPEED);
  }
  public void stopLeftMotor() {
    leftHangingMotor.set(ControlMode.PercentOutput, 0.0);
  }
  public void stopRightMotor() {
    rightHangingMotor.set(ControlMode.PercentOutput, 0.0);
  }
}
