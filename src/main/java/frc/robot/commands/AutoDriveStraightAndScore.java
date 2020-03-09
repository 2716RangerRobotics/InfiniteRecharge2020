/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;;

public class AutoDriveStraightAndScore extends SequentialCommandGroup {
  /**
   * Creates a new AutoDriveAndScore.
   */
  public AutoDriveStraightAndScore() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new DriveBrakeOn(),
      new DriveResetGyro(),
      new DriveStraightToDistance(89, .25),
      new ParallelRaceGroup(
        new DriveStraightToDistance(5, .25),
        new BallTiltToScore()
      ),
      new ParallelRaceGroup(
        new BallTiltToScore(),
        new BallIntakeHandleOuttake(),
        new WaitCommand(5.0)
        ),
      new BallIntakeHandleStop()
      
    );
  }
}
//this works if we are left of the port
//11 pt. plan
