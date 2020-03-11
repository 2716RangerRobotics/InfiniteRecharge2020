/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BallHandleIntake;
import frc.robot.commands.BallHandleUpperStop;
import frc.robot.commands.BallIntakeIntake;
import frc.robot.commands.BallIntakeIntakeStop;
import frc.robot.commands.BallIntakeHandleOuttake;
import frc.robot.commands.BallIntakeHandleStop;
import frc.robot.commands.BallTiltIn;
import frc.robot.commands.BallTiltOut;
import frc.robot.commands.BallTiltStop;
import frc.robot.commands.BallTiltToScore;
import frc.robot.commands.BallTiltToPass;
import frc.robot.commands.CoDriverIntakeRumble;
//import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.ColorWheelSpinnerLiftUp;
import frc.robot.commands.ColorWheelSpinnerLiftDown;
import frc.robot.commands.ColorWheelSpinnerLiftStop;
import frc.robot.commands.ColorWheelSpinnerLiftToPosition;
import frc.robot.commands.ColorWheelSpinnerWheelStop;
import frc.robot.commands.DriveResetGyro;
import frc.robot.commands.DriveStop;
import frc.robot.commands.DriveStraightToDistance;
import frc.robot.commands.DriveToWheelPosition;
import frc.robot.commands.DriveTurnToAngle;
import frc.robot.commands.ColorWheelSpinnerRotationWheel;
import frc.robot.commands.ColorWheelSpinnerWheelStop;
import frc.robot.commands.ColorWheelSpinnerColorRotation;
import frc.robot.commands.DriveWithGamePad;
import frc.robot.commands.HangingMechanismRelease;
import frc.robot.commands.HangingMechanismResetEnc;
import frc.robot.commands.HangingMechanismResetServo;
import frc.robot.commands.HangingMechanismRetract;
import frc.robot.commands.HangingMechanismSetEnc;
import frc.robot.commands.HangingMechanismSetServo;
import frc.robot.commands.HangingMechanismStop;
import frc.robot.commands.LimelightLEDOff;
import frc.robot.commands.LimelightLEDOn;
import frc.robot.commands.HangingMechanismExtendToDistance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import edu.wpi.first.wpilibj2.command.FunctionalCommand;

