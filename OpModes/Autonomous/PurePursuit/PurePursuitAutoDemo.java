package UltimateGoal_RobotTeam.OpModes.Autonomous.PurePursuit;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.ArrayList;

import UltimateGoal_RobotTeam.HarwareConfig.HardwareRobotMulti;
import UltimateGoal_RobotTeam.OpModes.Autonomous.BasicAuto;
import UltimateGoal_RobotTeam.Utilities.PursuitLines;
import UltimateGoal_RobotTeam.Utilities.PursuitPoint;

@Autonomous(name="Pure Pursuit Demo", group="Autonomous")

 public class PurePursuitAutoDemo extends BasicAuto {
	@Override
	public void runOpMode() {

		initialize();

		waitForStart();

		runCode();

	}

	@Override
	public void initialize() {
		runtime.reset();

		// Set low speed for initial demo
		cons.DRIVE_POWER_LIMIT = 0.3;
		cons.STEERING_POWER_LIMIT = cons.DRIVE_POWER_LIMIT  * 1.0;// scale back power limits as necessary
		cons.STEERING_POWER_GAIN = 0.1;

		// configure the robot needed - for this demo only need DriveTrain
		// configArray has True or False values for each subsystem HW element
		//
		/** configArray is arranged as
		 * [0] = DriveTrain
		 * [1] = Shooter
		 * [2] = Conveyor
		 * [3] = WobbleArm
		 * [4] = Collector
		 * items that are 1 = true will be configured to the robot
		 */
		// HW ELEMENTS *****************    DriveTrain  Shooter  Conveyor	WobbleArm	Collector
		boolean[] configArray = new boolean[]{ true, 	false, 	false, 		false, 		false};

		testModeActive = false;//configure real robot
		robotUG = new HardwareRobotMulti(this, configArray,testModeActive);

		// Tel the robot that it's starting at (0,0) field center and angle is zero - facing EAST - Right
		robotUG.driveTrain.initIMU(this); //confgures IMU and sets initial heading to 0.0 degrees
		robotUG.driveTrain.robotX = 0;
		robotUG.driveTrain.robotY = 0;
		robotUG.driveTrain.robotFieldLocation.setLocation(0,0,0);

	}

	@Override
	public void runCode() {

		ArrayList<PursuitPoint> pathPoints = new ArrayList<>();
		pathPoints= fieldPoints;
		// Always start path with where robot is
		pathPoints.add(new PursuitPoint(robotUG.driveTrain.robotX ,robotUG.driveTrain.robotY));
		pathPoints.add(new PursuitPoint(24,24));
		pathPoints.add(new PursuitPoint(0,48));
		pathPoints.add(new PursuitPoint(-24,24));
		pathPoints.add(new PursuitPoint(-6,6));


		for(int h=0;h<pathPoints.size()-1;h++) {
			lines.add(new PursuitLines(pathPoints.get(h).x, pathPoints.get(h).y, pathPoints.get(h+1).x, pathPoints.get(h+1).y));
		}

                robotUG.driveTrain.drivePursuit(pathPoints, this, "Drive multi-lines");

	}

}
