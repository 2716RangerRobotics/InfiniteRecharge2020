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

public class AutoDriveToPositionAndScore extends SequentialCommandGroup {
  /**
   * Creates a new AutoDriveToPositionAndScore.
   */
  public AutoDriveToPositionAndScore() {
    // Use addRequirements() here to declare subsystem dependencies.
      super(
        new DriveBrakeOn(),
        new DriveStraightToDistance(125, .25),
        new BallTiltOut(),
        new BallIntakeIntake(),
        new BallTiltIn(),
        new DriveStraightToDistance(50, -.25),
        new DriveTurnToAngle(180, .25),
        new ParallelRaceGroup(
          new DriveStraightToDistance(50, .25),
          new BallTiltToScore()
        ),
        new ParallelRaceGroup(
          new BallTiltToScore(),
          new BallIntakeHandleOuttake(),
          new WaitCommand(5.0)
        )
      );
  }
}
//Position is where two balls are located in the rendezvous area
// 15 pt. plan
//go to human player station after to pick up balls (in code or not?)