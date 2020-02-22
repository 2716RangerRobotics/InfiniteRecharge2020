/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoDriveToPositionAndScore extends SequentialCommandGroup {
  /**
   * Creates a new AutoDriveToPositionAndScore.
   */
  public AutoDriveToPositionAndScore() {
    // Use addRequirements() here to declare subsystem dependencies.
      super(
        new BallIntakeIntake(),
        new DriveStraightToDistance(125, .5),
        new BallIntakeTiltOut(),
        new BallIntakeIntake(),
        new BallIntakeTiltIn(),
        new DriveTurnToAngle(180, .5),
        new DriveStraightToDistance(50, .5),
        new BallIntakeTiltToScore(),
        new BallIntakeOuttake()
      );
  }
}
//Position is where two balls are located in the rendezvous area
// 15 pt. plan
//go to human player station after to pick up balls (in code or not?)