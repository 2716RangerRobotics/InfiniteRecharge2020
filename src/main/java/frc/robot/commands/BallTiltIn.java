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

public class BallTiltIn extends CommandBase {
  public double rumble;
  /**
   * Creates a new BallIntakeTiltIn.
   */
  public BallTiltIn() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.ballTilt);
    this.rumble = rumble;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.ballTilt.intakeTiltIn();
    if(RobotContainer.ballTilt.getLeftEncoder()>Constants.BALL_INTAKE_REAR_POSITION){
      RobotContainer.setRumbleCoDriver(rumble);
      RobotContainer.setRumbleDriver(rumble);
    }
    if(RobotContainer.ballTilt.getRightEncoder()>Constants.BALL_INTAKE_REAR_POSITION){
      RobotContainer.setRumbleCoDriver(rumble);
      RobotContainer.setRumbleDriver(rumble);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.ballTilt.intakeTiltStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.ballTilt.isRearLimit();
  }
}
