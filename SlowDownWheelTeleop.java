package us.newberg.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore

public class Driving extends OpMode()
        DcMotor leftfront;
        DcMotor leftback;
        DcMotor rightfront;
        DcMotor rightback;
        ElapsedTime time;
        Servo leftArm;
        Servo rightArm;

final double LEFT_ARM_OPEN = .5;
final double LEFT_ARM_CLOSED = 0.0;
final double RIGHT_ARM_OPEN = 0.5;
final double RIGHT_ARM_CLOSED = 0.0;

        DcMotor ArmTilt;
        DcMotor ArmLift;
final double PowerForward = .5;
final double PowerBack = -.5;
final double ArmStop = 0;


static final double fowardtime=0.010
static final double sleeping= 0.010
        int count = 0

enum State {fowarddrive,sleeping,done};
State state;

@Override
public void init() {
        leftfront= hardwareMap.dcmotor.get("lf");
        rightfront= hardwareMap.dcmotor.get("rf);
        leftback= hardwareMap.dcmotor.get("lb");
        rightback= hardwareMap.dcmotor.get("rb");
        leftY= -gamepad.leftstick.y;
        rightY= -gamepad.leftstick.y;

        rightfront.direction.setDirection(REVERSE);
        rightback.direction.setDirection(REVERSE);
        ArmTilt = hardwareMap.dcMotor.get("ArmTilt");
        ArmLift = hardwareMap.dcMotor.get("ArmLift");
        }

public void loop() {
        double currentTime= time.time();

        switch (state) {
        case fowarddrive:
        leftfront.setpower(leftY)
        rightfront.setpower(right)
        break;
        case sleeping:
        if (currentTime> fowardtime) {
        state = State.sleeping;
        }
        time.reset
        }
        break;

        case(sleeping)
        leftfront.setpower(0)
        rightfront.setpower(0)
        if(currentTime> sleeping) {
        state= State.fowardtime;
        }
        time.reset();
        }
        break;
        }
        leftback.setpower(leftY)
        rightback.setpower(rightY)
        {
        if(gamepad2.x){
        leftArm.setPosition(LEFT_ARM_OPEN);
        //rightArm.setPosition(RIGHT_ARM_OPEN);
        }
        if(gamepad2.b){
        //leftArm.setPosition(LEFT_ARM_CLOSED);
        rightArm.setPosition(RIGHT_ARM_OPEN);
        }
        if(gamepad2.y){
        leftArm.setPosition(LEFT_ARM_CLOSED);
        rightArm.setPosition(RIGHT_ARM_CLOSED);
        }
        if(gamepad2.right_trigger>.01){
        ArmTilt.setPower(PowerForward);
        }else{
        ArmTilt.setPower(ArmStop);
        }
        if(gamepad2.left_trigger>.01){
        ArmLift.setPower(PowerForward);
        }else{
        ArmLift.setPower(ArmStop);
        }
        if(gamepad2.right_bumper){
        ArmTilt.setPower(PowerBack);
        }else{
        ArmTilt.setPower(ArmStop);
        }
        if(gamepad2.left_bumper){
        ArmLift.setPower(PowerBack);
        }else{
        ArmLift.setPower(ArmStop);
        }
        }




