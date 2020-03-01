/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class HangingMechanismResetServo extends InstantCommand {
  /**
   * Creates a new HangingMechanismResetServo.
   */
  public HangingMechanismResetServo() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.hangingMechanism); 
  }

  //Called when the command is initially scheduled.
  @Override
  public void initialize() {
  RobotContainer.hangingMechanism.resetRightServo();
  RobotContainer.hangingMechanism.resetLeftServo();
  }
}
