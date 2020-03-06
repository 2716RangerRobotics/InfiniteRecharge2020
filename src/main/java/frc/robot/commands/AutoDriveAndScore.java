/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoDriveAndScore extends SequentialCommandGroup {
  /**
   * Creates a new AutoDriveAndScore.
   */
  public AutoDriveAndScore() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new DriveBrakeOn(),
      new DriveResetGyro(),
      new DriveStraightToDistance(25, .25),
      new DriveTurnToAngle(-90, .35),
      new DriveStraightToDistance(68, .25, -90),
      new DriveTurnToAngle(90, .35),
      new ParallelRaceGroup(
        new DriveStraightToDistance(108, .25, 0.0),
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
