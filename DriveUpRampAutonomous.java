package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

public class DriveUpRamp extends OpMode{
    final static double MOTOR_POWER = 0.50;
    DcMotor lf;
    DcMotor lb;
    DcMotor rf;
    DcMotor rb;
    double left = 0.0;
    double right = 0.0;

    @Override
    public void init() {
        time = 0.0;
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
        lf.setDirection(DcMotor.Direction.REVERSE);
        lb.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void loop() {
        if (this.time <= 2.85) {
            // from 0 to 1 seconds, run the motors for five seconds.
            left = MOTOR_POWER;
            right = MOTOR_POWER;
        } else if (this.time > 2.85 && this.time <= 3.5) {
            // between 5 and 8.5 seconds, point turn right.
            left = MOTOR_POWER;
            right = -MOTOR_POWER;
        } else if (this.time > 3.5 && this.time <= 5) {
            // between 8 and 15 seconds, idle.
            left = 0.0;
            right = 0.0;
        } else if (this.time > 5 && this.time <= 71) {
            // between 15 and 20.75 seconds, point turn left.
            left = MOTOR_POWER;
            right = MOTOR_POWER;
        } else {
            // after 20.75 seconds, stop.
            left = 0.0;
            right = 0.0;
        }
        lb.setPower(left);
        lf.setPower(left);
        rf.setPower(right);
        rb.setPower(right);
    }
    @Override
    public void stop(){

    }
}
