package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
public class OfficialTeleOp extends OpMode{
    private DcMotor leftfront;
    private DcMotor leftback;
    private DcMotor rightfront;
    private DcMotor rightback;
    private DcMotor ArmTiltLeft;
    private DcMotor ArmTiltRight;
    private DcMotor ArmLift;
    private Servo SkirtServoR;
    private Servo SkirtServoL;
    private Servo LowerArmLock;
    private Servo UpperArmLock;
    private Servo Zipline;
    float PowerForward = (float) .1;
    float PowerBack = (float) -.1;
    final static float LIFT_POWER_FORWARD = (float) 1;
    final static float LIFT_POWER_BACK = -1;
    final static double SERVO_SKIRT_UPR = .54;
    final static double SERVO_SKIRT_DOWNR= .38;
    final static double SERVO_SKIRT_UPL = .54;
    final static double SERVO_SKIRT_DOWNL= .38;
    final static double LOWER_ARM_LEFT = .6;
    final static double LOWER_ARM_RIGHT = .4;
    final static double LOWER_ARM_OFF = .5;
    final static double UPPER_ARM_LOCKED = .026;
    final static double UPPER_ARM_UNLOCKED = .1;
    final static double ZIPLINE_UP = .6;
    final static double ZIPLINE_DOWN = .23 ;
    @Override
    public void init(){
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
        ArmTiltRight = hardwareMap.dcMotor.get("ArmTiltRight");
        ArmTiltLeft = hardwareMap.dcMotor.get("ArmTiltLeft");
        ArmLift = hardwareMap.dcMotor.get("ArmLift");
        ArmLift.setDirection(DcMotor.Direction.REVERSE);
        ArmTiltLeft.setDirection(DcMotor.Direction.REVERSE);//sterling is retarted
        SkirtServoR = hardwareMap.servo.get("SkirtservoR");
        SkirtServoL = hardwareMap.servo.get("SkirtservoL");
        LowerArmLock = hardwareMap.servo.get("LowerArmLock");
        LowerArmLock.setDirection(Servo.Direction.REVERSE);
        UpperArmLock = hardwareMap.servo.get("UpperArmLock");
        Zipline = hardwareMap.servo.get("Zipline");
        SkirtServoR.setPosition(SERVO_SKIRT_DOWNR);
        SkirtServoL.setPosition(SERVO_SKIRT_DOWNL);
        UpperArmLock.setPosition(UPPER_ARM_UNLOCKED);
    }
    @Override
    public void loop(){
        float leftY = gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        double backleftpower = (leftY ) * .4;
        double backrightpower = (rightY ) * .4;
        double frontleftpower = (leftY * -.9 * .4);
        double frontrightpower = (rightY * -.9)* .4;

        leftfront.setPower(frontleftpower);
        rightfront.setPower(frontrightpower);
        leftback.setPower(backleftpower);
        rightback.setPower(backrightpower);

        PowerForward = (gamepad2.a)? -1 : (float) -0.2;
        PowerBack = (gamepad2.a)? 1 : (float) 0.2;

        float armPower = (gamepad2.left_trigger >= 0.05) ? PowerForward : (gamepad2.right_trigger >= 0.05) ? PowerBack : 0;
        ArmTiltLeft.setPower(armPower);
        ArmTiltRight.setPower(armPower);

        float extendPower = (gamepad2.left_bumper) ? LIFT_POWER_FORWARD : (gamepad2.right_bumper) ? LIFT_POWER_BACK : 0;
        ArmLift.setPower(extendPower);

        if (gamepad1.dpad_up){
            SkirtServoR.setPosition(SERVO_SKIRT_UPR);
            SkirtServoL.setPosition(SERVO_SKIRT_UPL);

        }
        if (gamepad1.dpad_down){
            SkirtServoR.setPosition(SERVO_SKIRT_DOWNR);
            SkirtServoL.setPosition(SERVO_SKIRT_DOWNL);
        }

        LowerArmLock.setPosition( gamepad1.a ? LOWER_ARM_RIGHT :
                gamepad1.b ? LOWER_ARM_LEFT : LOWER_ARM_OFF);

        if(gamepad1.x){
            UpperArmLock.setPosition(UPPER_ARM_LOCKED);
        }
        if(gamepad1.y){
            UpperArmLock.setPosition(UPPER_ARM_UNLOCKED);
        }

        if(gamepad2.x){
            Zipline.setPosition(ZIPLINE_DOWN);
        }
        if(gamepad2.y){
            Zipline.setPosition(ZIPLINE_UP);
        }
    }
}
