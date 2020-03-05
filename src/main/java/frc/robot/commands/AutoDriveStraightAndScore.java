/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;;

public class AutoDriveStraightAndScore extends SequentialCommandGroup {
  /**
   * Creates a new AutoDriveAndScore.
   */
  public AutoDriveStraightAndScore() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new DriveBrakeOn(),
      new DriveStraightToDistance(133, .25),
      new BallTiltToScore(),
      new BallIntakeHandleOuttake().withTimeout(5.0)
    );
  }
}
//this works if we are left of the port
//11 pt. plan
