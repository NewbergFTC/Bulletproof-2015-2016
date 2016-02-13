package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.qualcomm.ftcrobotcontroller.opmodes.FtcOpModeRegister;
import com.qualcomm.hardware.HiTechnicNxtLightSensor;
import com.qualcomm.hardware.HiTechnicNxtUltrasonicSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

import java.io.File;

public abstract class BulletOpMode extends LinearOpMode {
    public HiTechnicNxtLightSensor leftLightSensor;
    public HiTechnicNxtLightSensor rightLightSensor;
    public HiTechnicNxtUltrasonicSensor ultrasonicSensor;

    public DcMotor leftfront;
    public DcMotor leftback;
    public DcMotor rightfront;
    public DcMotor rightback;
    public DcMotor ArmTiltLeft;
    public DcMotor ArmTiltRight;
    public DcMotor ArmLift;

    public Servo skirtServo;
    public Servo LowerArmLock;
    public Servo UpperArmLock;

    public DcMotorController leftController;
    public DcMotorController ArmController;

    public MediaPlayer rocky;
    public MediaPlayer shia;
    public MediaPlayer shiaSurprise;
    public MediaPlayer doIt;
    public MediaPlayer roboto;

    public static final double CLICKS_PER_REVOLUTION = 1120;
    public static final double WHEEL_CIRCUMFERENCE = (7 * Math.PI);
    public static final double GOAL_DIFFERENCE = 100;
    public static final double DISTANCE_FACTOR = 0.89;
    public static final double TURN_FACTOR = 2.22;
    public static final double RIGHT = 1;
    public static final double LEFT = -1;
    public static final double ARM_GEAR_1 = 72;
    public static final double ARM_GEAR_2 = 20;
    public static final double ARM_GEAR_3 = 72;
    public static final double ARM_GEAR_4 = 34;
    public static final double LEFT_FRONT_POWER = .165;
    public static final double RIGHT_FRONT_POWER = .155;
    public static final double LEFT_BACK_POWER = .15;
    public static final double RIGHT_BACK_POWER = .125;
    public static final double SERVO_SKIRT_UP = .215;
    public static final double SERVO_SKIRT_DOWN = .11;
    public static final double UPPER_ARM_UNLOCKED = .018;

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

        skirtServo = hardwareMap.servo.get("SkirtServo");

        LowerArmLock = hardwareMap.servo.get("LowerArmLock");
        UpperArmLock = hardwareMap.servo.get("UpperArmLock");

        ultrasonicSensor = (HiTechnicNxtUltrasonicSensor) hardwareMap.ultrasonicSensor.get("SonarDrive");
        leftLightSensor = (HiTechnicNxtLightSensor) hardwareMap.lightSensor.get("LightSensor");
        rightLightSensor = (HiTechnicNxtLightSensor) hardwareMap.lightSensor.get("RightLightSensor");

        rightfront.setDirection(DcMotor.Direction.REVERSE);
        ArmTiltLeft.setDirection(DcMotor.Direction.REVERSE);
        ArmLift.setDirection(DcMotor.Direction.REVERSE);
        ArmTiltLeft.setDirection(DcMotor.Direction.REVERSE);

        ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        ArmTiltLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitCycle(6);
        ArmTiltLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        leftfront.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitCycle(6);
        leftfront.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitCycle(6);

        skirtServo.setPosition(SERVO_SKIRT_DOWN);
        UpperArmLock.setPosition(UPPER_ARM_UNLOCKED);
        LowerArmLock.setPosition(.5);

        rocky = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/rocky.mp3")));
        rocky.setVolume(1, 1);

        shia = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/shia.mp3")));
        shia.setVolume(1, 1);

        shiaSurprise = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/shiaSurprise.mp3")));
        shiaSurprise.setVolume(1, 1);

        doIt = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/doIt.mp3")));
        doIt.setVolume(1, 1);

