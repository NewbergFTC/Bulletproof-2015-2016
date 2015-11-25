package us.newberg.bullet;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by btaka on 11/6/2015.
 */
public class OFFICIAL_TELEOP extends OpMode {
        DcMotor leftfront;
        DcMotor leftback;
        DcMotor rightfront;
        DcMotor rightback;
        float count = 0;
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Servo leftArm;
        Servo rightArm;
        final double LEFT_ARM_OPEN = .5;//need to revers one of the servos
        final double LEFT_ARM_CLOSED = 0.0;
        final double RIGHT_ARM_OPEN = 0.5;
        final double RIGHT_ARM_CLOSED = 0.0;
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //DcMotor ArmTilt;
        //DcMotor ArmLift;
        final double PowerForward = .5;
        final double PowerBack = -.5;
        final double ArmStop = 0;

        @Override
        public void init(){
            leftfront = hardwareMap.dcMotor.get("lf");
            leftback = hardwareMap.dcMotor.get("lb");
            rightfront = hardwareMap.dcMotor.get("rf");
            rightback = hardwareMap.dcMotor.get("rb");
            rightback.setDirection(DcMotor.Direction.REVERSE);
            rightfront.setDirection(DcMotor.Direction.REVERSE);
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            leftArm = hardwareMap.servo.get("leftArm");
            rightArm = hardwareMap.servo.get("rightArm");
            rightArm.setPosition(RIGHT_ARM_CLOSED);
            leftArm.setPosition(LEFT_ARM_CLOSED);
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //ArmTilt = hardwareMap.dcMotor.get("ArmTilt");
           // ArmLift = hardwareMap.dcMotor.get("ArmLift");
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
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
        if(gamepad2.right_trigger > .01){
            ArmTilt.setPower(PowerForward);
        }else{
            ArmTilt.setPower(ArmStop);
        }
        if(gamepad2.left_trigger > .01) {
            ArmLift.setPower(PowerForward);
        }else{
            ArmLift.setPower(ArmStop);
        }
        if(gamepad2.right_bumper) {
            ArmTilt.setPower(PowerBack);
        }else{
            ArmTilt.setPower(ArmStop);
        }
        if(gamepad2.left_bumper) {
            ArmLift.setPower(PowerBack);
        }else {
            ArmLift.setPower(ArmStop);
        }
        */
        }
    }

