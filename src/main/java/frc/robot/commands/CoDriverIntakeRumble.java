/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class CoDriverIntakeRumble extends CommandBase {
  /**
   * Creates a new CoDriverIntakeRumble.
   */
  public CoDriverIntakeRumble() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.ballIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.setRumbleCoDriver(.25);
    RobotContainer.setRumbleTimeCoDriver(1.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.setRumbleCoDriver(0.0);
    RobotContainer.setRumbleTimeCoDriver(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
