/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveToWheelPosition extends CommandBase {
  /**
   * Creates a new DriveToWheelPosition.
   */
  public DriveToWheelPosition() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.drive);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // if(RobotContainer.colorWheelSpinner.isExtendLimit()) {
    //   RobotContainer.drive.setLeftRightMotorOutputs(0.0, 0.0);
    // }else{
    //   RobotContainer.drive.setLeftRightMotorOutputs(0.05, 0.05);
    // }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //RobotContainer.drive.setLeftRightMotorOutputs(.05, .05);
    if(RobotContainer.drive.isColorWheelLimit()) {
      RobotContainer.drive.setLeftRightMotorOutputs(0.0, 0.0);
    }else{
      RobotContainer.drive.setLeftRightMotorOutputs(0.05, 0.05);
    }
  }

  // Called once the command ens or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drive.setLeftRightMotorOutputs(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return false;
    return RobotContainer.drive.isColorWheelLimit();
  }
}
