package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
/*
Autonomous program to move robot into loading zone
© Bullet Proof 6712
All rights reserved to the Country of the USA
 */

public class OfficialAutonomousBlue extends BulletOpMode {
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
            if (goForward(5 , LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER ,RIGHT_BACK_POWER,3500 ))
                return;
            sleep(9000);
            if (goForward(37 , LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER ,RIGHT_BACK_POWER,3500 ))
                return;
            sleep(500);
            goTurn(64, RIGHT, LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            sleep(500);
            if (goForward(42 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER,3500))
                return;
            sleep(500);
            goTurn(58, RIGHT, LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            sleep(500);
            if (goForward(45 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER, LEFT_BACK_POWER , RIGHT_BACK_POWER,3500))
                return;
            sleep(500);
            break;

        }
    }
}
