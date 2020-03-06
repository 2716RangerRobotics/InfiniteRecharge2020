/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
//import frc.robot.subsystems.ColorWheelSpinner;

public class ColorWheelSpinnerColorRotation extends CommandBase {
  public String targetColor;
  /**
   * Creates a new ColorWheelSpinnerColorRotation.
   */
  public ColorWheelSpinnerColorRotation() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.colorWheelSpinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0){
      if(gameData.contains("R")){
        targetColor = "B";
      }else if(gameData.contains("Y")){
        targetColor = "G";
      } else if(gameData.contains("B")){
        targetColor = "R";
      } else if(gameData.contains("G")){
        targetColor = "Y";
      }
      else{
        this.cancel();
      }
    }else {
      this.cancel();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //RobotContainer.colorWheelSpinner.wheelSpin();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //RobotContainer.colorWheelSpinner.stopWheelSpin();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return targetColor.contentEquals(RobotContainer.colorWheelSpinner.getColorString());
  }
}