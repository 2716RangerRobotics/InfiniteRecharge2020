/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveTurnToAngle extends CommandBase {
	private double angle;
	private double turnSpeed;
	/**
	 * Turn angle
	 * @param angle Angle
	 * @param moveSpeed Speed
	 */
    public DriveTurnToAngle(double angle, double turnSpeed) {
    	this.angle = angle;
    	this.turnSpeed = turnSpeed;
    	addRequirements(RobotContainer.drive);
      
    }

    // Called just before this Command runs the first time
  public void initialize() {
    // RobotContainer.drive.zeroAngle();
    this.angle =  this.angle + RobotContainer.drive.getAngle();
  }

    // Called repeatedly when this Command is scheduled to run
  public void execute() {
    RobotContainer.drive.turnToAngle(this.angle, turnSpeed);
  }

    // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return (Math.abs(RobotContainer.drive.getAngle() - this.angle) <= 1);
  }

    // Called once after isFinished returns true
  public void end(boolean interupted) {
    RobotContainer.drive.arcadeDrive(0, 0, true);
  }
}