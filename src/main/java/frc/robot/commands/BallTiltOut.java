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

public class BallTiltOut extends CommandBase {
  public double rumble;
  /**
   * Creates a new BallIntakeTiltOut.
   */
  public BallTiltOut() {
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
    //RobotContainer.ballTilt.intakeLimitOut();
    if(RobotContainer.ballTilt.isFrontLimit()== true){
      RobotContainer.ballTilt.intakeTiltStop();
      // RobotContainer.setRumbleCoDriver(rumble);
    //   // RobotContainer.setRumbleDriver(rumble);
    }else{
       RobotContainer.ballTilt.intakeTiltOut();
     }
    //if(RobotContainer.ballTilt.isFrontLimit()== true){
      //RobotContainer.ballTilt.intakeTiltStop();
    //   // RobotContainer.setRumbleCoDriver(rumble);
    //   // RobotContainer.setRumbleDriver(rumble);
    //}else{
      //RobotContainer.ballTilt.intakeRearPosition();
    }
    // if(RobotContainer.ballTilt.isFrontLimit()== true){
    //   RobotContainer.setRumbleCoDriver(1.0);
    //   RobotContainer.setRumbleDriver(1.0);
    // }else{
    //   RobotContainer.setRumbleCoDriver(0.0);
    //   RobotContainer.setRumbleDriver(0.0);
    // }
  //}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.ballTilt.intakeTiltStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.ballTilt.isFrontLimit();
  }
}
