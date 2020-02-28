/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoFeedShooter extends SequentialCommandGroup {
  /**
   * Creates a new AutoFeedShooter.
   */
  public AutoFeedShooter() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new BallTiltOut(),
      new WaitCommand(5),
      new BallIntakeOuttake().withTimeout(5),
      new BallTiltIn(),
      new DriveTurnToAngle(45, .5),
      new DriveStraightToDistance(-25, .5)
    );
  }
}
//works anywhere (it would be based off where team wants us)&turning 45 brings us to get balls
//passes balls to alliance members
