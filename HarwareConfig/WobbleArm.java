package UltimateGoal_RobotTeam.HarwareConfig;

import OfflineCode.OfflineHW.Servo;
import UltimateGoal_RobotTeam.OpModes.BasicOpMode;

public class WobbleArm {

    /* Public OpMode members. */
    public Servo wobbleGoalServo    = null;
    public WobbleArm(BasicOpMode om) {
        wobbleGoalServo = om.hardwareMap.get(Servo.class, "wobble_goal_servo");
    }
    public WobbleArm(BasicOpMode om, boolean tm)  {
        if(tm) {
            wobbleGoalServo = new Servo();
        }
        else {
            wobbleGoalServo = om.hardwareMap.get(Servo.class, "wobble_goal_servo");

        }
    }
public void setWobbleServoPos(double servoPos) {

    wobbleGoalServo.setPosition(servoPos);
  }


}
