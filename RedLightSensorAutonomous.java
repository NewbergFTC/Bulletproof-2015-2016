package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;

import com.qualcomm.ftcrobotcontroller.opmodes.FtcOpModeRegister;

/*
Autonomous utilizing the light sensor and the ultrasonic sensor
to deposit climbers in the bucket
© Bullet Proof 6712
All rights reserved to the Country of the USA
 */
public class RedLightSensorAutonomous extends BulletOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        super.Init();

        waitForStart();
        while (opModeIsActive()) {
            rocky.start();
            skirtServo.setPosition(SERVO_SKIRT_DOWN);

            if (lightSensor(leftLightSensor)) {
                StopDriveMotors();
                StopArmMotors();

                telemetry.addData("Light1", 1);
                return;
            }

            sleep(500);
            if (goForward(5, LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER, 5000)) {
                StopDriveMotors();
                StopArmMotors();

                telemetry.addData("forward", 1);
                return;
            }

            sleep(500);
            if (lightSensor(leftLightSensor)) {
                StopDriveMotors();
                StopArmMotors();

                telemetry.addData("light2", 1);
                return;
            }

            sleep(500);
            if (goTurn(110, LEFT, LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER)) {
                StopDriveMotors();
                StopArmMotors();

                telemetry.addData("turn", 1);
                return;
            }


            sleep(500);
            double ultrasonicvalue = ultrasonicSensor.getUltrasonicLevel();
            while (ultrasonicvalue > 22) {
                ultrasonicvalue = ultrasonicSensor.getUltrasonicLevel();
                motorDrive(LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER);

                if (!opModeIsActive()) {
                    telemetry.addData("ultra", 1);
                    return;
                }
            }

            StopDriveMotors();

            rocky.pause();

            sleep(200);
            doIt.start();

            sleep(500);
            motorArmRotate(.12, 1);

            sleep(500);

            doIt.stop();
            rocky.start();

            sleep(5000);
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
            // TODO: Replace this with a goForward once it can hadle negitive distances
            motorDrive(-LEFT_FRONT_POWER, -RIGHT_FRONT_POWER, -LEFT_BACK_POWER, -RIGHT_BACK_POWER);

            sleep(550);
            StopDriveMotors();

            break;
        }

        telemetry.addData("Done", 1);
    }
}
