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
      new DriveBrakeOn(),
      new DriveStraightToDistance(25, .25),
      new DriveTurnToAngle(-90, .25),
      new DriveStraightToDistance(68, .25),
      new DriveTurnToAngle(90, .25),
      new DriveStraightToDistance(108, .25),
      new BallTiltToScore(),
      new BallIntakeHandleOuttake().withTimeout(5.0)
    );
  }
}
//this works if we are left of the port
//11 pt. plan
