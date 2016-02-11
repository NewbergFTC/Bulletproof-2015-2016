package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;

import android.media.MediaPlayer;
import android.net.Uri;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.io.File;

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

    static final float PowerForward =.1f;
    static final float PowerBack    = -.1f;

    static final float LIFT_POWER_FORWARD = 1.0f;
    static final float LIFT_POWER_BACK = -1.0f;
    static final double SERVO_SKIRT_UP = .215;
    static final double SERVO_SKIRT_DOWN = .11;

    static final double LOWER_ARM_LEFT = .6;
    static final double LOWER_ARM_RIGHT = .4;
    static final double LOWER_ARM_OFF = .5;
    static final double UPPER_ARM_LOCKED = 1;
    static final double UPPER_ARM_UNLOCKED = .0;

    public MediaPlayer rocky;
    public MediaPlayer shiaSurprise;
    public MediaPlayer doIt;
    public MediaPlayer poundCake;
    public MediaPlayer vamos;
    public MediaPlayer roboto;

    @Override
    public void init() {
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");

        ArmTiltRight = hardwareMap.dcMotor.get("ArmTiltRight");
        ArmTiltLeft = hardwareMap.dcMotor.get("ArmTiltLeft");
        ArmLift = hardwareMap.dcMotor.get("ArmLift");

        SkirtServo = hardwareMap.servo.get("SkirtservoR");
        LowerArmLock = hardwareMap.servo.get("LowerArmLock");

        UpperArmLock = hardwareMap.servo.get("UpperArmLock");

        rightfront.setDirection(DcMotor.Direction.REVERSE);
        LowerArmLock.setDirection(Servo.Direction.REVERSE);
        ArmLift.setDirection(DcMotor.Direction.REVERSE);
        ArmTiltLeft.setDirection(DcMotor.Direction.REVERSE);

        SkirtServo.setPosition(SERVO_SKIRT_DOWN);
        UpperArmLock.setPosition(UPPER_ARM_UNLOCKED);
        LowerArmLock.setPosition(LOWER_ARM_OFF);

        rocky = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/rocky.mp3")));
        rocky.setVolume(1, 1);

        shiaSurprise = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/Shia.mp3")));
        shiaSurprise.setVolume(1, 1);

        doIt = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/doIt.mp3")));
        doIt.setVolume(1, 1);

        poundCake = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/PoundCake.mp3")));
        poundCake.setVolume(1, 1);

        vamos = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/Vamos.mp3")));
        vamos.setVolume(1, 1);

        roboto = MediaPlayer.create(hardwareMap.appContext, Uri.fromFile(new File("/mnt/sdcard/Roboto.mp3")));
        roboto.setVolume(1, 1);

        // TODO; This shouldn't be needed...
        // But it is
        doIt.start();
        poundCake.start();
        vamos.start();
        roboto.start();
        rocky.start();
        shiaSurprise.start();

        doIt.pause();
        poundCake.pause();
        vamos.pause();
        roboto.pause();
        rocky.pause();
        shiaSurprise.pause();
    }

    @Override
    public void loop() {
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

        if (gamepad1.dpad_up) 
            SkirtServo.setPosition(SERVO_SKIRT_UP);

        if (gamepad1.dpad_down) 
            SkirtServo.setPosition(SERVO_SKIRT_DOWN); 

        LowerArmLock.setPosition(gamepad1.a ? LOWER_ARM_RIGHT :
                gamepad1.b ? LOWER_ARM_LEFT : LOWER_ARM_OFF);

        if (gamepad1.x) 
            UpperArmLock.setPosition(UPPER_ARM_LOCKED);
        
        if (gamepad1.y) 
            UpperArmLock.setPosition(UPPER_ARM_UNLOCKED);
        


        /*
         *Epic Sound Board
         *To Intimidate All Other Teams
         */

        // rocky
        if (gamepad2.dpad_left) {
            shiaSurprise.pause();
            doIt.pause();
            poundCake.pause();
            vamos.pause();
            roboto.pause();

            rocky.seekTo(0);
            rocky.start();
        }

        // shiaSurprise
        if (gamepad2.dpad_up) {
            doIt.pause();
            poundCake.pause();
            vamos.pause();
            roboto.pause();
            rocky.pause();

            shiaSurprise.seekTo(0);
            shiaSurprise.start();
        }

        // doIt
        if (gamepad2.dpad_right) {
            poundCake.pause();
            vamos.pause();
            roboto.pause();
            rocky.pause();
            shiaSurprise.pause();

            doIt.seekTo(0);
            doIt.start();
        }

        // poundCake
        if (gamepad2.dpad_down) {
            rocky.pause();
            shiaSurprise.pause();
            doIt.pause();
            vamos.pause();
            roboto.pause();

            poundCake.seekTo(0);
            poundCake.start();
        }

        // vamos
        if (gamepad2.right_stick_button) {
            rocky.pause();
            shiaSurprise.pause();
            doIt.pause();
            poundCake.pause();
            roboto.pause();

            vamos.seekTo(0);
            vamos.start();
        }

        // roboto
        if (gamepad2.left_stick_button) {
            rocky.pause();
            shiaSurprise.pause();
            doIt.pause();
            poundCake.pause();
            vamos.pause();

            roboto.seekTo(0);
            roboto.start();
        }
    }
}
