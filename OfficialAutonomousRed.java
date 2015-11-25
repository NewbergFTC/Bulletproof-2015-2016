package us.newberg.bullet;
public class OfficialAutonomousRed extends BulletProofOpMode {
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
            goTurn(33 , LEFT, TURN_POWER);
            // goForward(20, MOTOR_POWER);
            sleep(500);
            goForward(40 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT);
            sleep(500);
            goTurn(30 , LEFT, TURN_POWER);
            sleep(500);
            goForward(36 , 1 , 1); //accelerate into wall
            sleep(500);
            break;
        }
    }
}

