package us.newberg.bullet;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
///import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by btaka on 11/6/2015.
 */
public class DriveUpRampBlue extends LinearOpMode {
    //final static double MOTOR_POWER = 0.5;
    final static double LEFT_FRONT_MOTOR_POWER = 0.45;
    final static double LEFT_BACK_MOTOR_POWER = 0.5;
    final static double RIGHT_FRONT_MOTOR_POWER = 0.5;
    final static double RIGHT_BACK_MOTOR_POWER = 0.5;
    final static double MOTOR_STOP = 0.0;
    DcMotor lf;
    DcMotor lb;
    DcMotor rf;
    DcMotor rb;
    //double leftFront = 0.0;
    //double rightFront = 0.0;
    //double rightBack = 0.0;
    //double leftBack = 0.0;
    Servo leftArm;
    Servo rightArm;
    final double LEFT_ARM_OPEN = .5;//need to revers one of the servos
    final double LEFT_ARM_CLOSED = 0.0;
    final double RIGHT_ARM_OPEN = 0.5;
    final double RIGHT_ARM_CLOSED = 0.0;
/*
        @Override
        public void init() {
            //time = 0.0;
            lf = hardwareMap.dcMotor.get("lf");
            lb = hardwareMap.dcMotor.get("lb");
            rf = hardwareMap.dcMotor.get("rf");
            rb = hardwareMap.dcMotor.get("rb");
            rf.setDirection(DcMotor.Direction.REVERSE);
            rb.setDirection(DcMotor.Direction.REVERSE);
            leftArm = hardwareMap.servo.get("leftArm");
            rightArm = hardwareMap.servo.get("rightArm");
            rightArm.setPosition(RIGHT_ARM_CLOSED);
            leftArm.setPosition(LEFT_ARM_CLOSED);
        }
        @Override
        public void loop() {
            lb.setPower(LEFT_BACK_MOTOR_POWER);
            lf.setPower(LEFT_FRONT_MOTOR_POWER);
            rf.setPower(RIGHT_FRONT_MOTOR_POWER);
            rb.setPower(RIGHT_BACK_MOTOR_POWER);
            sleep(2000);
            //try {
                //Thread.sleep(2000);
            //} catch (InterruptedException e) {
               // e.printStackTrace();
            //}
            lb.setPower(MOTOR_STOP);
            lf.setPower(MOTOR_STOP);
            rf.setPower(MOTOR_STOP);
            rb.setPower(MOTOR_STOP);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lb.setPower(-LEFT_BACK_MOTOR_POWER);
            lf.setPower(-LEFT_FRONT_MOTOR_POWER);
            rf.setPower(RIGHT_FRONT_MOTOR_POWER);
            rb.setPower(RIGHT_BACK_MOTOR_POWER);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lb.setPower(MOTOR_STOP);
            lf.setPower(MOTOR_STOP);
            rf.setPower(MOTOR_STOP);
            rb.setPower(MOTOR_STOP);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lb.setPower(LEFT_BACK_MOTOR_POWER);
            lf.setPower(LEFT_FRONT_MOTOR_POWER);
            rf.setPower(RIGHT_FRONT_MOTOR_POWER);
            rb.setPower(RIGHT_BACK_MOTOR_POWER);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lb.setPower(MOTOR_STOP);
            lf.setPower(MOTOR_STOP);
            rf.setPower(MOTOR_STOP);
            rb.setPower(MOTOR_STOP);
        }
    */

    @Override
    public void runOpMode() throws InterruptedException {
        //time = 0.0;
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
        rf.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.REVERSE);
        leftArm = hardwareMap.servo.get("leftArm");
        rightArm = hardwareMap.servo.get("rightArm");
        rightArm.setPosition(RIGHT_ARM_CLOSED);
        leftArm.setPosition(LEFT_ARM_CLOSED);

        waitForStart();



        lb.setPower(LEFT_BACK_MOTOR_POWER);
        lf.setPower(LEFT_FRONT_MOTOR_POWER);
        rf.setPower(RIGHT_FRONT_MOTOR_POWER);
        rb.setPower(RIGHT_BACK_MOTOR_POWER);
         if (Sleep(1900)){
             return;
         }
        lb.setPower(MOTOR_STOP);
        lf.setPower(MOTOR_STOP);
        rf.setPower(MOTOR_STOP);
        rb.setPower(MOTOR_STOP);
        if (Sleep(10000)){
            return;
        }
        //try {
        //Thread.sleep(2000);
        //} catch (InterruptedException e) {
        // e.printStackTrace();
        //}
        lb.setPower(MOTOR_STOP);
        lf.setPower(MOTOR_STOP);
        rf.setPower(MOTOR_STOP);
        rb.setPower(MOTOR_STOP);
        if (Sleep(750)){
            return;
        }
        lb.setPower(-LEFT_BACK_MOTOR_POWER);
        lf.setPower(-LEFT_FRONT_MOTOR_POWER);
        rf.setPower(RIGHT_FRONT_MOTOR_POWER);
        rb.setPower(RIGHT_BACK_MOTOR_POWER);
        if (Sleep(1100)){
            return;
        }
        lb.setPower(MOTOR_STOP);
        lf.setPower(MOTOR_STOP);
        rf.setPower(MOTOR_STOP);
        rb.setPower(MOTOR_STOP);
        if (Sleep(500)){
            return;
        }
        lb.setPower(LEFT_BACK_MOTOR_POWER);
        lf.setPower(LEFT_FRONT_MOTOR_POWER);
        rf.setPower(RIGHT_FRONT_MOTOR_POWER);
        rb.setPower(RIGHT_BACK_MOTOR_POWER);
        if (Sleep(2000)){
            return;
        }
        lb.setPower(1);
        lf.setPower(1);
        rf.setPower(1);
        rb.setPower(1);
        if (Sleep(4000)){
            return;
        }
        lb.setPower(MOTOR_STOP);
        lf.setPower(MOTOR_STOP);
        rf.setPower(MOTOR_STOP);
        rb.setPower(MOTOR_STOP);
    }


    Boolean Sleep(long millis) {
            long sleepTime = millis / 10;

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!opModeIsActive())
                    return true;
            }
        return false;
        }

}

