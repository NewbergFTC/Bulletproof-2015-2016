package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.Hardware;
/**
 * Created by Marcos Cazares on 11/2/2015.
 */
public class MarcosDrive extends OpMode {
    DcMotor leftfront;
    DcMotor leftback;
    DcMotor rightfront;
    DcMotor rightback;
    ElapsedTime time;

    static final double fowardtime = 0.010;
    static final double sleeping = 0.010;
    int count = 0;

    Servo leftArm;
    Servo rightArm;
    final double LEFT_ARM_OPEN = .5;//need to revers one of the servos
    final double LEFT_ARM_CLOSED = 0.0;
    final double RIGHT_ARM_OPEN = 0.5;
    final double RIGHT_ARM_CLOSED = 0.0;

    DcMotor ArmTilt;
    DcMotor ArmLift;
    final double PowerForward = .5;
    final double PowerBack = -.5;
    final double ArmStop = 0;

    enum State {forwardtime, sleeping, done}
    State state;

    @Override
    public void init() {
        leftfront = hardwareMap.dcMotor.get("lf");
        rightfront = hardwareMap.dcMotor.get("rf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightback = hardwareMap.dcMotor.get("rb");
        rightback.setDirection(DcMotor.Direction.REVERSE);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        double currentTime = time.time();
        switch (state) {
            case forwardtime:
                leftfront.setPower(leftY);
                rightfront.setPower(rightY);
                if (currentTime > fowardtime) {
                    state = State.sleeping;
                }
                time.reset();
                break;
        }
        switch (state) {
            case sleeping:
                leftfront.setPower(0);
                rightfront.setPower(0);
                if (currentTime > sleeping) {
                    state = State.forwardtime;
                }
                time.reset();
                break;
        }
                leftback.setPower(leftY);
                rightback.setPower(rightY);
        }
    
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

