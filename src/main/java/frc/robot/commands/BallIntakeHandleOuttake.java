/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallHandle;

public class BallIntakeHandleOuttake extends CommandBase {

  /**
   * Creates a new BallIntakeOuttake.
   */
  public BallIntakeHandleOuttake() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.ballIntake,RobotContainer.ballHandle);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.ballIntake.setLowerMotors(BallIntake.LowerState.kOut);
    RobotContainer.ballHandle.setUpperMotors(BallHandle.UpperState.kOut);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.ballIntake.setLowerMotors(BallIntake.LowerState.kOff1);
    RobotContainer.ballIntake.setLowerMotors(BallIntake.LowerState.kOff2);
    RobotContainer.ballHandle.setUpperMotors(BallHandle.UpperState.kOff1);
    RobotContainer.ballHandle.setUpperMotors(BallHandle.UpperState.kOff2);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
