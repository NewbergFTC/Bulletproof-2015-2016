package com.qualcomm.ftcrobotcontroller.opmodes;
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
            goForward(42 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT);
            sleep(500);
            goTurn(33 , RIGHT, TURN_POWER);
            // goForward(20, MOTOR_POWER);
            sleep(500);
            goForward(40 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT);
            sleep(500);
            goTurn(30 , RIGHT, TURN_POWER);
            sleep(500);
            goForward(30 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT); //accelerate into wall
            sleep(500);
            armRotate(110 , armForward, PowerForward );
            sleep(500);
            break;
        }
    }
}
