package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;

/**
 * TODO(): A non-bs header
 *
 * Autonomous opmode that scores the climbers in the blue bucket, and lands us in the parking zone
 * Uses an ultrasonic and color sensor
 */
public class BlueLightSensorAutonomous extends BulletOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        super.Init();
        waitForStart();

        if (!opModeIsActive())
            return;

        // TODO(): Update this for the new skirt system
        SkirtServoR.setPosition(SERVO_SKIRT_DOWNR);
        SkirtServoL.setPosition(SERVO_SKIRT_DOWNL);

        Rocky.start();
        //Shia.start();

        lightsensor(rightLightSensor);
        sleep(500);

//        if (goForward(5, LEFT_FRONT_POWER * 1.2, RIGHT_FRONT_POWER, LEFT_BACK_POWER * 1.2, RIGHT_BACK_POWER, 5000))
//            return;
        motorDrive(LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER);
        sleep(750);
        StopDriveMotors();

        lightsensor(rightLightSensor);
        sleep(500);

        motorDrive(-LEFT_FRONT_POWER,-RIGHT_FRONT_POWER,-LEFT_BACK_POWER,-RIGHT_BACK_POWER);
        sleep(1000);
        StopDriveMotors();

        goTurn(160, RIGHT, LEFT_FRONT_POWER * 1.65, RIGHT_FRONT_POWER * 1.2, LEFT_BACK_POWER * 1.5, RIGHT_BACK_POWER * 1.35);
        sleep(500);

        while (ultrasonicSensor.getUltrasonicLevel() > 22) {
            motorDrive(LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER);
        }

        StopDriveMotors();
        Rocky.pause();
        sleep(200);
        DoIt.start();

        sleep(500);
        motorArmRotate(.12, 1);

        sleep(5500);
        DoIt.stop();
        Rocky.start();

        sleep(1000);
        StopArmMotors();
        sleep(2000);

        ArmLiftUp();
        sleep(1000);

        ArmLiftStop();
        sleep(500);

        motorArmRotate(.12, -1);
        sleep(500);

        StopArmMotors();
        sleep(500);

        // TODO(): Is this really needed?
        motorDrive(-LEFT_FRONT_POWER, -RIGHT_FRONT_POWER, -LEFT_BACK_POWER, -RIGHT_BACK_POWER);
        sleep(550);

        StopDriveMotors();
    }
}
