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

public class AutoTwoBallTake extends SequentialCommandGroup {
  /**
   * Creates a new AutoDriveAndScore.
   */
  public AutoTwoBallTake() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new DriveBrakeOn(),
      new DriveResetGyro(),
      new ParallelCommandGroup(
        new BallTiltOut(),
        new DriveStraightToDistance(40, .45, 0.0)
      ),
      new ParallelRaceGroup(
        new DriveStraightToDistance(77, .25, 0.0),
        new BallIntakeIntake(),
        new BallHandleIntake()
        // new WaitCommand(2.0)
      ),
      new BallIntakeHandleStop(),
      new ParallelRaceGroup(
        //new DriveTurnToAngle(-10, .35),
        new DriveStraightToDistance(15, .35),
        new BallIntakeIntake(),
        new BallHandleIntake()
      ),
      new BallIntakeHandleStop(),
      // new ParallelRaceGroup(
      //   new DriveStraightToDistance(20, .1),
      //   new BallIntakeIntake(),
      //   new BallHandleIntake()
      //   // new WaitCommand(2.0)
      // ),
      new DriveTurnToAngle(45, .35),
      new DriveResetEncoders(),
      new ParallelRaceGroup(
        new DriveStraightToDistance(21, .2),
        new BallIntakeIntake(),
        new BallHandleIntake()
      ),
      new BallIntakeHandleStop(),
      new DriveResetEncoders(),
      new WaitCommand(.25),
      //new DriveTurnToAngle(-45, .35),
      new ParallelRaceGroup(
        new DriveStraightToDistance(-72, -.35),
        new BallIntakeIntake(),
        new BallHandleIntake()
      )
      //new BallTiltIn()
    );
    
  }
}
//this works if we are left of the port
//11 pt. plan