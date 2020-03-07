/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

/**
 * Add your docs here.
 */
public class DriveResetGyro extends InstantCommand {
  /**
   * Add your docs here.
   */
  public DriveResetGyro() {
    addRequirements(RobotContainer.drive);
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    RobotContainer.drive.zeroAngle();
  }

  @Override
  public boolean runsWhenDisabled(){
    return true;
  }
}
