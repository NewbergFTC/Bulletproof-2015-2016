package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
public class OfficialTeleOp extends OpMode{
    DcMotor leftfront;
    DcMotor leftback;
    DcMotor rightfront;
    DcMotor rightback;
    DcMotor ArmTiltLeft;
    DcMotor ArmTiltRight;
    DcMotor ArmLift;
    float PowerForward = (float) .1;
    float PowerBack = (float) -.1;
    final float LiftPowerForward = (float) .5;
    final float LiftPowerBack = -1;
    //final double ArmStop = 0;
    //Servo leftArm;
    //Servo rightArm;
    //final double LEFT_ARM_OPEN = .5;
    //final double LEFT_ARM_CLOSED = 0.0;
    //final double RIGHT_ARM_OPEN = 0.5;
    //final double RIGHT_ARM_CLOSED = 0.0;
    //float count = 0;
    @Override
    public void init(){
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
        rightback.setDirection(DcMotor.Direction.REVERSE);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        ArmTiltRight = hardwareMap.dcMotor.get("ArmTiltRight");
        ArmTiltLeft = hardwareMap.dcMotor.get("ArmTiltLeft");
        ArmLift = hardwareMap.dcMotor.get("ArmLift");
        ArmTiltLeft.setDirection(DcMotor.Direction.REVERSE);
        //leftArm = hardwareMap.servo.get("leftArm");
        //rightArm = hardwareMap.servo.get("rightArm");
        //rightArm.setPosition(RIGHT_ARM_CLOSED);
        //leftArm.setPosition(LEFT_ARM_CLOSED);
    }
    @Override
    public void loop(){
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        leftfront.setPower(leftY);
        rightfront.setPower(rightY);
        leftback.setPower(leftY);
        rightback.setPower(rightY);
        PowerForward = (gamepad2.a)? 1 : (float) 0.1;
        PowerBack = (gamepad2.a)? -1 : (float) -0.1;
        float armPower = (gamepad2.left_trigger >= 0.05) ? PowerForward : (gamepad2.right_trigger >= 0.05) ? PowerBack : 0;
        ArmTiltLeft.setPower(armPower);
        ArmTiltRight.setPower(armPower);
        float extendPower = (gamepad2.left_bumper) ? LiftPowerForward : (gamepad2.right_bumper) ? LiftPowerBack : 0;
        ArmLift.setPower(extendPower);
        /*if(gamepad2.x){
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
        */
        /*if (gamepad1.a) {
            count = 1;
        }
        if (gamepad1.b) {
            count = 0;
        }
        if(count > 0){
            leftY = leftY/ 2;
            rightY = rightY/ 2;
        }*/
    }
}

