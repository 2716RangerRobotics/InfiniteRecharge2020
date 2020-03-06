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

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoFeederStationPosition extends SequentialCommandGroup {
  /**
   * Creates a new AutoFeederStationPosition.
   */
  public AutoFeederStationPosition() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new DriveBrakeOn(),
      new DriveStraightToDistance(15, .25),
      new DriveTurnToAngle(-90.0, .25),
      new DriveStraightToDistance(30, .25),
      new DriveTurnToAngle(90.0, .25),
      new DriveStraightToDistance(5, .25),
      new ParallelRaceGroup(
        new BallTiltToScore().withTimeout(2.0),
        new BallIntakeIntake(),
        new WaitCommand(5.0)
      )
    );
  }
}
//works if we are left of the human player station
//picks up balls from human player station
