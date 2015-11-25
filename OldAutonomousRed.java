package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
/**
 * Created by Bullet Proof on 11/20/2015.
 */
public class RedAutonomous extends LinearOpMode {
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
        final double MOTOR_POWER = .5;
        final double GOAL_DIFFERENCE = 100;
        final double DISTANCE_FACTOR = 0.89;
        final double TURN_FACTOR = 2.22;
        final double RIGHT = 1;
        final double LEFT = -1;
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
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

             waitCycle(6);

             _frontLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            _frontRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);

             waitCycle(6);

             _frontLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            _frontRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
             waitCycle(6);

            waitForStart();

            while (opModeIsActive()) {
                goForward(42, MOTOR_POWER);
                sleep(500);
                goTurn(45, LEFT, MOTOR_POWER);
           // goForward(20, MOTOR_POWER);
                sleep(500);
                goForward(44, MOTOR_POWER);
                sleep(500);
                goTurn(42, LEFT, MOTOR_POWER);
                sleep(500);
                goForward(36, 1); //accelerate into wall
                sleep(500);

            break;
            }
        }
        public void goForward(int inches, double motor_power) throws InterruptedException {
            //_frontLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            //_frontLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            //_frontLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            //_frontLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            double goal = (GEAR_TWO_TEETH/GEAR_ONE_TEETH)*(CLICKS_PER_REVOLUTION/WHEEL_CIRCUMFERENCE)*inches;//Get ticks for the distance needed to travel
            goal = (goal - GOAL_DIFFERENCE) * DISTANCE_FACTOR;
            telemetry.addData("Goal0:", String.valueOf(goal));
            ResetEncoders();//Puts program in read only inside the routine
            telemetry.addData("Goal1:", String.valueOf(goal));

            waitCycle(6);

            int ticks ;
            //ticks = _frontLeft.getCurrentPosition();
            //telemetry.addData("Ticks:", String.valueOf(ticks));

            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

            waitCycle(6);

            _backLeft.setPower(motor_power);
            _frontLeft.setPower(motor_power);
            _frontRight.setPower(motor_power * 0.9);//compensates for right side going to fast
            _backRight.setPower(motor_power * 0.9);

            waitCycle(6);

            //telemetry.addData("Goal:", String.valueOf(goal));
            //telemetry.addData("Ticks:", String.valueOf(ticks));
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);

            waitCycle(6);

            ticks = _frontLeft.getCurrentPosition();
            //telemetry.addData("Goal1:", String.valueOf(goal));
            while(ticks <= goal){
                ticks = _frontLeft.getCurrentPosition();
            }
            //telemetry.addData("Goal2:", String.valueOf(goal));
            //telemetry.addData("Ticks:", String.valueOf(ticks));
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

            waitCycle(6);

            _frontLeft.setPower(0);
            _backLeft.setPower(0);
            _frontRight.setPower(0);
            _backRight.setPower(0);
        }

        public void goTurn(int degrees, double direction, double motor_power) throws InterruptedException {
            double leftpower;
            int ticks ;
            double rightpower;
            double goal = (GEAR_TWO_TEETH/GEAR_ONE_TEETH)*(CLICKS_PER_REVOLUTION)*degrees/360;//Get ticks for the distance needed to travel
            goal = goal * TURN_FACTOR;
            ResetEncoders();

            waitCycle(6);

            //telemetry.addData("Ticks:", String.valueOf(ticks));
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

            waitCycle(6);

            leftpower = direction * motor_power;
            rightpower = -1 *direction * motor_power;
            _frontLeft.setPower(leftpower);
            _backLeft.setPower(leftpower);
            _backRight.setPower(rightpower);
            _frontRight.setPower(rightpower);

            waitCycle(6);

            //telemetry.addData("Goal:", String.valueOf(turngoal));
            //telemetry.addData("Ticks:", String.valueOf(ticks));
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);

            waitCycle(6);

            ticks = _frontLeft.getCurrentPosition();
            while(Math.abs(ticks) <= goal){
                ticks = _frontLeft.getCurrentPosition();
            }
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

            waitCycle(6);

            _frontLeft.setPower(0);
            _backLeft.setPower(0);
            _frontRight.setPower(0);
            _backRight.setPower(0);
        }


    public void ResetEncoders() throws InterruptedException {
            waitCycle(6);
            _leftCon.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
            waitCycle(6);
            _frontLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitCycle(6);
            _frontLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            waitCycle(6);
        }
    public void waitCycle(int count) throws InterruptedException {
        for (int i = count; i > 0; i--) {
            waitOneFullHardwareCycle();
        }
    }
}
