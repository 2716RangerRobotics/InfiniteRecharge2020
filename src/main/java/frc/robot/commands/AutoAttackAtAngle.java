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

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoAttackAtAngle extends SequentialCommandGroup {
  /**
   * Creates a new AutoAttackAtAngle.
   */
  public AutoAttackAtAngle() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new DriveBrakeOn(),
      new DriveResetGyro(),
      new ParallelCommandGroup(
        new DriveStraightToDistance(50, .35),
        new BallTiltOut()
      ),
      new ParallelRaceGroup(
        new DriveStraightToDistance(120, .35),
        new BallIntakeIntake(),
        new BallHandleIntake()
      ),
      new DriveResetEncoders(),
      new ParallelCommandGroup(
      new DriveStraightToDistance(-50, -.35),
      new BallTiltIn()
      )
    );
  }
}
