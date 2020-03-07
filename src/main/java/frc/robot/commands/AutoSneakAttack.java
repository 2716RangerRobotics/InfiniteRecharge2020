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

public class AutoSneakAttack extends SequentialCommandGroup {
  /**
   * Creates a new AutoDriveAndScore.
   */
  public AutoSneakAttack() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new DriveBrakeOn(),
      //new BallTiltOut(),
      //new BallIntakeIntake(),
      //new BallHandleIntake(),
      new ParallelRaceGroup(
        new DriveStraightToDistance(257, .25, 0.0),
        new BallTiltOut(),
        new BallHandleIntake()
        ),
      new ParallelRaceGroup(
        new DriveStraightToDistance(100, -.35),
        new BallTiltToScore()
        )
    );
  }
}
//this works if we are left of the port
//11 pt. plan