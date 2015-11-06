package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by thesm_000 on 11/5/2015.
 */
public class ManualReset extends OpMode {
    Servo leftArm;
    Servo rightArm;
    final double LEFT_ARM_OPEN = .5;//need to revers one of the servos
    final double LEFT_ARM_CLOSED = 0.0;
    final double RIGHT_ARM_OPEN = 0.5;
    final double RIGHT_ARM_CLOSED = 0.0;
@Override
  public void init(){
    leftArm = hardwareMap.servo.get("leftArm");
    rightArm = hardwareMap.servo.get("rightArm");
    rightArm.setPosition(RIGHT_ARM_CLOSED);
    leftArm.setPosition(LEFT_ARM_CLOSED);
    
  }
    

  public void loop(){
    leftArm.setPosition(LEFT_ARM_CLOSED);
    rightArm.setPosition(RIGHT_ARM_CLOSED);
  }
}
