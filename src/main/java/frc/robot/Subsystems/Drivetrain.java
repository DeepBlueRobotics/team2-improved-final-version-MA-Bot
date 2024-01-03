package frc.robot.Subsystems;

import org.carlmontrobotics.lib199.MotorConfig;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    private boolean isTank = false;
    private CANSparkMax leftMotor = MotorControllerFactory.createSparkMax(0, MotorConfig.NEO);
    private CANSparkMax rightMotor = MotorControllerFactory.createSparkMax(0, MotorConfig.NEO);
    private RelativeEncoder leftEncoder = leftMotor.getEncoder();
    public Drivetrain() {
        
    }
    @Override
    public void periodic() {

    }
    public void switchDriveModes() {
        isTank = isTank!;
    }
    public void drive(double leftJoyStick, double rightJoyStick) {
        if(isTank) {
            leftMotor.set(leftJoyStick);
            rightMotor.set(rightJoyStick);
        } else {
            double left = leftJoyStick + rightJoyStick;
            double right = leftJoyStick - rightJoyStick;

            leftMotor.set(left);
            rightMotor.set(right);
        }

    }
    public double leftPosition() {
        return leftEncoder.getPosition();
    }
}