import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallTilt;
import frc.robot.subsystems.ColorWheelSpinner;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.BallHandle;
// import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HangingMechanism;
import frc.robot.subsystems.Limelight;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are instantiated here...
  public static Drive drive;
  public static HangingMechanism hangingMechanism;
  public static BallIntake ballIntake;
  public static BallHandle ballHandle;
  public static BallTilt ballTilt;
  
  public static ColorWheelSpinner colorWheelSpinner;

  public static Limelight limelight;
  
  // The driver's controller
  static XboxController driverPad = new XboxController(0);
  static Button driverA1 = new JoystickButton(driverPad, 1);
  static Button driverB2 = new JoystickButton(driverPad, 2);
	static Button driverX3 = new JoystickButton(driverPad, 3);
	static Button driverY4 = new JoystickButton(driverPad, 4);
	static Button driverLB5 = new JoystickButton(driverPad, 5);
	static Button driverRB6 = new JoystickButton(driverPad, 6);
	static Button driverSEL7 = new JoystickButton(driverPad, 7);
	static Button driverSTART8 = new JoystickButton(driverPad, 8);
	static Button driverLS9 = new JoystickButton(driverPad, 9);
	static Button driverRS10 = new JoystickButton(driverPad, 10);
	static Button driverDLeft = new DPadButton(driverPad, DPadButton.Value.kDPadLeft);
	static Button driverDUp = new DPadButton(driverPad, DPadButton.Value.kDPadUp);
	static Button driverDDown = new DPadButton(driverPad, DPadButton.Value.kDPadDown);
	static Button driverDRight = new DPadButton(driverPad, DPadButton.Value.kDPadRight);
	static Button driverLTrigger = new TriggerButton(driverPad, Hand.kLeft);
	static Button driverRTrigger = new TriggerButton(driverPad, Hand.kRight);
	// static Button driverStartSelect = new DoubleButton(driverSEL7, driverSTART8);
  
  private static XboxController coDriverPad = new XboxController(1);
  static Button coDriverA1 = new JoystickButton(coDriverPad, 1);
	static Button coDriverB2 = new JoystickButton(coDriverPad, 2);
	static Button coDriverX3 = new JoystickButton(coDriverPad, 3);
	static Button coDriverY4 = new JoystickButton(coDriverPad, 4);
	static Button coDriverLB5 = new JoystickButton(coDriverPad, 5);
	static Button coDriverRB6 = new JoystickButton(coDriverPad, 6);
	static Button coDriverSEL7 = new JoystickButton(coDriverPad, 7);
	static Button coDriverSTART8 = new JoystickButton(coDriverPad, 8);
	static Button coDriverLS9 = new JoystickButton(coDriverPad, 9);
	static Button coDriverRS10 = new JoystickButton(coDriverPad, 10);
	static Button coDriverDLeft = new DPadButton(coDriverPad, DPadButton.Value.kDPadLeft);
	static Button coDriverDUp = new DPadButton(coDriverPad, DPadButton.Value.kDPadUp);
	static Button coDriverDDown = new DPadButton(coDriverPad, DPadButton.Value.kDPadDown);
	static Button coDriverDRight = new DPadButton(coDriverPad, DPadButton.Value.kDPadRight);
	static Button coDriverLTrigger = new TriggerButton(coDriverPad, Hand.kLeft);
	static Button coDriverRTrigger = new TriggerButton(coDriverPad, Hand.kRight);
	static Button coDriverLTriggerRTrigger = new DoubleButton(coDriverLTrigger, coDriverRTrigger);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // The robot's subsystems and commands are constructed here...
    drive = new Drive();
    hangingMechanism = new HangingMechanism();
    ballIntake = new BallIntake();
    ballHandle = new BallHandle();
    ballTilt = new BallTilt();
    colorWheelSpinner = new ColorWheelSpinner();
    CommandScheduler.getInstance().setDefaultCommand(drive, new DriveWithGamePad());
    
    // SmartDashboard.putData(drive);
    limelight = new Limelight();

    // Configure the button bindings - DO THIS LAST!!!
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    driverA1.whenPressed(new BallTiltOut().withTimeout(2.0));
    driverB2.whenPressed(new BallTiltIn().withTimeout(1.50));
    driverY4.whenPressed(new BallTiltToScore());
    driverX3.whenPressed(new BallTiltToPass());
    driverRB6.whenPressed(new BallIntakeHandleOuttake());
    driverRB6.whenReleased(new BallIntakeHandleStop());
    // driverRB6.whenReleased(new CoDriverIntakeRumble());
    driverLB5.whenPressed(new BallIntakeIntake());
    driverLB5.whenReleased(new BallIntakeIntakeStop());
    // driverSEL7.whileHeld(new DriveToWheelPosition());
    //driverRTrigger.whenPressed(new AutoDriveStraight(), false);
    // driverSEL7.whenPressed(new ColorWheelSpinnerLiftDown());
    // driverSEL7.whenReleased(new ColorWheelSpinnerLiftStop());
    // driverSTART8.whenPressed(new ColorWheelSpinnerLiftUp());
    // driverSTART8.whenReleased(new ColorWheelSpinnerLiftStop());

    driverDLeft.whenPressed(new DriveTurnToAngle(-25, .25));
    driverDRight.whenPressed(new DriveTurnToAngle(25, .25));
  
    coDriverA1.whenPressed(new BallHandleIntake());
    coDriverA1.whenReleased(new BallHandleUpperStop());
    coDriverB2.whenPressed(new BallTiltIn().withTimeout(1.5));
    coDriverLTrigger.whenPressed(new HangingMechanismResetEnc());
    coDriverRTrigger.whenPressed(new HangingMechanismSetEnc());
    // coDriverB2.whenPressed(new BallIntakeUpperState());
    coDriverX3.whenPressed(new HangingMechanismRetract());
    coDriverX3.whenReleased(new HangingMechanismStop());
    coDriverY4.whenPressed(new HangingMechanismExtendToDistance(100000, 0.3));
    coDriverY4.whenReleased(new HangingMechanismStop());
    coDriverRB6.whenPressed(new HangingMechanismSetServo());
    coDriverLB5.whenPressed(new HangingMechanismResetServo());
    // coDriverDLeft.whenPressed(new ColorWheelSpinnerRotationWheel());
    //coDriverDLeft.whenReleased(new ColorWheelSpinnerWheelStop()); //do we need this for this command?
    // coDriverDRight.whenPressed(new ColorWheelSpinnerColorRotation());
    // coDriverDUp.whenPressed(new ColorWheelSpinnerLiftUp());
    // coDriverDUp.whenReleased(new ColorWheelSpinnerLiftStop());
    // coDriverDDown.whenPressed(new ColorWheelSpinnerLiftDown());
    // coDriverDDown.whenReleased(new ColorWheelSpinnerLiftStop());
    coDriverSEL7.whenPressed(new DriveResetGyro());
    // coDriverLTrigger.whenPressed(new LimelightLEDOff());
    // coDriverRTrigger.whenPressed(new LimelightLEDOn());
    // coDriverSEL7.whenReleased(new ColorWheelSpinnerLiftStop()); //not sure if we need
    //coDriverLTriggerRTrigger.whenPressed(new HangingMechanismRelease());
  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    
    return null;
    //return AutoDriveStraight();
    //return AutoDriveAndScore();
    //return AutoDriveToPositionAndScore();
    //return AutoFeederStationPosition();
    //return AutoFeedShooter();
  }

  /**
   * 
   * @return value of the joystick [-1.0, 1.0]
   */
  public static double getDriverLeftStickY() {
		if (Math.abs(driverPad.getY(Hand.kLeft)) > 0.1) {
			return -1.0*driverPad.getY(Hand.kLeft);
		} else {
			return 0;
		}
  }
  public static double getDriverRightStickY() {
		if (Math.abs(driverPad.getY(Hand.kRight)) > 0.1) {
			return -1.0*driverPad.getY(Hand.kRight);
		} else {
			return 0;
		}
	}
  /**
   * 
   * @return value of the joystick [-1.0, 1.0]
   */
	public static double getDriverLeftStickX() {
		if (Math.abs(driverPad.getX(Hand.kLeft)) > 0.15) {
			return -1.0*driverPad.getX(Hand.kLeft);
		} else {
			return 0;
    }
  }

   /**
   * 
   * @return value of the joystick [-1.0, 1.0]
   */
  public static double getDriverRightStickX() {
		if (Math.abs(driverPad.getX(Hand.kRight)) > 0.15) {
			return -1.0*driverPad.getX(Hand.kRight);
		} else {
			return 0;
    }
  }

   /**
   * 
   * @return value of the joystick [-1.0, 1.0]
   */
  public static double getDriverStickLeftY() {
    if (Math.abs(driverPad.getY(Hand.kLeft)) > 0.15) {
      return -1.0*driverPad.getY(Hand.kLeft);
    } else {
      return 0;
    }
  }

   /**
   * 
   * @return value of the joystick [-1.0, 1.0]
   */
  public static double getDriverStickRightY() {
    if (Math.abs(driverPad.getY(Hand.kRight)) > 0.15) {
      return -1.0*driverPad.getY(Hand.kRight);
    } else {
      return 0;
    }
  }

  public static void setRumbleDriver(double rumble) {
    driverPad.setRumble(RumbleType.kLeftRumble, rumble);
    driverPad.setRumble(RumbleType.kRightRumble, rumble);
  }
  public static void setRumbleTimeDriver(double time){
    driverPad.setRumble(RumbleType.kLeftRumble, time);
    driverPad.setRumble(RumbleType.kRightRumble, time);
  }

  public static void setRumbleCoDriver(double rumble) {
    coDriverPad.setRumble(RumbleType.kLeftRumble, rumble);
    coDriverPad.setRumble(RumbleType.kRightRumble, rumble);
  }
  public static void setRumbleTimeCoDriver(double time){
    coDriverPad.setRumble(RumbleType.kLeftRumble, time);
    coDriverPad.setRumble(RumbleType.kRightRumble, time);
}
}
