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

public class DriveWithGamePad extends CommandBase {
  /**
   * Creates a new DriveWithGamePad.
   */
  public DriveWithGamePad() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double driveValueFast = RobotContainer.getDriverLeftStickY();
    double turnValueFast = RobotContainer.getDriverLeftStickX();
    double driveValueSlow = RobotContainer.getDriverRightStickY();
    double turnValueSlow = RobotContainer.getDriverRightStickX();
    // System.out.println("moveValue: " + driveValue + "  turnValue: "+ turnValue);
    if(Math.abs(driveValueSlow) > .2 || Math.abs(turnValueSlow) > .2){
      RobotContainer.drive.arcadeDrive(Constants.DRIVE_SLOW_SPEED*driveValueSlow,
        Constants.TURN_SLOW_SPEED*turnValueSlow, true);
    }else{
	    RobotContainer.drive.arcadeDrive(driveValueFast, turnValueFast, true);
    }
		
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
