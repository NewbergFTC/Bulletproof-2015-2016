package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.BulletOpMode;

public class OfficialAutonomousRed extends BulletOpMode {
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
            Rocky.start();
            if(goForward(5,LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER,1000))
                return;
            sleep(9000);
            if (goForward(37 , LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER ,RIGHT_BACK_POWER,3500 ))
                return;
            sleep(500);
            goTurn(64, LEFT, LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER);
            sleep(500);
            if (goForward(52 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER,3500))
                return;
            sleep(500);
            goTurn(60, LEFT, LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER);
            sleep(500);
            if (goForward(26 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER, LEFT_BACK_POWER , RIGHT_BACK_POWER,3500))
                return;//accelerate into wall
            sleep(500);
            motorArmRotate(.12,1);
            sleep(5500);
            StopArmMotors();
            sleep(500);
            break;

        }
    }
}
