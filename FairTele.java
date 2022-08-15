package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "FairTele")
public class FairTele extends FairInit {

    @Override
    public void run() {
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                shooterClass.shooter(gamepad1.right_trigger);
                telemetry.addLine("Telemetry");
                telemetry.update();
            }
        }
    }
}