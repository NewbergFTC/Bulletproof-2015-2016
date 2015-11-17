package us.newberg.bullet;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Util;

import java.lang.ref.Reference;

/**
 * Created by btaka on 11/13/2015.
 */

public abstract class BulletOpMode extends LinearOpMode {

    public DcMotor leftfront;
    public DcMotor leftback;
    public DcMotor rightfront;
    public DcMotor rightback;
    public Servo rightArm;
    public Servo leftArm;
    public DcMotorController leftController;
    //Put motors for arms in as wel
    final double LEFT_ARM_OPEN = .5;//need to reverse one of the servos
    final double LEFT_ARM_CLOSED = 0.0;
    final double RIGHT_ARM_OPEN = 0.5;
    final double RIGHT_ARM_CLOSED = 0.0;
    final double PowerForward = .5;
    final double PowerBack = -.5;
    final double ArmStop = 0;
    final double CLICKS_PER_REVOLUTION = 280;
    final double WHEEL_CIRCUM_FRONT = 7;
    final double WHEEL_CIRCUM_BACK = 3.875;
    final float ENCODER_TICKS_PER_REVOLUTION = 280;
    final double WHEEL_CIRCUMFERENCE = 7 * Math.PI;

    public void runOpMode() throws InterruptedException{

        protected final void Init(){
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
            leftController = hardwareMap.dcMotorController.get("leftCon");
            leftfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            leftfront.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
        }

        public int GetTicks() {

            leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int position = leftfront.getCurrentPosition();
        }

        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        try{
            waitOneFullHardwareCycle();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return position;
        public void moveMotors(float motorPower){
            leftfront.setPower(motorPower);
            rightfront.setPower(motorPower);
            leftback.setPower(motorPower);
            rightback.setPower(motorPower);
        }
        public void GoForward(float inches, float power){
            float position = GetTicks();

            float ticks = (float) ((ENCODER_TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE) * inches);
            float target = position + ticks;

            telemetry.addData("Target Ticks", String.valueOf(target));

            if (_timer != null)
                _timer.Terminate();

            _timer = new DriveTimer(this, Util.RoundReal(inches * 0.9));

            while (position <= target){
                //Drive(power, power);
                moveMotors(power);
                position = GetTicks();
            }

            while (position >= target) {
                //Drive(-power, -power);
                moveMotors(-power);
                position = GetTicks();
            }

            _timer.Terminate();
        }
    }
}
