package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
/*
Autonomous program to move robot into loading zone
in a straight line
© Bullet Proof 6712
All rights reserved to the Country of the USA
 */
public class StraightAutonomous extends BulletOpMode {
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
            SkirtServoR.setPosition(SERVO_SKIRT_DOWNR);
            SkirtServoL.setPosition(SERVO_SKIRT_DOWNL);
            sleep(5000);
            double ultrasonicvalue = ultrasonicSensor.getUltrasonicLevel();
            while (ultrasonicvalue > 35){ // The Ultrasonic value is equal to 35 centimeters
                ultrasonicvalue = ultrasonicSensor.getUltrasonicLevel();
                motorDrive(LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            }
            StopDriveMotors();
            break;
        }
    }
}
