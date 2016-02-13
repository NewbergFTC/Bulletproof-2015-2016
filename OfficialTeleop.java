package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;

import android.media.MediaPlayer;
import android.net.Uri;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import java.io.File;

/*
Main Tele-Op Program used in all competitions
© Bullet Proof 6712
All rights reserved to the Country of the USA
*/
public class OfficialTeleOp extends OpMode {
    private DcMotor leftfront;
    private DcMotor leftback;
    private DcMotor rightfront;
    private DcMotor rightback;
    private DcMotor ArmTiltLeft;
    private DcMotor ArmTiltRight;
    private DcMotor ArmLift;
    private Servo SkirtServo;
    private Servo LowerArmLock;
    private Servo UpperArmLock;
    private ServoController servoController;
    float PowerForward = (float) .1;
    float PowerBack = (float) -.1;
    final static float LIFT_POWER_FORWARD = (float) 1;
    final static float LIFT_POWER_BACK = -1;
    final static double SERVO_SKIRT_UP = .215;
    final static double SERVO_SKIRT_DOWN = .11;
    final static double LOWER_ARM_LEFT = .6;
    final static double LOWER_ARM_RIGHT = .4;
    final static double LOWER_ARM_OFF = .5;
    final static double UPPER_ARM_LOCKED = 0.935;
    final static double UPPER_ARM_UNLOCKED = 0.018;
    public MediaPlayer Rocky;
    public MediaPlayer ShiaSurprise;
    public MediaPlayer DoIt;
    public MediaPlayer poundCake;
    public MediaPlayer vamos;
    public MediaPlayer roboto;

    @Override
    public void init() {
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        ArmTiltRight = hardwareMap.dcMotor.get("ArmTiltRight");
        ArmTiltLeft = hardwareMap.dcMotor.get("ArmTiltLeft");
        ArmLift = hardwareMap.dcMotor.get("ArmLift");
        ArmLift.setDirection(DcMotor.Direction.REVERSE);
        ArmTiltLeft.setDirection(DcMotor.Direction.REVERSE);
        SkirtServo = hardwareMap.servo.get("SkirtServo");
        LowerArmLock = hardwareMap.servo.get("LowerArmLock");
        LowerArmLock.setDirection(Servo.Direction.REVERSE);
        UpperArmLock = hardwareMap.servo.get("UpperArmLock");
        servoController = hardwareMap.servoController.get("ServoCon");
        servoController.pwmDisable();
        SkirtServo.setPosition(SERVO_SKIRT_DOWN);
        UpperArmLock.setPosition(UPPER_ARM_UNLOCKED);
        LowerArmLock.setPosition(LOWER_ARM_OFF);
        Rocky = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/rocky.mp3")));
        Rocky.setVolume(1, 1);
        ShiaSurprise = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/Shia.mp3")));
        ShiaSurprise.setVolume(1, 1);
        DoIt = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/DoIt.mp3")));
        DoIt.setVolume(1, 1);
        poundCake = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/PoundCake.mp3")));
        poundCake.setVolume(1, 1);
        vamos = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/Vamos.mp3")));
        vamos.setVolume(1, 1);
        roboto = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/Roboto.mp3")));
        roboto.setVolume(1, 1);

        DoIt.start();
        poundCake.start();
        vamos.start();
        roboto.start();
        Rocky.start();
        ShiaSurprise.start();
        DoIt.pause();
        poundCake.pause();
        vamos.pause();
        roboto.pause();
        Rocky.pause();
        ShiaSurprise.pause();
    }

    @Override
    public void loop() {
        servoController.pwmEnable();

        float leftY = gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        double backleftpower = (leftY) * .75;
        double backrightpower = (rightY) * .75;
        double frontleftpower = (leftY * .9 * .75 * -1);
        double frontrightpower = (rightY * .9) * .75;
        leftfront.setPower(frontleftpower);
        rightfront.setPower(frontrightpower);
        leftback.setPower(backleftpower);
        rightback.setPower(backrightpower);

        PowerForward = (gamepad2.a) ? -1 : (float) -0.2;
        PowerBack = (gamepad2.a) ? 1 : (float) 0.2;

        float armPower = (gamepad2.left_trigger >= 0.05) ? PowerForward : (gamepad2.right_trigger >= 0.05) ? PowerBack : 0;
        ArmTiltLeft.setPower(armPower);
        ArmTiltRight.setPower(armPower);

        float extendPower = (gamepad2.left_bumper) ? LIFT_POWER_FORWARD : (gamepad2.right_bumper) ? LIFT_POWER_BACK : 0;
        ArmLift.setPower(extendPower);

        if (gamepad1.dpad_up) {
            SkirtServo.setPosition(SERVO_SKIRT_UP);
        }
        if (gamepad1.dpad_down) {
            SkirtServo.setPosition(SERVO_SKIRT_DOWN);
        }

        LowerArmLock.setPosition(gamepad1.a ? LOWER_ARM_RIGHT :
                gamepad1.b ? LOWER_ARM_LEFT : LOWER_ARM_OFF);

        if (gamepad1.x) {
            UpperArmLock.setPosition(UPPER_ARM_LOCKED);
        }
        if (gamepad1.y) {
            UpperArmLock.setPosition(UPPER_ARM_UNLOCKED);
        }

        /*
        Epic Sound Board
        To Intimidate All Other Teams
        */

        // Rocky
        if (gamepad2.dpad_left) {
            ShiaSurprise.pause();
            DoIt.pause();
            poundCake.pause();
            vamos.pause();
            roboto.pause();

            Rocky.seekTo(0);
            Rocky.start();
        }
        //Shia Surprise
        if (gamepad2.dpad_up) {
            DoIt.pause();
            poundCake.pause();
            vamos.pause();
            roboto.pause();
            Rocky.pause();

            ShiaSurprise.seekTo(0);
            ShiaSurprise.start();
        }
        //Do It
        if (gamepad2.dpad_right) {
            poundCake.pause();
            vamos.pause();
            roboto.pause();
            Rocky.pause();
            ShiaSurprise.pause();

            DoIt.seekTo(0);
            DoIt.start();
        }
        //Cake Cake Cake
        if (gamepad2.dpad_down) {
            Rocky.pause();
            ShiaSurprise.pause();
            DoIt.pause();
            vamos.pause();
            roboto.pause();

            poundCake.seekTo(0);
            poundCake.start();
        }
        //Vamos a la Playa
        if (gamepad2.right_stick_button) {
            Rocky.pause();
            ShiaSurprise.pause();
            DoIt.pause();
            poundCake.pause();
            roboto.pause();

            vamos.seekTo(0);
            vamos.start();
        }
        //Domo Arigato Mr. Roboto
        if (gamepad2.left_stick_button) {
            Rocky.pause();
            ShiaSurprise.pause();
            DoIt.pause();
            poundCake.pause();
            vamos.pause();

            roboto.seekTo(0);
            roboto.start();
        }
    }
}
