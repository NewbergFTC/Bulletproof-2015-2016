package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
public class OfficialAutonomousRed extends BulletProofMode {
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
            //JohnCena.start();

            if (goForward(42 , LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER ,RIGHT_BACK_POWER,3500 ))
                return;
            sleep(10000);
            goTurn(38, LEFT, LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            // goForward(20, MOTOR_POWER);
            sleep(500);
            if (goForward(40 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER,3500))
                return;
            sleep(500);
            goTurn(41, LEFT, LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            sleep(500);
            if (goForward(36 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER, LEFT_BACK_POWER , RIGHT_BACK_POWER,3500))
             return;//accelerate into wall
            sleep(500);
            //armRotate(100 , armForward, PowerForward );
            //sleep(500);
            break;

        }
    }
}
