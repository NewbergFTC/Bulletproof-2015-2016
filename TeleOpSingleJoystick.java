 us.newberg.bullet;

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
        float leftYOne =Range.clip(gamepad1.left_stick_y, -1.0f, 1.0f);
        float leftXfront =Range.clip(gamepad1.left_stick_x, -1.0f, 1.0f);
        //float rightYOne = Range,clip(gamepad1.right_stick_y, -1.0f, 1.0f);
        //float rightXOne = Range.clip(gamepad1.right_stick_x, -1.0f, 1.0f);

        // Calculate tank drive speed for each side
        float leftPower = Scale(leftYOne);
        float rightPower = Scale(leftYOne);

        float leftX = Scale(leftXfront);

        if (leftX > 0)
        {
            leftPower += leftX;
            rightPower -= rightPower > 0 ? leftX * 2 : leftX;
        }
        else if (leftX < 0)
        {
            leftPower += leftPower > 0 ? leftX * 2 : leftX;
            rightPower -= leftX;
        }

        leftfront.setPower(leftPower);
        rightfront.setPower(rightPower);

    }

    // Based off the K9TankDrive scale function
    public float Scale(float value)
    {
        float[] scaleArray = { 0.0f, 0.05f, 0.09f, 0.10f, 0.12f, 0.15f, 0.18f, 0.24f,
                0.30f, 0.36f, 0.43f, 0.50f, 0.60f, 0.72f, 0.85f, 0.95f, 1.00f };

       // int index = Math.abs(value *(scaleArray.length - 1));
        int index = (int)(Math.abs(value *(scaleArray.length - 1)) + 0.5f);

        if (index > scaleArray.length - 1)
            index = scaleArray.length - 1;

        boolean negative = value <= 0;
        return negative ? -scaleArray[index] : scaleArray[index];
    }
}
