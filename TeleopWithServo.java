package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.Servo;
//A base servo program to go off of for future use
public class TeleopWithServoArms extends OpMode {
    DcMotor leftfront;
    DcMotor leftback;
    DcMotor rightfront;
    DcMotor rightback;
    Servo leftArm;
    Servo rightArm;
    final double LEFT_ARM_OPEN = .5;
    final double LEFT_ARM_CLOSED = 0.0;
    final double RIGHT_ARM_OPEN = 0.5;
    final double RIGHT_ARM_CLOSED = 0.0;
    @Override
    public void init(){
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
        leftback.setDirection(DcMotor.Direction.REVERSE);
        leftfront.setDirection(DcMotor.Direction.REVERSE);
        leftArm = hardwareMap.servo.get("leftArm");
        rightArm = hardwareMap.servo.get("rightArm");
        rightArm.setPosition(RIGHT_ARM_CLOSED);
        leftArm.setPosition(LEFT_ARM_CLOSED);
    }
    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        leftfront.setPower(leftY);
        rightfront.setPower(rightY);
        leftback.setPower(leftY);
        rightback.setPower(rightY);
        if(gamepad2.x){
            leftArm.setPosition(LEFT_ARM_OPEN);
            //rightArm.setPosition(RIGHT_ARM_OPEN);
        }
        if(gamepad2.b){
            //leftArm.setPosition(LEFT_ARM_CLOSED);
            rightArm.setPosition(RIGHT_ARM_OPEN);
        }
        if(gamepad2.y){
            leftArm.setPosition(LEFT_ARM_CLOSED);
            rightArm.setPosition(RIGHT_ARM_CLOSED);
        }
    }
}
