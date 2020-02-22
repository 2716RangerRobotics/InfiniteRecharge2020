/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

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
      new DriveStraightToDistance(50, .5),
      new DriveTurnToAngle(90, .5),
      new DriveStraightToDistance(25, .5),
      new DriveTurnToAngle(90, .5),
      new DriveStraightToDistance(50, .5),
      new BallIntakeTiltToScore().withTimeout(3.0),
      new BallIntakeOuttake()
    );
  }
}
//11 pt. plan
