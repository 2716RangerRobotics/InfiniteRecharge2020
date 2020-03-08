/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;


public class DriveStraightToDistance extends CommandBase {
	private double distance;
	private double speed;
	private boolean isReverse;
  private double startAngle;
  private boolean isAngleTargeted;

	/**$
	 * Drive to distance
	 * @param distanceInInches Distance in "inches"
	 * @param speed Speed
	 */
  public DriveStraightToDistance(double distanceInInches, double speed) {
    this.distance = distanceInInches;
    this.speed = speed;
    addRequirements(RobotContainer.drive);
    isAngleTargeted = false;
    if (speed < 0) isReverse = true;
  }

  public DriveStraightToDistance(double distanceInInches, double speed, double angle) {
    this.distance = distanceInInches;
    this.speed = speed;
    addRequirements(RobotContainer.drive);
    this.startAngle = angle;
    isAngleTargeted = true;
    if (speed < 0) isReverse = true;
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    // RobotContainer.drive.zeroAngle();
    if(!isAngleTargeted){
      startAngle = RobotContainer.drive.getAngle();
    }
    RobotContainer.drive.resetLeftEncoder();
    RobotContainer.drive.resetRightEncoder();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    double turnValue = 0;
    // System.out.println(startAngle + ", " + drive.getAngle());
    double currentAngle = RobotContainer.drive.getAngle();
    if (currentAngle > startAngle + 2) {
      turnValue = 0.15;

    } else if (currentAngle > startAngle + 0.5) {
      turnValue = 0.1;
    } else if (currentAngle < startAngle - 0.5) {
      turnValue = -0.1;
    } else if (currentAngle < startAngle - 2) {
      turnValue = -0.15;
    } else {
      turnValue = 0;
    }
    double moveValue = speed;
    // if(Math.abs(distance - drive.getLeftPosition()) <= 37.5 || Math.abs(distance -
    // drive.getRightPosition()) <= 37.5){
    // moveValue = speed/3;
    // System.out.println("slowing");
    // }
    // System.out.println(currentAngle + ", " + turnValue);
    System.out.println(RobotContainer.drive.getLeftPosition() + ", " + RobotContainer.drive.getRightPosition());
    RobotContainer.drive.arcadeDrive(moveValue, turnValue, false);
  }

    // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    if (isReverse) {
      if (RobotContainer.drive.getLeftPosition() < distance || RobotContainer.drive.getRightPosition() < distance) {
        return true;
      }
    } else {
      if (RobotContainer.drive.getLeftPosition() > distance || RobotContainer.drive.getRightPosition() > distance) {
        return true;
      }
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interupted) {
     RobotContainer.drive.arcadeDrive(0, 0, false);
     RobotContainer.drive.resetLeftEncoder();
    RobotContainer.drive.resetRightEncoder();
  }


}