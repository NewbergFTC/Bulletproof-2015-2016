package us.newberg.bullet;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class RevOpMode extends LinearOpMode
{
    // Drive motors
    protected DcMotor _frontLeftMotor;
    protected DcMotor _frontRightMotor;
    protected DcMotor _backLeftMotor;
    protected DcMotor _backRightMotor;

    protected DcMotorController _frontController;
    protected DcMotorController _backController;

    // Zip-line stick things
    protected Servo _leftStick;
    protected Servo _rightStick;

    protected DriveTimer _timer;

    public RevOpMode()
    {
        super();

        _timer = new DriveTimer(this, 0);
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        Init();

        // Call subclass init
        Initialize();

        waitForStart();

        while (opModeIsActive())
        {
            Update();

            waitForNextHardwareCycle();
        }
    } public int GetTicks()
{
    _frontController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
    try
    {
        waitOneFullHardwareCycle();
    } catch (InterruptedException e)
    {
        e.printStackTrace();
    }

    int position = _frontLeftMotor.getCurrentPosition();

    _frontController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
    try
    {
        waitOneFullHardwareCycle();
    } catch (InterruptedException e)
    {
        e.printStackTrace();
    }

    return position;
}

    public abstract void Initialize();
    public abstract void Update();

    protected final void Init()
    {
        _frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        _frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        _backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        _backRightMotor = hardwareMap.dcMotor.get("backRight");

        _frontController = hardwareMap.dcMotorController.get("frontCon");
        _backController = hardwareMap.dcMotorController.get("backCon");

        //_leftStick = hardwareMap.servo.get("leftStick");
        //_rightStick = hardwareMap.servo.get("rightStick");

        SetDriveSpeed(0);

        _frontLeftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }



    public void Drive(float leftPower, float rightPower)
    {
        SetFrontLeftSpeed(-leftPower);
        SetBackLeftSpeed(-leftPower);
        SetFrontRightSpeed(rightPower);
        SetBackRightSpeed(rightPower);
    }

    public void AutoDrive(float power, float inches)
    {
        float position = GetTicks();

        float ticks = (Reference.ENCODER_TICKS_PER_REVOLUTION / Reference.WHEEL_CIRCUMFERENCE) * inches;
        float target = position + ticks;

        telemetry.addData("Target Ticks", String.valueOf(target));

        if (_timer != null)
            _timer.Terminate();

        _timer = new DriveTimer(this, Util.RoundReal(inches * 0.9));

        while (position <= target)
        {
            Drive(power, power);
            position = GetTicks();
        }

        while (position >= target)
        {
            Drive(-power, -power);
            position = GetTicks();
        }

        _timer.Terminate();
    }

    public void TimedDrive(float leftPower, float rightPower, long millis)
    {
        if (_timer != null)
            _timer.Terminate();

        _timer = new DriveTimer(this, millis);

        _timer.start();
        Drive(leftPower, rightPower);

        try
        {
            _timer.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void Turn(float degree, float power)
    {
        float position = GetTicks();
        float target = position + degree;

        telemetry.addData("Target Ticks", String.valueOf(target));

        if (_timer != null)
            _timer.Terminate();

        // TODO(Peacock): Real wait time here
        _timer = new DriveTimer(this, 20);

        while (position <= target)
        {
            Drive(power, -power);
            position = GetTicks();
        }

        while (position >= target)
        {
            Drive(-power, power);
            position = GetTicks();
        }

        _timer.Terminate();
    }

    /*public void SetLeftStick(double position)
    {
        _leftStick.setPosition(position);
    }*/

    /*public void SetRightStick(double position)
    {
        _rightStick.setPosition(position);
    }*/

    public void SetDriveSpeed(double speed)
    {
        SetFrontLeftSpeed(speed);
        SetFrontRightSpeed(speed);
        SetBackLeftSpeed(speed);
        SetBackRightSpeed(speed);
    }

    public void SetFrontLeftSpeed(double speed)
    {
        double spd = Util.Clampd(speed, -1.0, 1.0);

        _frontLeftMotor.setPower(spd);
    }

    public void SetFrontRightSpeed(double speed)
    {
        double spd = Util.Clampd(speed, -1.0, 1.0);

        _frontRightMotor.setPower(spd);
    }

    public void SetBackLeftSpeed(double speed)
    {
        double spd = Util.Clampd(speed, -1.0, 1.0);

        _backLeftMotor.setPower(spd);
    }

    public void SetBackRightSpeed(double speed)
    {
        double spd = Util.Clampd(speed, -1.0, 1.0);

        _backRightMotor.setPower(spd);
    }
}
