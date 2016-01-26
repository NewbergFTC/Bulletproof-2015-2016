package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.hardware.HiTechnicNxtLightSensor;
public abstract class Light_Sensor extends LinearOpMode{
    LightSensor lightSensor;
    //public void Update(){
    //}
    public void lightsensortest(){
        lightSensor.enableLed(true);
        double LightSensorData = lightSensor.getLightDetected();
        double LightSensorDataRaw = lightSensor.getLightDetectedRaw();
        telemetry.addData("LightSensor" , LightSensorData);
        telemetry.addData("LightSensorRaw" , LightSensorDataRaw);
    }
    public void runOpMode() throws InterruptedException {
        lightSensor = hardwareMap.lightSensor.get("LightSensor");
        waitForStart();
        while (opModeIsActive()){
            lightSensor.enableLed(true);
            double LightSensorData = lightSensor.getLightDetected();
            double LightSensorDataRaw = lightSensor.getLightDetectedRaw();
            telemetry.addData("LightSensor" , LightSensorData);
            telemetry.addData("LightSensorRaw" , LightSensorDataRaw);
        }
    }
}
