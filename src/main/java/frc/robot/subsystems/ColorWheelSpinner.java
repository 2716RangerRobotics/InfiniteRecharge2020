/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
//import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Servo;

public class ColorWheelSpinner extends SubsystemBase {
  // VictorSPX wheelMotor;
  // VictorSPX liftMotor;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private ColorSensorV3 colorSensor;
  Servo yourActuator;
  DigitalInput bottomBaseLimit;
  DigitalInput topBaseLimit;
  

  String colorString = "U";
  /**
   * A Rev Color Match object is used to register and detect known colors. This can 
   * be calibrated ahead of time or during operation.
   * 
   * This object uses a simple euclidian distance to estimate the closest match
   * with given confidence range.
   */
  private final ColorMatch colorMatcher = new ColorMatch();
  /**
   * Note: Any example colors should be calibrated as the user needs, these
   * are here as a basic example.
   */
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  static final double SPIN_COLOR_WHEEL_POSITION = 25;

  /**
   * Creates a new ColorWheelSpinner.
   */
  public ColorWheelSpinner() {
    colorSensor = new ColorSensorV3(i2cPort);
    colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kGreenTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorMatcher.addColorMatch(kYellowTarget); 
    
    yourActuator = new Servo(Constants.YOUR_ACTUATOR_CHANNEL);
    yourActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    yourActuator.setSpeed(-1.0);
    
    //wheelMotor = new VictorSPX(Constants.WHEEL_MOTOR);
    //liftMotor = new VictorSPX(Constants.LIFT_MOTOR);
    
    bottomBaseLimit = new DigitalInput(Constants.BOTTOM_BASE_LIMIT);
    topBaseLimit = new DigitalInput(Constants.TOP_BASE_LIMIT);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in
     * well lit conditions (the built in LED is a big help here!). The farther
     * an object is the more light from the surroundings will bleed into the 
     * measurements and make it difficult to accurately determine its color.
     */


    Color detectedColor = colorSensor.getColor();
    // System.out.println(detectedColor);

    /**
     * Run the color match algorithm on our detected color
     */
    
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "B";
      // System.out.println("Blue");
    } else if (match.color == kRedTarget) {
      colorString = "R";
      // System.out.println("Red");
    } else if (match.color == kGreenTarget) {
      colorString = "G";
      // System.out.println("Green");
    } else if (match.color == kYellowTarget) {
      colorString = "Y";
      // System.out.println("Yellow");
    } else {
      colorString = "U";
      // System.out.println("Unknown");
    }


    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    // SmartDashboard.putNumber("Red", detectedColor.red);
    // SmartDashboard.putNumber("Green", detectedColor.green);
    // SmartDashboard.putNumber("Blue", detectedColor.blue);
    // SmartDashboard.putNumber("Confidence", match.confidence);
    // SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putBoolean("Wheel Bot Lim", !bottomBaseLimit.get());
    SmartDashboard.putBoolean("Wheel Top Lim", !topBaseLimit.get());
  }
  
  public boolean isBottomBaseLimit() {
    if(!bottomBaseLimit.get()){
      return true;
    }
    else{
      return false;
    }
  }

  public boolean isTopBaseLimit() {
    if(!topBaseLimit.get()){
      return true;
    }
    else{
      return false;
    }
  }



  public boolean isRed(){
    return colorString.contentEquals("R");
  }

  public boolean isGreen(){
    return colorString.contentEquals("G");
  }

  public boolean isYellow(){
    return colorString.contentEquals("Y");
  }
  
  public String getColorString(){
    return colorString;
  }  
  
  // public void wheelSpin() {
    // wheelMotor.set(ControlMode.PercentOutput, Constants.WHEEL_MOTOR_SPEED);
  // }

  // public void stopWheelSpin() {
    // wheelMotor.set(ControlMode.PercentOutput, 0.0);
  // }
  
  public void liftUp() {
    if(isTopBaseLimit()){
      yourActuator.setSpeed(1.0); //to open
      // liftMotor.set(ControlMode.PercentOutput, 0.0);
    }else{
      yourActuator.setPosition(1.00);
      // liftMotor.set(ControlMode.PercentOutput, Constants.LIFT_MOTOR_SPEED);
    }
  }
  public void liftDown() {
    if(isBottomBaseLimit()){
      yourActuator.setSpeed(-1.0); //to close
      //liftMotor.set(ControlMode.PercentOutput, 0.0);
    }else{
      yourActuator.setPosition(0.00);
      // liftMotor.set(ControlMode.PercentOutput, -Constants.LIFT_MOTOR_SPEED);
    }
  }
  public void stopLift() {
    //liftMotor.set(ControlMode.PercentOutput, 0.0);
    // liftMotor.set(ControlMode.PercentOutput, SPIN_COLOR_WHEEL_POSITION);
    yourActuator.setSpeed(0.0); //to stop
  }
  //public void liftToPosition(){
    //liftMotor.set(ControlMode.PercentOutput, SPIN_COLOR_WHEEL_POSITION); //speed not position
  //}
  }