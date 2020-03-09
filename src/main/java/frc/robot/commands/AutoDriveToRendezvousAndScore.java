/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoDriveToRendezvousAndScore extends SequentialCommandGroup {
  /**
   * Creates a new AutoDriveToPositionAndScore.
   */
  public AutoDriveToRendezvousAndScore() {
    // Use addRequirements() here to declare subsystem dependencies.
      super(
        new DriveBrakeOn(),
        new ParallelCommandGroup(
          new DriveStraightToDistance(100, .25),
          new BallTiltOut()
        ),
        new ParallelRaceGroup(
          new DriveStraightToDistance(25, .25),
          new BallIntakeIntake(),
          new BallHandleIntake()
        ),
        new BallIntakeHandleStop(),
        // new BallTiltIn(),
        new DriveTurnToAngle(85, .35),
        new ParallelRaceGroup(
          new DriveStraightToDistance(45, .25),
          new BallIntakeIntake(),
          new BallHandleIntake()
        ),
        new BallHandleUpperStop(),
        new DriveStraightToDistance(50, -.25),
        new DriveTurnToAngle(95, .35),
        new DriveStraightToDistance(50, .25),
        new DriveTurnToAngle(90, .35),
        new DriveStraightToDistance(100, .25),
        new DriveTurnToAngle(-90, .35),

        new ParallelCommandGroup(
          new DriveStraightToDistance(75, .25),
          new BallTiltToScore()
        ),
        // new ParallelRaceGroup(
          // new BallTiltToScore(),
          new BallIntakeHandleOuttake(),
          new WaitCommand(5.0)
        // )
      );
  }
}
//Position is where two balls are located in the rendezvous area
// 15 pt. plan
//go to human player station after to pick up balls (in code or not?)