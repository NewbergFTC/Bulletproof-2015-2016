package com.qualcomm.ftcrobotcontroller.opmodes;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
//import com.qualcomm.hardware.HiTechnicNxtTouchSensor;
import com.qualcomm.hardware.HiTechnicNxtUltrasonicSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import java.io.File;
public abstract class BulletOpMode extends LinearOpMode {
    public DcMotor leftfront;
    public DcMotor leftback;
    public DcMotor rightfront;
    public DcMotor rightback;
    public DcMotor ArmTiltLeft;
    public DcMotor ArmTiltRight;
    public DcMotor ArmLift;
    public Servo SkirtServoR;
    public Servo SkirtServoL;
    public Servo LowerArmLock;
    public Servo UpperArmLock;
    private Servo Zipline;
    public DcMotorController leftController;
    public DcMotorController ArmController;
    public HiTechnicNxtUltrasonicSensor ultrasonicSensor;
    //final double GEAR_ONE_TEETH = 16;
    //final double GEAR_TWO_TEETH = 30;
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
    final double FRONT_WHEEL_RATIO = 1.8;
    final double BACK_WHEEL_RATIO = 1;
    final double LEFT_FRONT_POWER = .165;
    final double RIGHT_FRONT_POWER = .155;
    final double LEFT_BACK_POWER = .15;
    final double RIGHT_BACK_POWER = .125;
    final double ArmStop = 0;
    final int armForward = 1;
    final int armBack = -1;
    final float ARM_POWER_FORWARD = (float) .2;
    final float ARM_POWER_BACK = (float) -.2;
    float PowerForward;
    float PowerBack;
    final static double SERVO_SKIRT_UPR = .23;
    final static double SERVO_SKIRT_DOWNR= .1;
    final static double SERVO_SKIRT_UPL = .23;
    final static double SERVO_SKIRT_DOWNL= 0.1;
    final static double LOWER_ARM_LEFT = .6;
    final static double LOWER_ARM_RIGHT = .4;
    final static double LOWER_ARM_OFF = .5;
    final static double UPPER_ARM_LOCKED = .54;
    final static double UPPER_ARM_UNLOCKED = .25;
    final static double ZIPLINE_UP = .6;
    final static double ZIPLINE_DOWN = .23;
    public static Context CONTEXT;
    public MediaPlayer Rocky;
    //HiTechnicNxtTouchSensor ArmReset;
    //float count  = 0;
    protected final void Init() throws InterruptedException {
        ArmTiltLeft = hardwareMap.dcMotor.get("ArmTiltLeft");
        ArmTiltRight = hardwareMap.dcMotor.get("ArmTiltRight");
        ArmTiltLeft.setDirection(DcMotor.Direction.REVERSE);
        ArmLift = hardwareMap.dcMotor.get("ArmLift");
        ArmLift.setDirection(DcMotor.Direction.REVERSE);
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
        ArmController = hardwareMap.dcMotorController.get("ArmController");
        leftController = hardwareMap.dcMotorController.get("leftController");
        SkirtServoR = hardwareMap.servo.get("SkirtservoR");
        SkirtServoL = hardwareMap.servo.get("SkirtservoL");
        LowerArmLock = hardwareMap.servo.get("LowerArmLock");
        UpperArmLock = hardwareMap.servo.get("UpperArmLock");
        Zipline = hardwareMap.servo.get("Zipline");
        ultrasonicSensor = (HiTechnicNxtUltrasonicSensor) hardwareMap.ultrasonicSensor.get("SonarDrive");
        //leftback.setDirection(DcMotor.Direction.REVERSE);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        ArmTiltLeft.setDirection(DcMotor.Direction.REVERSE);
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
        SkirtServoR.setPosition(SERVO_SKIRT_UPR);
        SkirtServoL.setPosition(SERVO_SKIRT_UPL);
        UpperArmLock.setPosition(UPPER_ARM_UNLOCKED);
        Zipline.setPosition(ZIPLINE_UP);//fix left front0
        Rocky = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/rocky.mp3")));
        Rocky.setVolume(1, 1);
    }
    public int GetTicks() throws InterruptedException { //gets the current value of the encoder
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);
        int position = leftfront.getCurrentPosition();
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        return position;
    }
    public void motorDrive(double LeftFrontPower, double RightFrontPower , double LeftBackPower , double RightBackPower){  //Driving forward
        leftfront.setPower(LeftFrontPower);
        rightfront.setPower(RightFrontPower);
        leftback.setPower(LeftBackPower * -1);
        rightback.setPower(RightBackPower);
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
    public void motorTurn(double LeftFrontPower , double RightFrontPower , double LeftBackPower , double RightBackPower , double direction){//Turning robot
        double LeftFrontMotorPower =  direction * LeftFrontPower;
        double RightFrontMotorPower = -1 * direction * RightFrontPower;
        double LeftBackMotorPower = -1 * direction * LeftBackPower;
        double RightBackMotorPower = -1 * direction * RightBackPower;
        leftfront.setPower(LeftFrontMotorPower);
        leftback.setPower(LeftBackMotorPower);
        rightfront.setPower(RightFrontMotorPower);
        rightback.setPower(RightBackMotorPower);
    }
    public void motorArmRotate(double Arm_power , double direction){//Turning robot
        double ArmFinalPower = direction * Arm_power;
        ArmTiltRight.setPower(ArmFinalPower);
        ArmTiltLeft.setPower(ArmFinalPower);
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
    public void motorPowerRampUp( double LeftFrontPower , double RightFrontPower , double LeftBackPower , double RightBackPower) throws InterruptedException {
        for(int i = 3; i > 0; i--){
            leftfront.setPower(LeftFrontPower / i);
            leftback.setPower( -1 * LeftBackPower / i);
            rightfront.setPower(RightFrontPower / i);
            rightback.setPower(RightBackPower / i);
            sleep(250);
        }
    }
    public void ArmLiftUp (){
        ArmLift.setPower(1);
    }
    public void ArmLiftStop (){
        ArmLift.setPower(0);
    }
    //public void TouchSensor() throws InterruptedException{
    //ArmReset = (HiTechnicNxtTouchSensor) hardwareMap.touchSensor.get("TouchSensor");
    //String  touchValue;

    //waitForStart();

    //while(opModeIsActive()){
    //if (ArmReset.isPressed()) {
    //touchValue = "Yes";
    //ArmTiltLeft.setPower(1);//if motors aren't attached it won't work
    //ArmTiltRight.setPower(1);
    //}else {
    // touchValue = "No";
    //}
    // telemetry.addData("Is it pressed?", touchValue);
    //}
    //}
    public abstract void initialize();
    public abstract void Update();
    public boolean goForward(int inches , double LeftFrontPower , double RightFrontPower , double LeftBackPower , double RightBackPower, long delay) throws InterruptedException {
        double goal = (CLICKS_PER_REVOLUTION/WHEEL_CIRCUMFERENCE)*inches; //Get ticks for the distance needed to travel
        goal = (goal - GOAL_DIFFERENCE) * DISTANCE_FACTOR;
        ResetEncoders();//Puts program in read only inside the routine
        waitCycle(6);
        int ticks ;
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        motorPowerRampUp(LeftFrontPower , RightFrontPower , LeftBackPower , RightBackPower);
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);
        ticks = leftfront.getCurrentPosition();
        WatchDog dog = new WatchDog(this, delay);
        dog.start();
        while(ticks <= goal) {
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
    public void goTurn(int degrees, double direction, double LeftFrontPower , double RightFrontPower , double LeftBackPower , double RightBackPower) throws InterruptedException {
        int ticks ;
        double goal = (CLICKS_PER_REVOLUTION)*degrees/360;//Get ticks for the distance needed to travel
        goal = goal * TURN_FACTOR;
        ResetEncoders();
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        motorTurn(LeftFrontPower , RightFrontPower , LeftBackPower , RightBackPower , direction);
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
