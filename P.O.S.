
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Marcos on 10/21/2015.
 */
public class  TankDriveAlt extends OpMode {
    DcMotor leftfront;
    DcMotor rightfront;
    DcMotor leftback;
    DcMotor rightback;

    @Override
    public void init() {
        //Gets the variables for the motors
       leftfront = hardwareMap.dcMotor.get("lf");
        rightfront = hardwareMap.dcMotor.get("rf");
        leftback= hardwareMap.dcMotor.get("lb");
        rightback=hardwareMap.dcMotor.get("rb");
        
        //it reverses the Motor the reversed direction
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        rightback.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop(){
        float ValueX= -gamepad1.left_stick_y;
        float ValueY= -gamepad1.right_stick_y;
        float leftpower= ValueY+ValueX;
        float rightpower= ValueY-ValueX;

        leftpower= Range.clip(leftpower,-1,1);
        rightpower= Range.clip(rightpower,-1,1);

        leftfront.setPower(leftpower);
        rightfront.setPower(rightpower);
        leftback.setPower(leftpower);
        rightback.setPower(rightpower);
    }
}

