package UltimateGoal_RobotTeam.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import OfflineCode.OfflineHW.Telemetry;
import OfflineCode.OfflineOpModes.OpModeParamFunctions;
import UltimateGoal_RobotTeam.HarwareConfig.HardwareRobot;
import UltimateGoal_RobotTeam.HarwareConfig.HardwareRobotMulti;
import UltimateGoal_RobotTeam.Parameters.Constants;

public class BasicOpMode extends LinearOpMode {

    public HardwareRobot Billy = new HardwareRobot();// call using Billy.(for hardware or angle unwrap method)
    public HardwareRobotMulti robotUG = null; // Adds new multi-HW element robot to OpMode
        // Need to configure robotUG in specific OpModes that use it
    public Constants cons = new Constants();// call using cons.(constant DRIVE_POWER_LIMIT etc.)

    //********************UPDATED 12/27/19 for OpMpde HashMap *********************************
    public OpModeParamFunctions ompf = new OpModeParamFunctions();
    public boolean loadFile = true;
    public String fileName = "AndroidHashMapFile.txt";
    public String fileNameEdited = "AndroidHashMapFileEdited.txt";
    //********************UPDATED 12/27/19 for OpMpde HashMap *********************************

    public boolean fileWasRead = true;
    public String hashMapFile = "HashMapFile.txt";
    public String autoOptionFile = "AutoOptionFile.txt";
    public int selected = 0;

    public double DeltaH = 0;
    public double currentH = 0;

    public boolean testModeActive = false;

    public Telemetry telemetry = new Telemetry();

    public ElapsedTime runtime = new ElapsedTime(); //create a counter for elapsed time

    public double timeStep = 135;//determined a fixed time step (in milliseconds) so that faster speeds will show shorter time to distance
    //Above needed for TestMode


    public BasicOpMode() {

    }

    @Override
    public void runOpMode() {

    }

    public void pressAToContinue() {

        telemetry.addLine("**********************");
        telemetry.addLine("Press A to continue");
        telemetry.update();
        while (!gamepad1.a && opModeIsActive()) {

            idle();
        }
        sleep(300);
    }

    public void readOrWriteHashMap() {

        cons.readFromPhone(hashMapFile, this);
        telemetry.addData("Existing File Was Read?","%s", fileWasRead);

        if (!fileWasRead) {

            cons.defineParameters();
            cons.writeToPhone(hashMapFile, this);

            cons.readFromPhone(hashMapFile, this);//Can be eliminated because HashMap exists in constants from defineParameters
            telemetry.addData("Created File, File Was Read?","%s", fileWasRead);
            //No telemetry.update();
            //Note differences in Pancho's Constants_Pw
        }

        cons.initParameters();
    }

    public void readOrWriteHashMapOffline() {

        cons.readFromFile(hashMapFile, this);
        telemetry.addData("Existing File Was Read?","%s", fileWasRead);
        telemetry.update();
        if (!fileWasRead) {

            cons.defineParameters();
            cons.writeToFile(hashMapFile, this);

            cons.readFromFile(hashMapFile, this);//Can be eliminated because HashMap exists in constants from defineParameters
            telemetry.addData("Created File, File Was Read?","%s", fileWasRead);
            telemetry.update();
        }
        // ******************** ADDED initPARAMETERS ****************
        cons.initParameters();
        //Note differences in Pancho's Constants_Pw implementation
        // ******************** ADDED initPARAMETERS ****************

    }

//    public void readOrWriteHashMapAO() {
//
//        cons.readFromPhoneAO(hashMapFile, this);
//        telemetry.addData("Existing File Was Read?","%s", fileWasRead);
//
//        if (!fileWasRead) {
//
//            cons.defineAutoOptions();
//            cons.writeToPhoneAO(hashMapFile, this);
//
//            cons.readFromPhoneAO(hashMapFile, this);
//            telemetry.addData("Created File, File Was Read?","%s", fileWasRead);
//        }
//    }

}