package com.qualcomm.ftcrobotcontroller.opmodes;
public class OfficialAutonomousRed extends BulletOpMode{
    @Override public void initialize(){}
    @Override public void Update(){}
    @Override
    public void runOpMode() throws InterruptedException{
        super.Init();
        waitForStart();
        while (opModeIsActive()){
            goForward(42 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT);
            sleep(10000);
            goTurn(33 , LEFT, TURN_POWER);
            sleep(500);
            goForward(40 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT);
            sleep(500);
            goTurn(30 , LEFT, TURN_POWER);
            sleep(500);
            goForward(30 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT);
            sleep(500);
            //armRotate(80 , armForward, ARM_POWER_FORWARD );
            //sleep(500);
            break;
        }
    }
}

