package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Util;

import java.lang.ref.Reference;

public abstract class BulletOpMode extends LinearOpMode {
    public DcMotor leftfront;
    public DcMotor leftback;
    public DcMotor rightfront;
    public DcMotor rightback;
    public Servo rightArm;
    public Servo leftArm;
    public DcMotorController leftController;
    public DcMotorController rightController;
    //Put motors for arms in as well
    final double LEFT_ARM_OPEN = .5;//need to reverse one of the servos
    final double LEFT_ARM_CLOSED = 0.0;
    final double RIGHT_ARM_OPEN = 0.5;
    final double RIGHT_ARM_CLOSED = 0.0;
    final double GEAR_ONE_TEETH = 16;
    final double GEAR_TWO_TEETH = 30;
    final double CLICKS_PER_REVOLUTION = 1120;
    final double WHEEL_CIRCUMFERENCE = 7 * Math.PI;
    final double MOTOR_POWER = .5;
    final double TURN_POWER = 0.5;
    final double GOAL_DIFFERENCE = 100;
    final double DISTANCE_FACTOR = 0.89;
    final double TURN_FACTOR = 2.22;
    final double RIGHT = 1;
    final double LEFT = -1;
    final double MOTOR_POWER_LEFT = 0.6;
    final double MOTOR_POWER_RIGHT = 0.35;
    float count  = 0;
    final double ArmGear1 = 72;
    final double ArmGear2 = 20;
    final double ArmGear3 = 72;
    final double ArmGear4 = 34;
    DcMotor ArmTiltLeft;
    DcMotor ArmTiltRight;
    DcMotor ArmLift;
    final int armForward = 1;
    final int armBack = -1;
    public DcMotorController ArmController;
    final double PowerForward = .2;
    final double PowerBack = -.2;
    final double ArmStop = 0;
    protected final void Init() throws InterruptedException {   //Sets up motor configurations, etc.
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
        leftController = hardwareMap.dcMotorController.get("leftController");
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        leftfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        //_frontRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitCycle(6);
        leftfront.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //_frontRight.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitCycle(6);
    }
    public int GetTicks() throws InterruptedException { //gets the current value of the encoder
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);
        int position = leftfront.getCurrentPosition();
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        return position;
    }
    public void motorDrive(float motorPower){  //Driving forward
        leftfront.setPower(motorPower);
        rightfront.setPower(motorPower);
        leftback.setPower(motorPower);
        rightback.setPower(motorPower);
    }
    public void StopDriveMotors(){  //Stopping the robot
        leftfront.setPower(0);
        rightfront.setPower(0);
        leftback.setPower(0);
        rightback.setPower(0);
    }
    public void StopArmMotors(){  //Stopping the robot
        ArmTiltLeft.setPower(0);
        ArmTiltRight.setPower(0);
    }
    public void motorTurn(double motor_power , double direction){//Turning robot
        double leftpower = direction * motor_power;
        double rightpower = -1 *direction * motor_power;
        leftfront.setPower(leftpower);
        leftback.setPower(leftpower);
        rightfront.setPower(rightpower);
        rightback.setPower(rightpower);
    }
    public void motorArmRotate(double Arm_power , double direction){//Turning robot
        double Armpower = direction * Arm_power;
        ArmTiltRight.setPower(Armpower);
        ArmTiltLeft.setPower(Armpower);
    }
    public void ResetEncoders() throws InterruptedException {
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        leftfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitCycle(6);
        leftfront.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitCycle(6);
    }
    public void ResetArmEncoders() throws InterruptedException {
        waitCycle(6);
        ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        ArmTiltLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitCycle(6);
        ArmTiltLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitCycle(6);
    }
    public void waitCycle(int count) throws InterruptedException {
        for (int i = count; i > 0; i--) {
            waitOneFullHardwareCycle();
        }
    }
    public void motorPowerRampUp( double leftPower , double rightPower) throws InterruptedException {
        for(int i = 3; i > 0; i--){
            leftfront.setPower(leftPower / i);
            leftback.setPower(leftPower / i);
            rightfront.setPower(rightPower / i);
            rightback.setPower(rightPower / i);
            sleep(250);

        }
    }
    public abstract void initialize();
    public abstract void Update();
    public void goForward(int inches , double leftPower , double rightPower) throws InterruptedException {
        double goal = (GEAR_TWO_TEETH/GEAR_ONE_TEETH)*(CLICKS_PER_REVOLUTION/WHEEL_CIRCUMFERENCE)*inches;//Get ticks for the distance needed to travel
        goal = (goal - GOAL_DIFFERENCE) * DISTANCE_FACTOR;
        ResetEncoders();//Puts program in read only inside the routine
        waitCycle(6);
        int ticks ;
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        motorPowerRampUp(leftPower , rightPower);
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);
        ticks = leftfront.getCurrentPosition();
        while(ticks <= goal){
            ticks = leftfront.getCurrentPosition();
        }
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        StopDriveMotors();
    }
    public void goTurn(int degrees, double direction, double motor_power) throws InterruptedException {
        int ticks ;
        double goal = (GEAR_TWO_TEETH/GEAR_ONE_TEETH)*(CLICKS_PER_REVOLUTION)*degrees/360;//Get ticks for the distance needed to travel
        goal = goal * TURN_FACTOR;
        ResetEncoders();
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        motorTurn(motor_power, direction);
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);
        ticks = leftfront.getCurrentPosition();
        while(Math.abs(ticks) <= goal){
            ticks = leftfront.getCurrentPosition();
        }
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        StopDriveMotors();
    }
    public void armRotate(int degrees, int direction, double arm_power) throws InterruptedException {
        int ticks ;
        double goal = ((ArmGear1/ArmGear2)*(ArmGear3/ArmGear4))*(CLICKS_PER_REVOLUTION)*degrees/360;//Get ticks for the distance needed to travel
        ResetArmEncoders();
        waitCycle(6);
        ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        motorArmRotate(arm_power, direction);
        waitCycle(6);
        ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);
        ticks = ArmTiltLeft.getCurrentPosition();
        while(Math.abs(ticks) <= goal){
            ticks = ArmTiltLeft.getCurrentPosition();
        }
        ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        StopArmMotors();
    }
    @Override
    public void runOpMode() throws InterruptedException {
        Init();
        // Call subclass init
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            Update();
            waitForNextHardwareCycle();
        }
    }
}
