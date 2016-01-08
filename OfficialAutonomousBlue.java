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
            goForward(42 , LEFT_FRONT_POWER ,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER,5000);
            sleep(10000);
            goTurn(33 , RIGHT, LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            // goForward(20, MOTOR_POWER);
            sleep(500);
            goForward(40 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER,5000);
            sleep(500);
            goTurn(30 , RIGHT, LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            sleep(500);
            goForward(36 , .9 , .9 , 1 , 1 , 5000); //accelerate into wall
            sleep(500);
            //armRotate(100 , armForward, PowerForward );
            //sleep(500);
            break;
        }
    }
}
