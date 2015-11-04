package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.hardware.ModernRoboticsNxtLightSensor;
public class lightsensortest extends OpMode {
    DcMotor lf;
    DcMotor lb;
    DcMotor rf;
    DcMotor rb;
    ModernRoboticsNxtLightSensor lightSensor;
    public void init() {
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
        //lightSensor = hardwareMap.
    }
    @Override
    public void loop() {
        Double lightsensordata = lightSensor.getLightDetected();
        if (lightsensordata > 80) {
            lf.setPower(0);
            lb.setPower(0);
            rf.setPower(0);
            rb.setPower(0);
        } else {
            lf.setPower(1);
            lb.setPower(1);
            rf.setPower(1);
            rb.setPower(1);
        }
        telemetry.addData("lightsensordata value", lightsensordata);
    }
}
