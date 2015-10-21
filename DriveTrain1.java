package us.newberg.bullet;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.util.Range;


/**
 * Created by btaka on 10/9/2015
 */

public class DriveTrain extends OpMode{
    DcMotor leftfront;
    DcMotor leftback;
    DcMotor rightfront;
    DcMotor rightback;


    @Override
    public void init() {
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
    }

    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        // float leftX= -gamepad1.left_ stick_x;
        //float rightX= -gamepad1.right_stick_x;

        /*
        {
            if (gamepad1.a)
                leftfront.setPower(leftY);
                rightfront.setPower(rightY);
                leftback.setPower(leftY);
                rightback.setPower(rightY);
        }

        {
            if (gamepad1.b)
            leftback.setPower(leftY);
            rightback.setPower(rightY);
            leftfront.setPower(0);
            rightfront.setPower(0);
        }

            */
            leftfront.setPower(leftY);
            rightfront.setPower(rightY);
            leftback.setPower(leftY);
            rightback.setPower(rightY);

        }
    }
