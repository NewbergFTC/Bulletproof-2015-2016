package us.newberg.bullet;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
public class SterlingEncoders  {
    public abstract class Subroutines extends LinearOpMode {
        private DcMotor _frontLeft;
        private DcMotor _frontRight;
        private DcMotor _backLeft;
        private DcMotor _backRight;
        private DcMotorController _leftCon;

        //@Override
        public void runOpMode(int distance, float speed) throws InterruptedException {
            _frontLeft = hardwareMap.dcMotor.get("lf");
            _backLeft = hardwareMap.dcMotor.get("lb");
            _frontRight = hardwareMap.dcMotor.get("rf");
            _backRight = hardwareMap.dcMotor.get("rb");
            _leftCon = hardwareMap.dcMotorController.get("leftController");
            _frontRight.setDirection(DcMotor.Direction.REVERSE);
            _backRight.setDirection(DcMotor.Direction.REVERSE);
            _frontLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            _frontLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            //2000 ticks = 81.6 inches
            //1000 ticks = 40.8 inches
            //500 ticks = 20.4 inches
            //250 ticks = 10.2 inches

            _frontLeft.setTargetPosition(distance);
            waitForStart();
            while (opModeIsActive()) {
                _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                int ticks = _frontLeft.getCurrentPosition();
                telemetry.addData("Ticks:", String.valueOf(ticks));
                _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                _frontLeft.setPower(speed);
                _frontRight.setPower(speed);
                _backRight.setPower(speed);
                _backLeft.setPower(speed);
                //the ticks for other wheels are set at .9875 of the left front wheel
                if (ticks >= (distance * 0.9875)) {
                    _backLeft.setPower(0);
                    _frontRight.setPower(0);
                    _backRight.setPower(0);
                    break;
                }
                _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
            }
        }

        public void RunOpMode(int turn, float speed) throws InterruptedException {
            _frontLeft = hardwareMap.dcMotor.get("lf");
            _backLeft = hardwareMap.dcMotor.get("lb");
            _frontRight = hardwareMap.dcMotor.get("rf");
            _backRight = hardwareMap.dcMotor.get("rb");
            _leftCon = hardwareMap.dcMotorController.get("leftController");
            _frontRight.setDirection(DcMotor.Direction.REVERSE);
            _backRight.setDirection(DcMotor.Direction.REVERSE);
            _frontLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            _frontLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            waitOneFullHardwareCycle();
            waitOneFullHardwareCycle();
            //?ticks= 90 degree turn
            _frontLeft.setTargetPosition(turn);
            waitForStart();
            while (opModeIsActive()) {
                _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                int ticks = _frontLeft.getCurrentPosition();
                telemetry.addData("Ticks:", String.valueOf(ticks));
                _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                waitOneFullHardwareCycle();
                _frontLeft.setPower(speed);
                _frontRight.setPower(-speed);
                _backRight.setPower(-speed);
                _backLeft.setPower(speed);
                //the ticks for other wheels are set at .9875 of the left front wheel
                if (ticks >= (turn * 0.9875)) {
                    _backLeft.setPower(0);
                    _frontRight.setPower(0);
                    _backRight.setPower(0);
                    break;
                }
                _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);

            }
        }
    }
}