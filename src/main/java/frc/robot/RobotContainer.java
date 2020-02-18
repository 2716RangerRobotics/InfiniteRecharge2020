/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoDriveAndScore;
import frc.robot.commands.BallIntakeIntake;
import frc.robot.commands.BallIntakeIntakeStop;
import frc.robot.commands.BallIntakeOuttake;
import frc.robot.commands.BallIntakeOuttakeStop;
import frc.robot.commands.BallIntakeTiltIn;
import frc.robot.commands.BallIntakeTiltOut;
import frc.robot.commands.BallIntakeTiltStop;
//import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.ColorWheelSpinnerLiftUp;
import frc.robot.commands.ColorWheelSpinnerLiftDown;
import frc.robot.commands.ColorWheelSpinnerLiftStop;
//import frc.robot.commands.ColorWheelSpinnerTurnWheel;
//import frc.robot.commands.ColorWheelSpinnerTurnWheelStop;
import frc.robot.commands.ColorWheelSpinnerRotationWheel;
import frc.robot.commands.ColorWheelSpinnerRotationWheelStop;
import frc.robot.commands.ColorWheelSpinnerColorRotation;
import frc.robot.commands.ColorWheelSpinnerColorRotationStop;
import frc.robot.commands.DriveWithGamePad;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.ColorWheelSpinner;
import frc.robot.subsystems.Drive;
// import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HangingMechanism;
import frc.robot.subsystems.Limelight;
//import frc.robot.commands.HangingMechanismRelease;
//import frc.robot.commands.HangingMechanismReleaseStop;
//import frc.robot.commands.HangingMechanismExtend;
//import frc.robot.commands.HangingMechanismExtendStop;
//import frc.robot.commands.ColorWheelSpinnerTurnWheel;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import edu.wpi.first.wpilibj2.command.FunctionalCommand;

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
  //public DigitalInput gearSwitch;
  public static ColorWheelSpinner colorWheelSpinner;
  public static AutoDriveAndScore autoDriveAndScore;
  public static Limelight limelight;
  // The robot's commands are instantiated here...
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
	// static Button coDriverStartSelect = new DoubleButton(coDriverSEL7, coDriverSTART8);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // The robot's subsystems and commands are constructed here...
    drive = new Drive();
    hangingMechanism = new HangingMechanism();
    ballIntake = new BallIntake();
    //gearSwitch = new DigitalInput(Constants.GEAR_SWITCH_PORT);
    colorWheelSpinner = new ColorWheelSpinner();
    autoDriveAndScore = new AutoDriveAndScore();
    CommandScheduler.getInstance().setDefaultCommand(drive, new DriveWithGamePad());
    // m_autoCommand = new ExampleCommand(m_exampleSubsystem);
    SmartDashboard.putData(drive);
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
    driverA1.whenPressed(new BallIntakeTiltOut());
    driverA1.whenReleased(new BallIntakeTiltStop());
    driverB2.whenPressed(new BallIntakeTiltIn());
    driverB2.whenReleased(new BallIntakeTiltStop());
    driverRB6.whenPressed(new BallIntakeIntake());
    driverRB6.whenReleased(new BallIntakeIntakeStop());
    driverLB5.whenPressed(new BallIntakeOuttake());
    driverLB5.whenReleased(new BallIntakeOuttakeStop());
    driverLTrigger.whenPressed(new AutoDriveAndScore());
    driverSTART8.whenPressed(new ColorWheelSpinnerLiftUp());
    driverSEL7.whenPressed(new ColorWheelSpinnerLiftDown());
    // driverX3.whenPressed(new HangingMechanismRelease());
    // driverX3.whenReleased(new HangingMechanismReleaseStop());
    // driverY4.whenPressed(new HangingMechanismExtend());
    // driverY4.whenReleased(new HangingMechanismExtendStop());
    //coDriverA1.whenPressed(new ColorWheelSpinnerTurnWheel());
    //coDriverA1.whenReleased(new ColorWheelSpinnerTurnWheelStop());
    coDriverDLeft.whenReleased(new ColorWheelSpinnerRotationWheel());
    coDriverDLeft.whenReleased(new ColorWheelSpinnerRotationWheelStop());
    coDriverDRight.whenReleased(new ColorWheelSpinnerColorRotation());
    coDriverDRight.whenReleased(new ColorWheelSpinnerColorRotationStop());
    coDriverDUp.whenPressed(new ColorWheelSpinnerLiftUp());
    coDriverDUp.whenReleased(new ColorWheelSpinnerLiftStop());
    coDriverA1.whenPressed(new BallIntakeTiltOut());
    coDriverA1.whenReleased(new BallIntakeTiltStop());
    coDriverB2.whenPressed(new BallIntakeTiltIn());
    coDriverB2.whenReleased(new BallIntakeTiltStop());
    coDriverRB6.whenPressed(new BallIntakeIntake());
    coDriverRB6.whenReleased(new BallIntakeIntakeStop());
    coDriverLB5.whenPressed(new BallIntakeOuttake());
    coDriverLB5.whenReleased(new BallIntakeOuttakeStop());
  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
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

  /**
   * 
   * @return value of the joystick [-1.0, 1.0]
   */
	public static double getDriverLeftStickX() {
		if (Math.abs(driverPad.getX(Hand.kLeft)) > 0.1) {
			return -1.0*driverPad.getX(Hand.kLeft);
		} else {
			return 0;
    }
  }

  
  }
