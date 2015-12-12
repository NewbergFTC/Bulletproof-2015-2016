package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
public class OfficialAutonomousBlue extends BulletProofMode {
    @Override
    public void initialize() {
    }
    @Override
    public void Update() {
    }
    @Override
    public void runOpMode() throws InterruptedException {
        super.Init();
        waitForStart();
        while (opModeIsActive()) {
            JohnCena.start();
            if (goForward(42 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT , 3500))
                return;

            sleep(500);
            goTurn(33 , RIGHT, TURN_POWER);
            // goForward(20, MOTOR_POWER);
            sleep(500);
            if ( goForward(40 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT , 3500))
                return;

            sleep(500);
            goTurn(30 , RIGHT, TURN_POWER);
            sleep(500);
            if (goForward(36 , 1 , 1 , 3500)) //accelerate into wall
                return;

            sleep(500);
            //armRotate(100 , armForward, PowerForward );
            //sleep(500);
            break;
        }
    }
}


