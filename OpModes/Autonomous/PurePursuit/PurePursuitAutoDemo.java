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

		cons.DRIVE_POWER_LIMIT = 0.75;
		cons.STEERING_POWER_LIMIT = cons.DRIVE_POWER_LIMIT *0.65;//somewhere between 0.60 and 0.72
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

		robotUG = new HardwareRobotMulti(this, configArray,testModeActive);

		// Tel the robot that it's starting at (0,0) field center and angle is zero - facing EAST - Right
		robotUG.driveTrain.initIMU(this); //confgures IMU and sets initial heading to 0.0 degrees
		robotUG.driveTrain.robotX = 0;
		robotUG.driveTrain.robotY = 0;
		robotUG.driveTrain.robotLocation.setLocation(0,0,0);
	}

	@Override
	public void runCode() {

		ArrayList<PursuitPoint> pathPoints = new ArrayList<>();
		pathPoints= fieldPoints;
		// Always start path with where robot is
		pathPoints.add(new PursuitPoint(robotUG.driveTrain.robotX ,robotUG.driveTrain.robotY));

		pathPoints.add(new PursuitPoint(10,0));
		pathPoints.add(new PursuitPoint(20,10));
		pathPoints.add(new PursuitPoint(30,30));
		pathPoints.add(new PursuitPoint(65,30));
		pathPoints.add(new PursuitPoint(65,65));
		pathPoints.add(new PursuitPoint(10,65));
		pathPoints.add(new PursuitPoint(0,15));


		for(int h=0;h<pathPoints.size()-1;h++) {
			lines.add(new PursuitLines(pathPoints.get(h).x, pathPoints.get(h).y, pathPoints.get(h+1).x, pathPoints.get(h+1).y));
		}

                Billy.drivePursuit(pathPoints, this, "Drive multi-lines");

	}

}
