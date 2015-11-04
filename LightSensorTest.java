package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

public class LightSensorTest extends OpMode{
    DcMotor lf;
    DcMotor lb;
    DcMotor rf;
    DcMotor rb;
    float lightSensor;
    
     public void init(){
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
        lightSensor = hardwareMap. opticalDistanceSensor.get("lightsensor")
    }
    @Override
    public void loop() {
    float lightsensordata = lightSensor.getLightDetected();
      if (lightsensordata => 80){
        lf.setPower(0)
        lb.setPower(0)
        rf.setPower(0)
        rb.setPower(0)
        }else{
        lf.setPower(left)
        lb.setPower(left)
        rf.setPower(right)
        rb.setPower(right)
        }
        telemetry.addData("lightsensordata value", lightsensordata)
    }
      
