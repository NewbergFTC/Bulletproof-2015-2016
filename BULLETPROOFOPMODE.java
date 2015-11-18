package us.newberg.bullet;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Util;
import java.lang.ref.Reference;
public abstract class BulletProofOpMode extends LinearOpMode {
    private DcMotor leftfront;
    private DcMotor leftback;
    private DcMotor rightfront;
    private DcMotor rightback;
    private Servo rightArm;
    private Servo leftArm;
    private DcMotorController leftController;
    //Put motors for arms in as wel
    final double LEFT_ARM_OPEN = .5;//need to reverse one of the servos
    final double LEFT_ARM_CLOSED = 0.0;
    final double RIGHT_ARM_OPEN = 0.5;
    final double RIGHT_ARM_CLOSED = 0.0;
    final double PowerForward = .5;
    final double PowerBack = -.5;
    final double ArmStop = 0;
    final double CLICKS_PER_REVOLUTION = 280;
    final double WHEEL_CIRCUM_FRONT = 7;
    final double WHEEL_CIRCUM_BACK = 3.875;
    final float ENCODER_TICKS_PER_REVOLUTION = 280;
    final double WHEEL_CIRCUMFERENCE = 7 * Math.PI;
    final float DISTANCE_FOR_TURN = 1;
    final int LEFT = 1;
    final int RIGHT = -1;
    protected final void Init() {   //Sets up motor configurations, etc.
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
        rightback.setDirection(DcMotor.Direction.REVERSE);
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        leftArm = hardwareMap.servo.get("leftArm");
        rightArm = hardwareMap.servo.get("rightArm");
        rightArm.setPosition(RIGHT_ARM_CLOSED);
        leftArm.setPosition(LEFT_ARM_CLOSED);
        leftController = hardwareMap.dcMotorController.get("leftCon");
        leftfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        leftfront.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int GetTicks() { //gets the current value of the encoder
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        try{
            waitOneFullHardwareCycle();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        int position = leftfront.getCurrentPosition();

        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        try{
            waitOneFullHardwareCycle();
        }
        catch(InterruptedException e){
           e.printStackTrace();
        }
        return position;
    }
    public void motorDrive(float motorPower){  //Driving forward
        leftfront.setPower(motorPower);
        rightfront.setPower(motorPower);
        leftback.setPower(motorPower);
        rightback.setPower(motorPower);
    }
    public void StopDriveMotors(){  //Stopping the robot
        leftfront.setPower(0);
        rightfront.setPower(0);
        leftback.setPower(0);
        rightback.setPower(0);
    }
    public void motorTurn(float motorPower , int direction){ //Turning robot
        leftfront.setPower(motorPower * direction);
        rightfront.setPower(motorPower * direction);
        leftback.setPower(motorPower * direction);
        rightback.setPower(motorPower * direction);
    }
    public void GoForward(float inches, float power){  //Driving forward using encoders
        float position = GetTicks();
        float ticks = (float) ((ENCODER_TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE) * inches);
        float goal = position + ticks;
        telemetry.addData("Target Ticks", String.valueOf(goal));
        telemetry.addData("Intial Ticks" , position);
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //if (_timer != null)
            //_timer.Terminate();

        //_timer = new DriveTimer(this, Util.RoundReal(inches * 0.9));
        while ((Math.abs(position)) < goal){
            //Drive(power, power);
            motorDrive(power);
            position = GetTicks();
        }
        StopDriveMotors();
        telemetry.addData("Leaving GoForward Program", toString());
        telemetry.addData("Encoder value = %f" , position);
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //while (position >= target) {
            //Drive(-power, -power);
            //moveMotors(-power);
            //position = GetTicks();
        //}
       // _timer.Terminate();
    }

    public void Turn(int direction , float percentof90 , float power){
        float position = GetTicks();
        float turnDistance = DISTANCE_FOR_TURN * percentof90;
        //float ticks = (float) ((ENCODER_TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE) * inches);
        float goal = position + turnDistance;
        telemetry.addData("Target Ticks", String.valueOf(goal));
        telemetry.addData("Intial Ticks" , position);
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //if (_timer != null)
        //_timer.Terminate();
        //_timer = new DriveTimer(this, Util.RoundReal(inches * 0.9));
        while ((Math.abs(position)) < goal){
            //Drive(power, power);
           motorTurn(power , direction);
            position = GetTicks();
        }
        StopDriveMotors();
        telemetry.addData("Leaving Turn Program", toString());
        telemetry.addData("Encoder value = %f" , position);
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //while (position >= target) {
        //Drive(-power, -power);
        //moveMotors(-power);
        //position = GetTicks();
        //}
        // _timer.Terminate();
    }

    public abstract void Initialize();
    public abstract void Update();
    @Override
    public void runOpMode() throws InterruptedException {
        Init();
        // Call subclass init
        Initialize();
        waitForStart();
        while (opModeIsActive()) {
            Update();
            waitForNextHardwareCycle();
        }
    }
}
