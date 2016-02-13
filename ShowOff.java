package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;

/**
 * Created by Bullet Proof on 2/12/2016.
 */
public class ShowOff extends BulletOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        super.Init();

        waitForStart();
        while (opModeIsActive()) {
            if (goTurn(520, RIGHT,.8,.8,.8,.8)) {
                StopDriveMotors();
                StopArmMotors();

                return;
            }
            motorArmRotate(.12, 1);
            sleep(2000);
            StopArmMotors();

            roboto.start();
            sleep (8000);
            roboto.stop();
            break;

        }
    }
}

