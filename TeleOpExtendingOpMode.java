package us.newberg.bullet;
public class OfficialTeleOp extends BulletProofOpMode {
    @Override
    public void initialize() {
    }
    @Override
    public void Update() {
    }
    public void runOpode() throws InterruptedException {
        float leftY=-gamepad1.left_stick_y;
        float rightY= -gamepad1.right_stick_y;
        if (gamepad1.a) {
            count = 1;
        }
        if (gamepad1.b) {
            count = 0;
        }
        if(count > 0){
            leftY = leftY/ 2;
            rightY = rightY/ 2;
        }
        leftfront.setPower(leftY);
        rightfront.setPower(rightY);
        leftback.setPower(leftY);
        rightback.setPower(rightY);
        if(gamepad2.left_bumper){
            leftArm.setPosition(LEFT_ARM_OPEN);
        }
        if(gamepad2.right_bumper){
            rightArm.setPosition(RIGHT_ARM_OPEN);
        }
        if(gamepad2.left_trigger >= .9){
            leftArm.setPosition(LEFT_ARM_CLOSED);
        }
        if(gamepad2.right_trigger >= .9){
            rightArm.setPosition((RIGHT_ARM_CLOSED));
        }
    }
}
