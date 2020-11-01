package UltimateGoal_RobotTeam.HarwareConfig;

import com.qualcomm.robotcore.hardware.CRServo;

import UltimateGoal_RobotTeam.OpModes.BasicOpMode;

public class Conveyor {

    /* Public OpMode members. */
    public CRServo conveyorLeft = null;
    public CRServo conveyorRight = null;
    public Conveyor(BasicOpMode om) {
        conveyorLeft = om.hardwareMap.get(CRServo.class, "servo_conveyorL");
        conveyorRight = om.hardwareMap.get(CRServo.class, "servo_conveyorR");

    }
    public Conveyor(BasicOpMode om, boolean tm) {

        if(tm){
//            conveyorLeft = new CRServo();
////            conveyorRight = new CRServo();
        }
        else{
            conveyorLeft = om.hardwareMap.get(CRServo.class, "servo_conveyorL");
            conveyorRight = om.hardwareMap.get(CRServo.class, "servo_conveyorR");

        }
    }

}
