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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ColorWheelSpinnerRotationWheel extends CommandBase {
  public int numberOfGreens;
  public boolean prevIsGreen;
  /**
   * Creates a new ColorWheelSpinnerRotationWheel.
   */
  public ColorWheelSpinnerRotationWheel() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.colorWheelSpinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    numberOfGreens = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //RobotContainer.colorWheelSpinner.wheelSpin();

    // SmartDashboard.putNumber("Number of Greens", numberOfGreens);

    if(RobotContainer.colorWheelSpinner.isGreen() && !prevIsGreen){
      numberOfGreens++;
    } 
    
    prevIsGreen = RobotContainer.colorWheelSpinner.isGreen();
    // if (numberOfGreens == 7){
    //   RobotContainer.colorWheelSpinner.stopWheelSpin();
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //RobotContainer.colorWheelSpinner.stopWheelSpin();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return numberOfGreens >= 10;
  }
}
