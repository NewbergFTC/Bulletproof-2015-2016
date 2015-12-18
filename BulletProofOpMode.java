package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.qualcomm.hardware.HiTechnicNxtTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import java.io.File;

public abstract class BulletProofMode extends LinearOpMode {
        public DcMotor leftfront;
        public DcMotor leftback;
        public DcMotor rightfront;
        public DcMotor rightback;
        public DcMotor ArmTiltLeft;
        public DcMotor ArmTiltRight;
        public DcMotor ArmLift;
        public Servo rightArm;
        public Servo leftArm;
        public Servo Skirtservo;
        public Servo LowerArmLock;
        public Servo UpperArmLock;
        public DcMotorController leftController;
        public DcMotorController ArmController;
        final double LEFT_ARM_OPEN = .5;
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
        final double MOTOR_POWER_LEFT = 0.9;
        final double MOTOR_POWER_RIGHT = 0.65;
        final double ARM_GEAR_1 = 72;
        final double ARM_GEAR_2 = 20;
        final double ARM_GEAR_3 = 72;
        final double ARM_GEAR_4 = 34;
        final double ArmStop = 0;
        final int armForward = 1;
        final int armBack = -1;
        final float ARM_POWER_FORWARD = (float) .1;
        final float ARM_POWER_BACK = (float) -.1;
        float PowerForward;
        float PowerBack;
        double SERVO_POSITION_UP = 0.5;
        double SERVO_POSITION_DOWN= 0;
        double LowerArmLocked = 0;
        double LowerArmUnlocked = .5;
        double UpperArmLocked = 0;
        double UpperArmUnlocked = .5;

        public static Context CONTEXT;
        public MediaPlayer JohnCena;
        HiTechnicNxtTouchSensor ArmReset;

        //float count  = 0;
        protected final void Init() throws InterruptedException {
            ArmTiltLeft = hardwareMap.dcMotor.get("ArmTiltLeft");
            ArmTiltRight = hardwareMap.dcMotor.get("ArmTiltRight");
            ArmLift = hardwareMap.dcMotor.get("ArmLift");
            leftfront = hardwareMap.dcMotor.get("lf");
            leftback = hardwareMap.dcMotor.get("lb");
            rightfront = hardwareMap.dcMotor.get("rf");
            rightback = hardwareMap.dcMotor.get("rb");
            ArmController = hardwareMap.dcMotorController.get("ArmController");
            leftController = hardwareMap.dcMotorController.get("leftController");
            leftArm = hardwareMap.servo.get("leftArm");
            rightArm = hardwareMap.servo.get("rightArm");
            Skirtservo = hardwareMap.servo.get("Skirtservo");
            LowerArmLock = hardwareMap.servo.get("LowerArmLock");
            UpperArmLock = hardwareMap.servo.get("UpperArmLock");
            rightback.setDirection(DcMotor.Direction.REVERSE);
            rightfront.setDirection(DcMotor.Direction.REVERSE);
            ArmTiltLeft.setDirection(DcMotor.Direction.REVERSE);
            rightArm.setPosition(RIGHT_ARM_CLOSED);
            leftArm.setPosition(LEFT_ARM_CLOSED);
            ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            waitCycle(6);
            ArmTiltLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitCycle(6);
            ArmTiltLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            waitCycle(6);
            leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            waitCycle(6);
            leftfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitCycle(6);
            leftfront.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            waitCycle(6);

            JohnCena = MediaPlayer.create(CONTEXT, Uri.fromFile(new File("/mnt/sdcard/cena.mp3")));
            JohnCena.setVolume(1, 1);
        }
        public int GetTicks() throws InterruptedException { //gets the current value of the encoder
            leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            waitCycle(6);
            int position = leftfront.getCurrentPosition();
            leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            waitCycle(6);
            return position;
        }
        public void SkirtServo(){
            if (gamepad2.dpad_up) {
                Skirtservo.setPosition(SERVO_POSITION_UP);
            }
            if (gamepad2.dpad_down){
                Skirtservo.setPosition(SERVO_POSITION_DOWN);
            }
        }
        public void LowerArmLock(){
            if(gamepad1.a){
                LowerArmLock.setPosition(LowerArmLocked);
            }
            if(gamepad1.b){
                LowerArmLock.setPosition(LowerArmUnlocked);
            }
        }
        public void UpperArmLock(){
            if(gamepad1.x){
                UpperArmLock.setPosition(UpperArmLocked);
            }
            if(gamepad1.y){
                UpperArmLock.setPosition(UpperArmUnlocked);
            }
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
        public void TouchSensor() throws InterruptedException{
            ArmReset = (HiTechnicNxtTouchSensor) hardwareMap.touchSensor.get("TouchSensor");
            String  touchValue;

            waitForStart();

            while(opModeIsActive()){
                if (ArmReset.isPressed()) {
                    touchValue = "Yes";
                    ArmTiltLeft.setPower(1);//if motors aren't attached it won't work
                    ArmTiltRight.setPower(1);
                }else {
                    touchValue = "No";
                }
                telemetry.addData("Is it pressed?", touchValue);
            }
        }
        public abstract void initialize();
        public abstract void Update();
        public boolean goForward(int inches , double leftPower , double rightPower, long delay) throws InterruptedException {
            double goal = (GEAR_TWO_TEETH/GEAR_ONE_TEETH)*(CLICKS_PER_REVOLUTION/WHEEL_CIRCUMFERENCE)*inches;//Get ticks for the distance needed to travel
            goal = (goal - GOAL_DIFFERENCE) * DISTANCE_FACTOR;
            ResetEncoders();//Puts program in read only inside the routine
            waitCycle(6);
            int ticks ;
            leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            waitCycle(6);
            motorPowerRampUp(leftPower, rightPower);
            waitCycle(6);
            leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            waitCycle(6);
            ticks = leftfront.getCurrentPosition();
            WatchDog dog = new WatchDog(this, delay);
            dog.start();
            while(ticks <= goal){
                ticks = leftfront.getCurrentPosition();

                if (!dog.GetRunning()) {
                    leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
                    waitCycle(6);
                    StopDriveMotors();

                    return true;
                }
            }
            leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            waitCycle(6);
            StopDriveMotors();

            return false;
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
            double goal = ((ARM_GEAR_1/ARM_GEAR_2)*(ARM_GEAR_3/ARM_GEAR_4))*(CLICKS_PER_REVOLUTION)*degrees/360;//Get ticks for the distance needed to travel
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
            initialize();
            waitForStart();
            while (opModeIsActive()) {
                Update();
                waitForNextHardwareCycle();
            }
        }
}



