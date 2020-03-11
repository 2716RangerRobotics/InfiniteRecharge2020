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

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoFakeTwoBallTake extends SequentialCommandGroup {
  /**
   * Creates a new AutoFakeTwoBallTake.
   */
  public AutoFakeTwoBallTake() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new DriveBrakeOn(),
      new DriveResetGyro(),
      new DriveResetEncoders(),
      new DriveStraightToDistance(-67, -.25),
      new DriveTurnToAngle(-90, .35),
      new DriveStraightToDistance(165, .3),
      new DriveResetGyro(),
      new DriveTurnToAngle(-90, .35),
      new DriveResetEncoders(),
      new ParallelRaceGroup(
        new DriveStraightToDistance(45, .35),
        new BallTiltToScore()
      ),
      new DriveResetEncoders(),
      new WaitCommand(.05),
      new ParallelCommandGroup(
        new DriveStraightToDistance(7, 25),
        new BallTiltToScore(),
        new BallIntakeHandleOuttake() //need help, doesnt run outtake
        )
    );
  }
}
