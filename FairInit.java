package org.firstinspires.ftc.teamcode;

public abstract class FairInit extends TacoFairSuper{
    public ShooterClass shooterClass;

    @Override
    public void inits() {
        shooterClass = new ShooterClass(this);

        while (!isStopRequested()&&!isStopRequested()&&!isStopRequested()){

        }
    }
}