        roboto = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/roboto.mp3")));
        roboto.setVolume(1, 1);
    }

    public int GetTicks() throws InterruptedException {
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);

        int position = leftfront.getCurrentPosition();

        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);

        return position;
    }

    public void motorDrive(double LeftFrontPower, double RightFrontPower, double LeftBackPower, double RightBackPower) {
        leftfront.setPower(LeftFrontPower);
        rightfront.setPower(RightFrontPower);
        leftback.setPower(LeftBackPower * -1);
        rightback.setPower(RightBackPower);
    }

    public void StopDriveMotors() {
        leftfront.setPower(0);
        rightfront.setPower(0);
        leftback.setPower(0);
        rightback.setPower(0);
    }

    public void StopArmMotors() {
        ArmTiltLeft.setPower(0);
        ArmTiltRight.setPower(0);
    }

    public boolean lightSensor(HiTechnicNxtLightSensor lightSensor) {
        lightSensor.enableLed(true);

        // TODO: Calculate or measure a real time
        WatchDog watchDog = new WatchDog(this, 20 * 1000);
        watchDog.start();

        double LightSensorDataRaw = lightSensor.getLightDetectedRaw();
        while (LightSensorDataRaw < 120) {
            LightSensorDataRaw = lightSensor.getLightDetectedRaw();
            motorDrive(LEFT_FRONT_POWER, RIGHT_FRONT_POWER, LEFT_BACK_POWER, RIGHT_BACK_POWER);

//            checkOpIsActive();

            if (!opModeIsActive())
                return true;

            if (!watchDog.GetRunning()) {
                StopDriveMotors();

                return true;
            }
        }

        StopDriveMotors();

        watchDog.Terminate();
        return false;
    }

    public void motorTurn(double LeftFrontPower, double RightFrontPower, double LeftBackPower, double RightBackPower, double direction) {
        double LeftFrontMotorPower = direction * LeftFrontPower;
        double RightFrontMotorPower = -1 * direction * RightFrontPower;
        double LeftBackMotorPower = -1 * direction * LeftBackPower;
        double RightBackMotorPower = -1 * direction * RightBackPower;

        leftfront.setPower(LeftFrontMotorPower);
        leftback.setPower(LeftBackMotorPower);
        rightfront.setPower(RightFrontMotorPower);
        rightback.setPower(RightBackMotorPower);
    }

    public void motorArmRotate(double Arm_power, double direction) {
        double ArmFinalPower = direction * Arm_power;

        ArmTiltRight.setPower(ArmFinalPower);
        ArmTiltLeft.setPower(ArmFinalPower);
    }

    public void ResetEncoders() throws InterruptedException {
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        leftfront.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitCycle(6);
        leftfront.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitCycle(6);
    }

    public void ResetArmEncoders() throws InterruptedException {
        waitCycle(6);
        ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        ArmTiltLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitCycle(6);
        ArmTiltLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitCycle(6);
    }

    public void waitCycle(int count) throws InterruptedException {
        for (int i = count; i > 0; i--) {
            waitOneFullHardwareCycle();
        }
    }

    public void motorPowerRampUp(double LeftFrontPower, double RightFrontPower, double LeftBackPower, double RightBackPower) throws InterruptedException {
        for (int i = 3; i > 0; i--) {
            leftfront.setPower(LeftFrontPower / i);
            leftback.setPower(-1 * LeftBackPower / i);
            rightfront.setPower(RightFrontPower / i);
            rightback.setPower(RightBackPower / i);
            sleep(250);
        }
    }

    public void ArmLiftUp() {
        ArmLift.setPower(1);
    }

    public void ArmLiftStop() {
        ArmLift.setPower(0);
    }

    public void initialize() { }
    public void Update() { }

    // TODO(): Can this handle a negative distance?
    public boolean goForward(int inches, double LeftFrontPower, double RightFrontPower, double LeftBackPower, double RightBackPower, long delay) throws InterruptedException {
        double goal = (CLICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE) * inches;
        goal = (goal - GOAL_DIFFERENCE) * DISTANCE_FACTOR;

        ResetEncoders();

        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        motorPowerRampUp(LeftFrontPower, RightFrontPower, LeftBackPower, RightBackPower);
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);

        WatchDog dog = new WatchDog(this, delay);
        dog.start();

        int ticks = leftfront.getCurrentPosition();
        while (ticks <= goal) {
            ticks = leftfront.getCurrentPosition();

            if (!opModeIsActive())
                return true;

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

        dog.Terminate();
        return false;
    }

    public boolean goTurn(int degrees, double direction, double LeftFrontPower, double RightFrontPower, double LeftBackPower, double RightBackPower) throws InterruptedException {
        double goal = (CLICKS_PER_REVOLUTION) * degrees / 360;
        goal = goal * TURN_FACTOR;

        ResetEncoders();

        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);

        motorTurn(LeftFrontPower, RightFrontPower, LeftBackPower, RightBackPower, direction);
        waitCycle(6);

        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);

        // TODO: Calculate or measure a real time
        WatchDog watchDog = new WatchDog(this, 10 * 1000);
        watchDog.start();

        int ticks = leftfront.getCurrentPosition();
        while (Math.abs(ticks) <= goal) {
            ticks = leftfront.getCurrentPosition();

            if (!opModeIsActive())
                return true;

            if (!watchDog.GetRunning()) {
                leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
                waitCycle(6);

                StopDriveMotors();

                return true;
            }
        }

        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);

        StopDriveMotors();

        watchDog.Terminate();
        return false;
    }

    // TODO: Return error true or false
    public void armRotate(int degrees, int direction, double arm_power) throws InterruptedException {
        double goal = ((ARM_GEAR_1 / ARM_GEAR_2) * (ARM_GEAR_3 / ARM_GEAR_4)) * (CLICKS_PER_REVOLUTION) * degrees / 360;

        ResetArmEncoders();

        waitCycle(6);
        ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        motorArmRotate(arm_power, direction);
        waitCycle(6);
        ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitCycle(6);

        int ticks = ArmTiltLeft.getCurrentPosition();

        // TODO: Calculate or measure a real time
        WatchDog watchDog = new WatchDog(this, 7 * 1000);
        watchDog.start();

        while (Math.abs(ticks) <= goal) {
            ticks = ArmTiltLeft.getCurrentPosition();

            if (!watchDog.GetRunning()) {
                break;
            }
        }

        ArmController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);

        watchDog.Terminate();
        StopArmMotors();
    }

    public void KillAllMotors() {
        StopDriveMotors();
        StopArmMotors();
    }

//    public boolean checkOpIsActive() {
//        if (!opModeIsActive()) {
//            KillAllMotors();
//
//            FtcOpModeRegister.opModeManager.stopActiveOpMode();
//            FtcOpModeRegister.opModeManager.initActiveOpMode("StopRobot");
//            return false;
//        }
//
//        return true;
//    }

    @Override
    public void sleep(long millis) throws InterruptedException {
        final long TIME_TO_SLEEP = millis / 10;
        long timeRemaining = millis;

        while (timeRemaining > 0)
        {
            super.sleep(TIME_TO_SLEEP);

            timeRemaining -= TIME_TO_SLEEP;
        }
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
