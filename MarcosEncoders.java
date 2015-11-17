package us.newberg.bullet;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
///import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by btaka on 11/13/2015.
 */
public class MarcosEncoders {
    public abstract class subroutines extends LinearOpMode{}
        DcMotor leftfront;
        DcMotor rightfront;

        static final int ENCODER_CPR = 280; // Encoder Counts per revolution
        static final double GEAR_RATIO = 1.875;
        static final int WHEEL_DIAMETER = 7;
        final static int DISTANCE = 94;

        final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
        final static double ROTATIONS = DISTANCE / CIRCUMFERENCE;
        final static double COUNTS = ENCODER_CPR * ROTATIONS * GEAR_RATIO;

        @Override
        public void init() {
            leftfront = hardwareMap.dcMotor.get("lf");
            rightfront = hardwareMap.dcMotor.get("rf");

            rightfront.setDirection(DcMotor.Direction.REVERSE);

            rightfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            leftfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
        @Override
        public void start(){
            leftfront.setTargetPosition((int)COUNTS);
            rightfront.setTargetPosition((int)COUNTS);

            leftfront.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            rightfront.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

            leftfront.setPower(0.5);
            rightfront.setPower(0.5);;
            @Override
            public void loop(){
                telemetry.addData("Motor Target", COUNTS);
                telemetry.addData("Left Position",leftfront.getCurrentPosition());
                telemetry.addData("Right Position", rightfront.getCurrentPosition());
        }
        }
}

