package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ShooterClass{

    public DcMotor shooter;
    public Servo indexer;

    public double fired = 0;
    double tacotimers;
    double shooterplace;
    double shooterdif;

    public ElapsedTime tacotimer = new ElapsedTime();
    public ElapsedTime fireTimer = new ElapsedTime();

    public enum shooterState {
        Idle,
        Spinup,
        Shoot
    }

    shooterState ShooterState = shooterState.Idle;

    public final LinearOpMode shooterClass;

    public ShooterClass(LinearOpMode shooterClass){
        shooter = shooterClass.hardwareMap.dcMotor.get("shooter");
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        indexer = shooterClass.hardwareMap.get(ServoImplEx.class, "indexer");

        this.shooterClass=shooterClass;
    }

    public void shooter(double trigger){
        switch (ShooterState) {
            case Idle:
                telemetry.addData("Shooter", "Idle");
                shooter.setPower(0);
                fired = 0;
                indexer.setPosition(.65);
                if (trigger > .3){
                    ShooterState = shooterState.Spinup;
                }
                break;

            case Spinup:
                telemetry.addData("Shooter", "Spinup");
                indexer.setPosition(.65);
                shooter.setPower(9.3*(1/(shooterdif)));
                if((tacotimer.seconds()-tacotimers)>.01) {
                    shooterdif = shooter.getCurrentPosition() - shooterplace;
                    shooterplace = shooter.getCurrentPosition();
                    tacotimers = tacotimer.seconds();
                }
                if(abs(shooterdif) < 10){
                    ShooterState = shooterState.Shoot;
                }
                break;

            case Shoot:
                telemetry.addData("Shooter", "Shoot");
                fireTimer.reset();
                indexer.setPosition(0);
                if (fireTimer.seconds() > .5){
                    if (fired < 3){
                        fired++;
                        ShooterState = shooterState.Spinup;
                    } else {
                        ShooterState = shooterState.Idle;
                    }
                }
        }
    }
}