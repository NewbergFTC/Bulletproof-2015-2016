package com.qualcomm.ftcrobotcontroller.opmodes;

        import com.qualcomm.ftcrobotcontroller.opmodes.BulletOpMode;

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
            Rocky.start();
            if (goForward(5 , LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER ,RIGHT_BACK_POWER,3500 ))
                return;
            sleep(9000);
            if (goForward(37 , LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER ,RIGHT_BACK_POWER,3500 ))
                return;
            sleep(500);
            goTurn(64, RIGHT, LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            // goForward(20, MOTOR_POWER);
            sleep(500);
            if (goForward(42 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER,3500))
                return;
            sleep(500);
            goTurn(58, RIGHT, LEFT_FRONT_POWER,RIGHT_FRONT_POWER,LEFT_BACK_POWER,RIGHT_BACK_POWER);
            sleep(500);
            if (goForward(45 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER, LEFT_BACK_POWER , RIGHT_BACK_POWER,3500))
                return;//accelerate into wall
            sleep(500);
            //armRotate(100 , armForward, PowerForward );
            //sleep(500);
            break;

        }
    }
}
