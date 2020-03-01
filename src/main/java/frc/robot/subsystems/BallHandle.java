/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;


public class BallHandle extends SubsystemBase {
    VictorSPX upperMotor1;
    VictorSPX upperMotor2;

    
    static final double ROLLER_MOTOR_IN_SPEED = -0.8;
    static final double ROLLER_MOTOR_OUT_SPEED = 0.6;

    public enum UpperState {
      kOff1,
      kOff2,
      kIn,
      kOut,
      kSpin
    }
  

  /**
   * Creates a new BallIntakeHandle.
   */
  public BallHandle() {
    upperMotor1 = new VictorSPX(Constants.UPPER_MOTOR_1);
    upperMotor2 = new VictorSPX(Constants.UPPER_MOTOR_2);
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setUpperMotors(UpperState state) {
      switch (state) {
        case kOff1:
          upperMotor1.set(ControlMode.PercentOutput, 0.0);
          break;
        case kOff2:
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
          upperMotor1.set(ControlMode.PercentOutput, -Constants.UPPER_MOTOR_SPEED);
          upperMotor2.set(ControlMode.PercentOutput, Constants.UPPER_MOTOR_SPEED);
          break;
      }
  
  }
}
