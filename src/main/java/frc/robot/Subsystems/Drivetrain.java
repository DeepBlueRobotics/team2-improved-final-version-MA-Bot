package frc.robot.Subsystems;

import org.carlmontrobotics.lib199.MotorConfig;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Drivetrain extends SubsystemBase{
    private boolean isTank = false;
    private boolean isAuto = false;
    private CANSparkMax leftMotor = MotorControllerFactory.createSparkMax(Constants.Drivetrain.LEFT_MOTOR_PORT, MotorConfig.NEO);
    private CANSparkMax rightMotor = MotorControllerFactory.createSparkMax(Constants.Drivetrain.RIGHT_MOTOR_PORT, MotorConfig.NEO);
    private int speedTick = 1;
    private final XboxController controller;
    private double speedSlower = 0.5;
    private RelativeEncoder leftEncoder = leftMotor.getEncoder();
    public Drivetrain(XboxController controller) {
        this.controller = controller;
    }
    @Override
    public void periodic() {
        if(isAuto) {
            return;
        }
        drive(controller.getLeftY(), controller.getRightX());
    }

    public void switchDriveModes() {
        isTank = !isTank;
    }

    public void setAuto(boolean isAutonomous) {
        isAuto = isAutonomous;
    }
    public void switchSpeed() {
        speedTick+=1;
        if(speedTick == 4) {
            speedTick = 1;
        }
        switch(speedTick) {
            case 1:
                speedSlower = 0.5;
            case 2:
                speedSlower = 0.75;
            case 3: 
                speedSlower = 1;
        }
    }
    public void drive(double leftJoyStick, double rightJoyStick) {
        if(isTank) {
            leftMotor.set(leftJoyStick);
            rightMotor.set(controller.getRightY());
        } else {
            double left = leftJoyStick + rightJoyStick;
            double right = leftJoyStick - rightJoyStick;

            leftMotor.set(left);
            rightMotor.set(right);
        }
    }

    public void autoDrive() {
        leftMotor.set(0.2);
        rightMotor.set(0.2);
    }

    public double leftPosition() {
        return leftEncoder.getPosition();
    }
}
