/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallIntake;

public class CoDriverLower1Stop extends CommandBase {
  /**
   * Creates a new CoDriverLower1Stop.
   */
  public CoDriverLower1Stop() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.ballIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.ballIntake.setUpperMotors(BallIntake.UpperState.kOff1);
    RobotContainer.ballIntake.setUpperMotors(BallIntake.UpperState.kOff2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
