package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
import android.bluetooth.BluetoothA2dp;
import android.graphics.Color;
import android.hardware.Sensor;
import android.location.Address;
import android.provider.CalendarContract;
import android.provider.CallLog;
import android.widget.Button;

import com.qualcomm.ftcrobotcontroller.opmodes.ColorSensorDriver;
import com.qualcomm.hardware.HiTechnicNxtColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LegacyModule;


/**
 * Created by Bullet Proof on 11/30/2015.
 */
public class ColorSensorTest extends LinearOpMode {
    ColorSensor  colorSensor;

    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.colorSensor.get("ColorSensor");

        waitForStart();
        double RedThreshold= 25;
        while (opModeIsActive()) {
            colorSensor.enableLed(true);

            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            boolean CurrentcolorRed =(colorSensor.red() > colorSensor.blue()) && (colorSensor.red() > RedThreshold);

           telemetry.addData("CurrentcolorRed",CurrentcolorRed);
        }
    }
}
