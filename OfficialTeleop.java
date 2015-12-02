package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
public class OfficialTeleOp extends OpMode {
    DcMotor leftfront;
    DcMotor leftback;
    DcMotor rightfront;
    DcMotor rightback;
    float count = 0;
    Servo leftArm;
    Servo rightArm;
    final double LEFT_ARM_OPEN = .5;
    final double LEFT_ARM_CLOSED = 0.0;
    final double RIGHT_ARM_OPEN = 0.5;
    final double RIGHT_ARM_CLOSED = 0.0;
    DcMotor ArmTiltLeft;
    DcMotor ArmTiltRight;
    DcMotor ArmLift;
    final double PowerForward = .2;
    final double PowerBack = -.2;
    final double ArmStop = 0;
    double armPower;
    double extendPower;
    @Override
    public void init(){
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
        rightback.setDirection(DcMotor.Direction.REVERSE);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        leftArm = hardwareMap.servo.get("leftArm");
        rightArm = hardwareMap.servo.get("rightArm");
        rightArm.setPosition(RIGHT_ARM_CLOSED);
        leftArm.setPosition(LEFT_ARM_CLOSED);
        ArmTiltRight = hardwareMap.dcMotor.get("ArmTiltRight");
        ArmTiltLeft = hardwareMap.dcMotor.get("ArmTiltLeft");
        ArmLift = hardwareMap.dcMotor.get("ArmLift");
    }
    @Override
    public void loop() {
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
        if(gamepad2.x){
            leftArm.setPosition(LEFT_ARM_OPEN);
        }
        if(gamepad2.b){
            rightArm.setPosition(RIGHT_ARM_OPEN);
        }
        if(gamepad2.y){
            leftArm.setPosition(LEFT_ARM_CLOSED);
        }
        if(gamepad2.a){
            rightArm.setPosition(RIGHT_ARM_CLOSED);
        }
        armPower = (gamepad2.left_trigger >= 0.5)?  armPower = PowerForward : (gamepad2.right_trigger >= 0.5)? armPower = PowerBack : 0;
        ArmTiltLeft.setPower(armPower);
        ArmTiltRight.setPower(armPower);
        extendPower = (gamepad2.left_bumper)?  extendPower = PowerForward : (gamepad2.right_bumper)? extendPower = PowerBack : 0;
        ArmLift.setPower(extendPower);
    }
}
