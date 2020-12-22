package UltimateGoal_RobotTeam.HarwareConfig;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;

import UltimateGoal_RobotTeam.OpModes.BasicOpMode;
import UltimateGoal_RobotTeam.OpModes.TeleOp.BasicTeleOp;

public class Conveyor {
    /* Public Variables */
    public double conveyor_Power = 0;

    /* Public OpMode members. */
    public CRServo conveyorLeft = null;
    public CRServo conveyorRight = null;
    public Conveyor(BasicOpMode om) {
        conveyorLeft = om.hardwareMap.get(CRServo.class, "servo_conveyorL");
        conveyorRight = om.hardwareMap.get(CRServo.class, "servo_conveyorR");

    }
    public Conveyor(BasicOpMode om, boolean tm) {

        if(tm){
            // Need new CRServo OfflineHW class
//            conveyorLeft = new CRServo();
//            conveyorRight = new CRServo();
        }
        else{
            conveyorLeft = om.hardwareMap.get(CRServo.class, "servo_conveyorL");
            conveyorRight = om.hardwareMap.get(CRServo.class, "servo_conveyorR");

        }
    }
    public void ConveyorControl(Gamepad gamepad, BasicTeleOp om) {

        if (gamepad.a) {
            conveyor_Power = 1;
            conveyorLeft.setPower(conveyor_Power);
            conveyorRight.setPower(-conveyor_Power);
            om.sleep(300);
        }
        if (gamepad.y) {
            conveyor_Power = -1;
            conveyorLeft.setPower(conveyor_Power);
            conveyorRight.setPower(-conveyor_Power);
            om.sleep(300);
        }
        if (gamepad.b) {
            conveyor_Power = 0;
            conveyorLeft.setPower(conveyor_Power);
            conveyorRight.setPower(conveyor_Power);
            om.sleep(300);
        }
    }
}
