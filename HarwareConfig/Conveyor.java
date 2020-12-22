package UltimateGoal_RobotTeam.HarwareConfig;

import OfflineCode.OfflineHW.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;

import UltimateGoal_RobotTeam.OpModes.BasicOpMode;
import UltimateGoal_RobotTeam.OpModes.TeleOp.BasicTeleOp;

public class Conveyor {
    /* Public Variables */
    public double conveyor_Power = 0;

    /* Public OpMode members. */
    public CRServo conveyorLeft = null;
    public CRServo conveyorRight = null;

    public Conveyor(BasicOpMode om, boolean tm) {

        if(tm){
            om.telemetry.addData("Conveyor", " Initializing  ...");
            om.telemetry.update();
            conveyorLeft = new CRServo();
            conveyorRight = new CRServo();

            conveyorLeft.timeStep = om.timeStep;
            conveyorRight.timeStep = om.timeStep;

            om.telemetry.addLine("\t\t... Initialization COMPLETE");
            om.telemetry.update();
        }
        else{
            om.telemetry.addData("Conveyor", " Initializing  ...");
            om.telemetry.update();
            conveyorLeft = om.hardwareMap.get(CRServo.class, "servo_conveyorL");
            conveyorRight = om.hardwareMap.get(CRServo.class, "servo_conveyorR");

            om.telemetry.addLine("\t\t... Initialization COMPLETE");
            om.telemetry.update();

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
    /* -- COACH NOTE: need to add method for autonomous conveyor control
     * -- method required to set power like "y" button
     * -- also need a stop method to keep rings from continually advancing
     *  - made a shutdown method for all hardware
     *
     */
    public void setMotion(motionType mt){
        switch(mt){
            case UP:
                conveyor_Power = -1.0;//pull rings up
                break;
            case DOWN:
                conveyor_Power = 1.0;//push rings down
                break;
            case OFF:
                conveyor_Power = 0.0;// stop
                break;
        }
        conveyorLeft.setPower(conveyor_Power);
        conveyorRight.setPower(-conveyor_Power);
    }
    public enum motionType {UP, DOWN, OFF};
    public void shutdown(){
        conveyorLeft.setPower(0.0);
        conveyorRight.setPower(0.0);
    }
}
