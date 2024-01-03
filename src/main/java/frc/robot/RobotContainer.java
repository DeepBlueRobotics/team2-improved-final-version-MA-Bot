// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Commands.AutoDrive;

public class RobotContainer {
  private final Drivetrain drivetrain = new Drivetrain();
  private final XboxController controller = new XboxController(Constants.controllerPort);
  

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(controller, Button.kX.value).onTrue(new InstantCommand(drivetrain::switchDriveModes));
    
  }
  public Command getAutonomousCommand() {
    return new AutoDrive(drivetrain);
  }
}
