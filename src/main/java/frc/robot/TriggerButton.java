/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;

public class TriggerButton extends Button {
	private XboxController controller;
	private Hand hand;

	/**
	 * Creates a button using a trigger on a controller
	 * 
	 * @param controller The controller to use
	 * @param hadn       The left/right side of the controller
	 */
	public TriggerButton(XboxController controller, Hand hand) {
		this.controller = controller;
		this.hand = hand;
	}

	@Override
	public boolean get() {
		return controller.getTriggerAxis(hand) >= 0.5;
	}
}
