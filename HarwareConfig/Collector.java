package UltimateGoal_RobotTeam.HarwareConfig;

import OfflineCode.OfflineHW.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import UltimateGoal_RobotTeam.OpModes.BasicOpMode;

public class Collector {

    public double collectorPower = 0.0;

    /* Public OpMode members. */
    public DcMotor collectorWheel    = null;


    public Collector(BasicOpMode om, boolean tm)  {
        if(tm) {
            om.telemetry.addData("Status: ", "Called Constructor and TestMode is true");
            collectorWheel = new DcMotor();
            collectorWheel.setPower(0);
            collectorWheel.setDirection(DcMotorSimple.Direction.FORWARD);
            collectorWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        else {
            collectorWheel = om.hardwareMap.get(DcMotor.class, "motor_collector");

            collectorWheel.setPower(0);
            collectorWheel.setDirection(DcMotorSimple.Direction.FORWARD);
            collectorWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }

    }

    public void collectorControl(Gamepad g, BasicOpMode om) {

        if (g.dpad_left) {
            collectorPower = 0.0;
            collectorWheel.setPower(collectorPower);
            om.sleep(300);
        }

        // changed controls from gamepad up/down
        if (g.left_trigger > 0) {
            collectorPower += 0.10;
            collectorWheel.setPower(collectorPower);
            om.sleep(300);
        }
        if (g.right_trigger > 0) {
            collectorPower -= 0.10;
            collectorWheel.setPower(collectorPower);
            om.sleep(300);
        }

    }
}
