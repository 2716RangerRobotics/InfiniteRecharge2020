/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class HangingMechanismExtendToDistance extends CommandBase {
  public double distance;
  public double speed;
  public double rumble;
  /**
   * Creates a new HangingMechanismExtend.
   */
  public HangingMechanismExtendToDistance(double distance, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.hangingMechanism);
    this.distance = distance;
    this.speed = speed;
    this.rumble = rumble;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
       if(RobotContainer.hangingMechanism.getRightEncoder()>Constants.HANGING_EXTENDING_POSITION){
        RobotContainer.hangingMechanism.stopRightMotor();
      } else if(RobotContainer.hangingMechanism.getRightEncoder()>Constants.HANGING_EXTENDING_POSITION-3000){
        RobotContainer.hangingMechanism.setRightMotor(Constants.CLIMBING_EXTENDING_MOTOR_SPEED);
        // RobotContainer.setRumbleCoDriver(rumble);
    } else {
      RobotContainer.hangingMechanism.setRightMotor(0.60);
    }
    if(RobotContainer.hangingMechanism.getLeftEncoder()>Constants.HANGING_EXTENDING_POSITION){
      RobotContainer.hangingMechanism.stopLeftMotor();
    } else if(RobotContainer.hangingMechanism.getLeftEncoder()>Constants.HANGING_EXTENDING_POSITION-3000){
      RobotContainer.hangingMechanism.setLeftMotor(Constants.CLIMBING_EXTENDING_MOTOR_SPEED);
      // RobotContainer.setRumbleCoDriver(rumble);
  } else {
    RobotContainer.hangingMechanism.setLeftMotor(0.60);
  }
    //    if(RobotContainer.hangingMechanism.getRightEncoder()<Constants.HANGING_EXTENDING_POSITION){
    //   RobotContainer.hangingMechanism.setRightMotor(Constants.CLIMBING_EXTENDING_MOTOR_SPEED);
    // } else {
    //   RobotContainer.hangingMechanism.stopRightMotor();
    //   RobotContainer.setRumbleCoDriver(rumble);
    // }
    // if(RobotContainer.hangingMechanism.getLeftEncoder()<Constants.HANGING_EXTENDING_POSITION){
    //   RobotContainer.hangingMechanism.setLeftMotor(Constants.CLIMBING_EXTENDING_MOTOR_SPEED);
    // } else {
    //   RobotContainer.hangingMechanism.stopLeftMotor();
    //   RobotContainer.setRumbleCoDriver(rumble);
    // }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.hangingMechanism.stopRightMotor();
    RobotContainer.hangingMechanism.stopLeftMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (RobotContainer.hangingMechanism.getRightEncoder()>=distance) &&
     (RobotContainer.hangingMechanism.getLeftEncoder()>=distance);
  }
}
