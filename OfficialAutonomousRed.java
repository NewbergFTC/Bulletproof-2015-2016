package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.BulletOpMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

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
            SkirtServoR.setPosition(SERVO_SKIRT_DOWNR);
            SkirtServoL.setPosition(SERVO_SKIRT_DOWNL);
            Rocky.start();
            //if(goForward(5,LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER,1000))
            //    return;
            sleep(9000);
            if (goForward(45 , LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER ,RIGHT_BACK_POWER,5000 ))
                return;
            sleep(500);
            goTurn(64, LEFT, LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER);
            sleep(500);
            if (goForward(52 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER,5000))
                return;
            sleep(500);
            goTurn(60, LEFT, LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER);
            sleep(500);
            double ultrasonicvalue = ultrasonicSensor.getUltrasonicLevel();
            while (ultrasonicvalue > 22){
                ultrasonicvalue = ultrasonicSensor.getUltrasonicLevel();
                motorDrive(LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            }
            StopDriveMotors();
            //if (goForward(28 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER, LEFT_BACK_POWER , RIGHT_BACK_POWER,5000));//accelerate into wall
            sleep(500);
            motorArmRotate(.12,1);
            sleep(5500);
            StopArmMotors();
            sleep(2000);
            ArmLiftUp();
            sleep(500);
            ArmLiftStop();
            break;


        }
    }
}
