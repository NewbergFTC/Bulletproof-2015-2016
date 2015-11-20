package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
/**
 * Created by Bullet Proof on 11/19/2015.
 */
public class EncodersBrandonNov19 extends LinearOpMode {
        private DcMotor _frontLeft;
        private DcMotor _frontRight;
        private DcMotor _backLeft;
        private DcMotor _backRight;
        private DcMotorController _leftCon;
        final double GEAR_ONE_TEETH = 16;
        final double GEAR_TWO_TEETH = 30;
        final double CLICKS_PER_REVOLUTION = 1120;// Revolution come from Motor
        final double WHEEL_CIRCUMFERENCE = 7 * Math.PI;
        final double DISTANCE = 25;// One tile
        final double MOTOR_POWER = .5;

        @Override
        public void runOpMode() throws InterruptedException {
            _frontLeft = hardwareMap.dcMotor.get("lf");
            _backLeft = hardwareMap.dcMotor.get("lb");
            _frontRight = hardwareMap.dcMotor.get("rf");
            _backRight = hardwareMap.dcMotor.get("rb");
            _leftCon = hardwareMap.dcMotorController.get("leftController");
            _frontRight.setDirection(DcMotor.Direction.REVERSE);
            _backRight.setDirection(DcMotor.Direction.REVERSE);
            _frontLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            _frontLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            //2000 ticks = 81.6 inches
            //1000 ticks = 40.8 inches
            //500 ticks = 20.4 inches
            //250 ticks = 10.2 inches

            waitForStart();

            while (opModeIsActive()) {
                double inches = DISTANCE;
                double goal = (GEAR_TWO_TEETH/GEAR_ONE_TEETH)*(CLICKS_PER_REVOLUTION/WHEEL_CIRCUMFERENCE)*inches;//Get ticks for the distance needed to travel
                ResetEncoders();
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);//Updates the left encoders
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                int ticks = _frontLeft.getCurrentPosition();
                //telemetry.addData("Ticks:", String.valueOf(ticks));
                _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
                while (ticks < goal){
                    waitOneFullHardwareCycle();
                    waitOneFullHardwareCycle();
                    _frontLeft.setPower(MOTOR_POWER);
                    _backLeft.setPower(MOTOR_POWER);
                    _backRight.setPower(MOTOR_POWER);
                    _frontRight.setPower(MOTOR_POWER);
                    telemetry.addData("Goal:", String.valueOf(goal));
                    telemetry.addData("Ticks:", String.valueOf(ticks));
                }
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                if(ticks > goal) {
                    _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
                    waitOneFullHardwareCycle();
                    waitOneFullHardwareCycle();

                    _frontLeft.setPower(0);
                    _backLeft.setPower(0);
                    _frontRight.setPower(0);
                    _backRight.setPower(0);
                    break;
                }
                //_leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            }
        }
    public void ResetEncoders(){
        _frontLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
}
