package us.newberg.bullet;

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

        leftback.direction.setDirection(REVERSE);
        rightback.direction.setDirection(REVERSE);
        }
@Override
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
          /*
                break;
                case done:
                // Code...
                break;
                default:

                break;
            }

          switch(state) {
              leftfront.setpower(leftY)
              rightfront.setpower(right)
              if (currentTime> fowardtime) {
              state= State.sleeping;
              }
              time.reset();
              }
          break;
          */
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

