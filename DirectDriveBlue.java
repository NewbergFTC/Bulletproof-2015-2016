package us.newberg.bullet;
public class OfficialAutonomousBlue extends BulletProofMode {
    @Override public void initialize(){}
    @Override public void Update(){}
    @Override
    public void runOpMode() throws InterruptedException{
        super.Init();
        waitForStart();
        while (opModeIsActive()){
           //PullBackArm();
            goForward(42 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER , LEFT_BACK_POWER , RIGHT_BACK_POWER , 5000);
            sleep(10000);
            goTurn(33 , RIGHT, LEFT_FRONT_POWER , RIGHT_FRONT_POWER , LEFT_BACK_POWER , RIGHT_BACK_POWER);
            sleep(500);
            goForward(40 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER , LEFT_BACK_POWER , RIGHT_BACK_POWER , 5000);
            sleep(500);
            goTurn(30 , RIGHT, LEFT_FRONT_POWER , RIGHT_FRONT_POWER , LEFT_BACK_POWER , RIGHT_BACK_POWER);
            sleep(500);
            goForward(30 , LEFT_FRONT_POWER , RIGHT_FRONT_POWER , LEFT_BACK_POWER , RIGHT_BACK_POWER , 4500);
            sleep(500);
            //armRotate(80 , armForward, ARM_POWER_FORWARD );
            //sleep(500);
            break;
        }
    }
}
