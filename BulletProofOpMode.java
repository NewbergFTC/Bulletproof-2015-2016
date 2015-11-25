package us.newberg.bullet;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class BulletProofOpMode extends LinearOpMode {
    public DcMotor leftfront;
    public DcMotor leftback;
    public DcMotor rightfront;
    public DcMotor rightback;
    public Servo rightArm;
    public Servo leftArm;
    public DcMotorController leftController;
    public DcMotorController rightController;
    //Put motors for arms in as well
    final double LEFT_ARM_OPEN = .5;//need to reverse one of the servos
    final double LEFT_ARM_CLOSED = 0.0;
    final double RIGHT_ARM_OPEN = 0.5;
    final double RIGHT_ARM_CLOSED = 0.0;
    final double PowerForward = .5;
    final double PowerBack = -.5;
    final double ArmStop = 0;
    final float DISTANCE_FOR_TURN = 1;
    final double GEAR_ONE_TEETH = 16;
    final double GEAR_TWO_TEETH = 30;
    final double CLICKS_PER_REVOLUTION = 1120;
    final double WHEEL_CIRCUMFERENCE = 7 * Math.PI;
    final double DISTANCE = 25;
    final double MOTOR_POWER = .5;
    final double GOAL_DIFFERENCE = 100;
    final double DISTANCE_FACTOR = 0.89;
    final double TURN_FACTOR = 2.22;
    final double RIGHT = 1;
    final double LEFT = -1;
    final double MOTOR_POWER_LEFT = 0.6;
    final double MOTOR_POWER_RIGHT = 0.3;
    float count  = 0;

    protected final void Init() throws InterruptedException {   //Sets up motor configurations, etc.
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
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        waitCycle(6);

        leftfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        //_frontRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        waitCycle(6);

        leftfront.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //_frontRight.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitCycle(6);
    }

    public int GetTicks() throws InterruptedException { //gets the current value of the encoder
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);

        waitCycle(6);

        int position = leftfront.getCurrentPosition();

        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

       waitCycle(6);

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

    public void resetEncoders(){
        leftfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void ResetEncoders() throws InterruptedException {
        waitCycle(6);
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitCycle(6);
        leftfront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitCycle(6);
        leftfront.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitCycle(6);
    }

    public void waitCycle(int count) throws InterruptedException {
        for (int i = count; i > 0; i--) {
            waitOneFullHardwareCycle();
        }
    }

    public void motorPowerRampUp() throws InterruptedException {
        for(int i = 3; i > 0; i--){
            leftfront.setPower(MOTOR_POWER_LEFT / i);
            leftback.setPower(MOTOR_POWER_LEFT / i);
            rightfront.setPower(MOTOR_POWER_RIGHT / i);
            rightback.setPower(MOTOR_POWER_RIGHT / i);
            sleep(250);
        }
    }

    public abstract void initialize();

    public abstract void Update();

    public void goForward(int inches, double leftPower , double rightPower) throws InterruptedException {
        double goal = (GEAR_TWO_TEETH/GEAR_ONE_TEETH)*(CLICKS_PER_REVOLUTION/WHEEL_CIRCUMFERENCE)*inches;//Get ticks for the distance needed to travel
        goal = (goal - GOAL_DIFFERENCE) * DISTANCE_FACTOR;
        telemetry.addData("Goal0:", String.valueOf(goal));
        ResetEncoders();//Puts program in read only inside the routine
        telemetry.addData("Goal1:", String.valueOf(goal));

        waitCycle(6);

        int ticks ;
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        waitCycle(6);

        //_backLeft.setPower(leftPower);
        //_frontLeft.setPower(leftPower);
        //_frontRight.setPower(.1);
        //_backRight.setPower(rightPower);
        motorPowerRampUp();

        waitCycle(6);

        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);

        waitCycle(6);

        ticks = leftfront.getCurrentPosition();
        while(ticks <= goal){
            ticks = leftfront.getCurrentPosition();
        }
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        waitCycle(6);

        leftfront.setPower(0);
        leftback.setPower(0);
        rightfront.setPower(0);
        rightback.setPower(0);
    }

    public void goTurn(int degrees, double direction, double motor_power) throws InterruptedException {
        double leftpower;
        int ticks ;
        double rightpower;
        double goal = (GEAR_TWO_TEETH/GEAR_ONE_TEETH)*(CLICKS_PER_REVOLUTION)*degrees/360;//Get ticks for the distance needed to travel
        goal = goal * TURN_FACTOR;
        ResetEncoders();

        waitCycle(6);

        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        waitCycle(6);

        leftpower = direction * motor_power;
        rightpower = -1 *direction * motor_power;
        leftfront.setPower(leftpower);
        leftback.setPower(leftpower);
        rightfront.setPower(rightpower);
        rightback.setPower(rightpower);

        waitCycle(6);

        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);

        waitCycle(6);

        ticks = leftfront.getCurrentPosition();
        while(Math.abs(ticks) <= goal){
            ticks = leftfront.getCurrentPosition();
        }
        leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        waitCycle(6);

        leftfront.setPower(0);
        leftback.setPower(0);
        rightfront.setPower(0);
        rightback.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Init();
        // Call subclass init
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            Update();
            waitForNextHardwareCycle();
        }
    }
}

