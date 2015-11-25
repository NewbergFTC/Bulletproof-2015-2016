package us.newberg.bullet;
public class OfficialAutonomousBlue extends BulletProofOpMode {
    @Override
    public void initialize() {
    }
    @Override
    public void Update() {
    }
    @Override
    public void runOpMode() throws InterruptedException {
        super.init();
        waitForStart();
        while (opModeIsActive()) {
            goForward(42 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT);
            sleep(500);
            goTurn(30 , RIGHT, MOTOR_POWER);
            // goForward(20, MOTOR_POWER);
            sleep(500);
            goForward(40 , MOTOR_POWER_LEFT , MOTOR_POWER_RIGHT);
            sleep(500);
            goTurn(42 , RIGHT, MOTOR_POWER);
            sleep(500);
            goForward(36 , 1 , 1); //accelerate into wall
            sleep(500);
            break;
        }
    }
}
