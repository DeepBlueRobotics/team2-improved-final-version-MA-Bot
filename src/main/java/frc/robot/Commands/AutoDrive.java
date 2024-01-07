package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Subsystems.Drivetrain;

public class AutoDrive extends CommandBase{
    private Drivetrain drivetrain;
    public AutoDrive(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }
    @Override
    public void initialize() {
        drivetrain.setAuto(true);
        drivetrain.autoDrive();

    }
    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(0, 0);
        drivetrain.setAuto(false);
    }
    @Override
    public boolean isFinished() {
        return drivetrain.leftPosition() * (Constants.WHEEL_RADIUS*2) >= (Constants.Drivetrain.GOAL_FEET)/12;
    }
}
