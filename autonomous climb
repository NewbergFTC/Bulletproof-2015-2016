package us.newberg.bullet;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;


public class DriveTrain extends OpMode{
    DcMotor lf;
    DcMotor lb;
    DcMotor rf;
    DcMotor rb;
    
    @Override 
    public void runOpMode() throws InterruptedException{
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
        rf.setdirection(DcMotor.Direction.REVERSE);
        rb.setdirection(DcMotor.Direction.REVERSE);
    
    
    //make it go forward
      lf.setPower(1);
      rf.setPower(1);
      lb.setPower(1);
      sleep(2000);
      //angle left
      lf.setPower(-1);
      rf.setPower(1);
      lb.setPower(-1);
      rb.setPower(1);
      sleep(1000);
      //go straight up ramp
      lf.setPower(1);
      rf.setPower(1);
      lb.setPower(1);
      rb.setPower(1);
      sleep(5000);
      //stop
      lf.setPower(0);
      rf.setPower(0);
      lb.setPower(0);
      rb.setPower(0);
      }
}
