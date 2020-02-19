/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Button;

public class DoubleButton extends Button {
	private Button button1;
	private Button button2;

	/**
	 * Creates a button using a trigger on a controller
	 * 
	 * @param controller The controller to use
	 * @param hadn       The left/right side of the controller
	 */
	public DoubleButton(Button button1, Button button2) {
		this.button1 = button1;
		this.button2 = button2;
	}

	@Override
	public boolean get() {
		return button1.get() && button2.get();
	}
}
