package UltimateGoal_RobotTeam.HarwareConfig;

import OfflineCode.OfflineHW.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import OfflineCode.OfflineHW.Servo;
import UltimateGoal_RobotTeam.OpModes.BasicOpMode;

public class WobbleArm {

    /* Public OpMode members */
    public Servo wobbleGoalServo    = null;
    public DcMotor wobbleGoalArm    = null;

    /* Public variables  */

    public double wobbleGoalPos = 0.5;// undecided values
    public double wobbleGrabInc = 0.1;
    public double wobbleGrabPos = 0.5;
    public double wobbleReleasePos = 0;
    public int wobbleArmTarget = 0;
    public int wobbleArmTargetAngle = 0;
    public int armDegInc = 1;
    public int armDegIncBig = 25;
    public double armPower = 0.25;
    public double powerInc = 0.05;
    public double armPowerHold = 0.7;
    public double armGearRatio = 24.0/15.0;  //Coach Note: updated value

    public int c = 0; //temporary variable use to prevent us from choosing how we want to progress before we drop the wobble goal

    public WobbleArm(BasicOpMode om, boolean tm)  {
        if(tm) {
            wobbleGoalServo = new Servo();
            wobbleGoalArm = new DcMotor();
            wobbleGoalArm.setPower(0);
            wobbleGoalArm.setTargetPosition(0);
            wobbleGoalArm.setDirection(DcMotorSimple.Direction.FORWARD);
            wobbleGoalArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wobbleGoalArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            wobbleGoalArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wobbleGoalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else {
            wobbleGoalArm = om.hardwareMap.get(DcMotor.class, "motor_wobble_goal");
            wobbleGoalServo = om.hardwareMap.get(Servo.class, "wobble_goal_servo");

            wobbleGoalArm.setPower(0);
            wobbleGoalArm.setTargetPosition(0);
            wobbleGoalArm.setDirection(DcMotorSimple.Direction.FORWARD);
            wobbleGoalArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wobbleGoalArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            wobbleGoalArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wobbleGoalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
    public void setWobbleMotorPower(Gamepad gamepad, BasicOpMode om) {

        if (gamepad.left_bumper) {
            armPower -= powerInc;
            wobbleGoalArm.setPower(armPower);
            om.sleep(300);
        }
        if (gamepad.right_bumper) {
            armPower += powerInc;
            wobbleGoalArm.setPower(armPower);
            om.sleep(300);
        }

//        if (gamepad.b) {
//            armPower = 0.0;
//            wobbleGoalArm.setPower(armPower);
//            om.sleep(300);
//        }

    }

    public void changeWobbleMotorVariable(Gamepad gamepad, BasicOpMode om) {

        if (gamepad.dpad_up) {
            wobbleArmTargetAngle += armDegInc;
            wobbleArmTarget = (int) Math.round(wobbleArmTargetAngle * (om.cons.DEGREES_TO_COUNTS_60_1 * armGearRatio));
            om.sleep(300);
        }
        if (gamepad.dpad_down) {
            wobbleArmTargetAngle -= armDegInc;
            wobbleArmTarget = (int) Math.round(wobbleArmTargetAngle * (om.cons.DEGREES_TO_COUNTS_60_1 * armGearRatio));
            om.sleep(300);
        }
        if (gamepad.x) {
            wobbleArmTargetAngle += armDegIncBig;
            wobbleArmTarget = (int) Math.round(wobbleArmTargetAngle * (om.cons.DEGREES_TO_COUNTS_60_1 * armGearRatio));
            om.sleep(300);
        }
        if (gamepad.b) {
            wobbleArmTargetAngle -= armDegIncBig;
            wobbleArmTarget = (int) Math.round(wobbleArmTargetAngle * (om.cons.DEGREES_TO_COUNTS_60_1 * armGearRatio));
            om.sleep(300);
        }

    }

    public void autoWobbleMotorVariable(Gamepad gamepad, BasicOpMode om) {

        if (gamepad.y) {
            wobbleArmTargetAngle = 110;
            wobbleArmTarget = (int) Math.round(wobbleArmTargetAngle * (om.cons.DEGREES_TO_COUNTS_60_1 * armGearRatio));
            om.sleep(300);
        }
        if (gamepad.a) {
            wobbleArmTargetAngle = 70;
            wobbleArmTarget = (int) Math.round(wobbleArmTargetAngle * (om.cons.DEGREES_TO_COUNTS_60_1 * armGearRatio));
            om.sleep(300);
        }

        c = 1;//Coach note: what's this?  replaced by "pressAToContinue"?

    }

    public void setWobbleMotorPosition(Gamepad gamepad, BasicOpMode om) {

        if (gamepad.dpad_right) {
            wobbleGoalArm.setTargetPosition(wobbleArmTarget);
            om.sleep(300);
        }

    }

    public void setWobbleServoPos(Gamepad gamepad, BasicOpMode om) {

        if (gamepad.y) {

            wobbleGoalPos += wobbleGrabInc;
            wobbleGoalServo.setPosition(wobbleGoalPos);
            om.sleep(250);
        }
        if (gamepad.a) {

            wobbleGoalPos -= wobbleGrabInc;
            wobbleGoalServo.setPosition(wobbleGoalPos);
            om.sleep(250);
        }

    }

    public void pickUpWobble(BasicOpMode om) {

/** Coach Note: pickUpWobble for Auto or TeleOp or both?
 * May want a method for Auto to pick of the 2nd wobble goal if there's time
 * May want a 2nd method to enable just moving the arm for pick up in TeleOp
 * Also need a 3rd method to drop wobbleGoal over the wall in TeleOp end game
 */


    }

    public void dropWobble(BasicOpMode om) {

        wobbleGoalArm.setPower(0.5);

        wobbleArmTargetAngle = 30;// Coach Note: define these values in the constants so they can be used throughout methods
        wobbleArmTarget = (int) Math.round(wobbleArmTargetAngle * (om.cons.DEGREES_TO_COUNTS_60_1 * armGearRatio));
        wobbleGoalArm.setTargetPosition(wobbleArmTarget);
/** Coach note: has this been tested?  There might need to be a loop to wait for the motor to reach
* the target position
*/
        wobbleGoalServo.setPosition(0.5); // Coach Note: define these values in the constants so they can be used throughout methods

        wobbleArmTargetAngle = 100; // Coach Note: define these values in the constants so they can be used throughout methods
        wobbleArmTarget = (int) Math.round(wobbleArmTargetAngle * (om.cons.DEGREES_TO_COUNTS_60_1 * armGearRatio));
        wobbleGoalArm.setTargetPosition(wobbleArmTarget);

        wobbleGoalServo.setPosition(0); // Coach Note: define these values in the constants so they can be used throughout methods

    }

    /** Coach Note: Add a method that returns the wobbleGoalArm position in degrees of arm rotation not motor
     *
     */

}
