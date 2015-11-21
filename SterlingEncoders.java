package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
/**
 * Created by Bullet Proof on 11/19/2015.
 */
public class EncodersSterlingNov19 extends LinearOpMode {
    private DcMotor _frontLeft;
    private DcMotor _frontRight;
    private DcMotor _backLeft;
    private DcMotor _backRight;
    private DcMotorController _leftCon;
    private DcMotorController _rightCon;
    final double GEAR_ONE_TEETH = 16;
    final double GEAR_TWO_TEETH = 30;
    final double CLICKS_PER_REVOLUTION = 1120;
    final double WHEEL_CIRCUMFERENCE = 7 * Math.PI;
    final double DISTANCE = 25;

    @Override
    public void runOpMode() throws InterruptedException {
        _frontLeft = hardwareMap.dcMotor.get("lf");
        _backLeft = hardwareMap.dcMotor.get("lb");
        _frontRight = hardwareMap.dcMotor.get("rf");
        _backRight = hardwareMap.dcMotor.get("rb");
        _leftCon = hardwareMap.dcMotorController.get("leftController");
        _rightCon = hardwareMap.dcMotorController.get("rightController");

        _frontRight.setDirection(DcMotor.Direction.REVERSE);
        _backRight.setDirection(DcMotor.Direction.REVERSE);
        _frontLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        _frontRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        waitOneFullHardwareCycle();
        waitOneFullHardwareCycle();

        _frontLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        _frontRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitOneFullHardwareCycle();
        waitOneFullHardwareCycle();
        //2000 ticks = 81.6 inches
        //1000 ticks = 40.8 inches
        //500 ticks = 20.4 inches
        //250 ticks = 10.2 inches

        waitForStart();

        while (opModeIsActive()) {
            double inches = DISTANCE;
            double goal = (GEAR_TWO_TEETH/GEAR_ONE_TEETH)*(CLICKS_PER_REVOLUTION/WHEEL_CIRCUMFERENCE)*inches;
            int difference;
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            _rightCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            int ticksLeft = _frontLeft.getCurrentPosition();
            int ticksRight = _frontRight.getCurrentPosition();
            //telemetry.addData("Ticks:", String.valueOf(ticks));
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            _rightCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            _backLeft.setPower(.5);
            _frontLeft.setPower(0.5);
            _frontRight.setPower(0.5);
            _backRight.setPower(0.5);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            _rightCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            while(ticksLeft <= goal){
                ticksLeft = _frontLeft.getCurrentPosition();
            }
            ticksRight = _frontRight.getCurrentPosition();
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            //waitOneFullHardwareCycle();
            //waitOneFullHardwareCycle();
            //waitOneFullHardwareCycle();
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            _rightCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            _frontLeft.setPower(0);
            _backLeft.setPower(0);
            _frontRight.setPower(0);
            _backRight.setPower(0);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            _rightCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();

            ticksLeft = _frontLeft.getCurrentPosition();
            ticksRight = _frontRight.getCurrentPosition();
            difference = ticksLeft - ticksRight;
            telemetry.addData("Ticks Left:", String.valueOf(ticksLeft));
            telemetry.addData("Ticks Right:", String.valueOf(ticksRight));
            telemetry.addData("Difference:", String.valueOf(difference));
            telemetry.addData("Goal", String.valueOf(goal));
            break;
        }
    }
}
