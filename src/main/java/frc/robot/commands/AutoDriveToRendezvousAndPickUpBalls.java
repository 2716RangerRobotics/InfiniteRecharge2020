/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoDriveToRendezvousAndPickUpBalls extends SequentialCommandGroup {
  /**
   * Creates a new AutoDriveToRendezvousAndPickUpBalls.
   */
  public AutoDriveToRendezvousAndPickUpBalls() {
    // Use addRequirements() here to declare subsystem dependencies.
    super(
    new DriveBrakeOn(),
    new DriveStraightToDistance(125, .25),
    new BallTiltOut(),
    new BallIntakeIntake(),
    // new BallTiltIn(),
    new DriveTurnToAngle(90, .35),
    new ParallelRaceGroup(
      new DriveStraightToDistance(60, .25)
    ));
  }
}
//Drives to Rendezvous and picks up 2 more balls (assuming we had 3 pre-loaded at start of match).
//NEED TO TEST

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
