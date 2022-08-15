package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public abstract class TacoFairSuper extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        inits();
        waitForStart();
        run();
    }

    public abstract void inits();
    public abstract void run();
}