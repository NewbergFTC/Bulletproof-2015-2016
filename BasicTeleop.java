
package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

public class testdrive extends OpMode{
    DcMotor lf;
    DcMotor lb;
    DcMotor rf;
    DcMotor rb;
    boolean frontWheel = true;
    @Override
    public void init(){
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
    }
    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        lf.setPower(leftY);
        rf.setPower(rightY);
        lb.setPower(leftY);
        rb.setPower(rightY);
        //if(gamepad1.a && frontWheel){
        //lf.setPower(leftY);
        //rf.setPower(rightY);
        //lb.setPower(leftY);
        //rb.setPower(rightY);
        //frontWheel = false;
        //}
        //else if(gamepad1.a){
        //lb.setPower(leftY);
        //rb.setPower(rightY);
        //lf.setPower(0);
        //rf.setPower(0);
        //frontWheel = true;
        //}
    }
}





