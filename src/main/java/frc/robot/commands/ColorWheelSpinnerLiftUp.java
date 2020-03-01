/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ColorWheelSpinnerLiftUp extends CommandBase {
  public double rumble;
  /**
   * Creates a new ColorWheelSpinnerLift.
   */
  public ColorWheelSpinnerLiftUp() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.colorWheelSpinner);
    this.rumble = rumble;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //RobotContainer.colorWheelSpinner.liftUp();
    if(RobotContainer.colorWheelSpinner.isTopBaseLimit()== true){
      RobotContainer.colorWheelSpinner.stopLift();
      RobotContainer.setRumbleCoDriver(1.0);
      RobotContainer.setRumbleDriver(1.0);
      // RobotContainer.setRumbleTimeDriver(1.0);
      // RobotContainer.setRumbleTimeCoDriver(1.0);
    } else{
      RobotContainer.colorWheelSpinner.liftUp();
      RobotContainer.setRumbleCoDriver(0.0);
      RobotContainer.setRumbleDriver(0.0);
      // RobotContainer.setRumbleTimeDriver(0.0);
      // RobotContainer.setRumbleTimeCoDriver(0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.colorWheelSpinner.stopLift();
  }

  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.colorWheelSpinner.isTopBaseLimit();
  }
}
