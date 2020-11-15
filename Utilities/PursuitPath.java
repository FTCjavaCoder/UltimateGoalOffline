package UltimateGoal_RobotTeam.Utilities;

import java.util.ArrayList;

public class PursuitPath {

    /**
     * PursuitPath holds an array of points that define the driving path for the robot
     * The methods within the class are used to define basic 2D geometries and append them to the
     * arraylist of points.  The robot hardware class HardwareBilly implements drivePursuit as the
     * means for the robot to follow the points.
     * <p>
     * --- Are these methods really needed since not invoked? ----
     * Recommend that the ArrayList fieldPoints is defined in BasicAuto - DONE!!
     * Recommend that the method to findPursuitPoint is added to RobotHardware - DONE!!
     * Recommend COMMENTING out this file and then DELETING after testing
     */
//
    public static ArrayList<PursuitLines> fieldLines = new ArrayList();
//    public static ArrayList<PursuitPoint> fieldPoints = new ArrayList();
//    public  ArrayList<PursuitLines> tempLocations = new ArrayList();
//    private final double TIME_STEP = 0.1;
//    private final int TOTAL_POINTS = (int) Math.round(30.0 / TIME_STEP);

    public PursuitPath() {
        //empty constructor to instantiate
    }

    //    public void appendPoints() {
//        fieldLines.addAll(tempLocations);
//        tempLocations.clear();
//    }
//    public void defineLine(double x1, double y1,double x2, double y2){
//        PursuitLines L = new PursuitLines(x1, y1, x2, y2);
//        fieldPoints.add(new PursuitPoint(x1,y1));
//        fieldPoints.add(new PursuitPoint(x2,y2));
//
//        fieldLines.add(L);
//
//    }
//
    public static ArrayList<PursuitPoint> defineRectangle(double x1, double y1, double w, double h, double tol) {
        //Define rectangle from start x1, y1 to end at the same spot - tol
        ArrayList<PursuitPoint> fieldPoints = new ArrayList();

        //w and l can be negative or positive

        fieldPoints.add(new PursuitPoint(x1,y1));
        fieldPoints.add(new PursuitPoint(x1+w,y1));
        fieldPoints.add(new PursuitPoint(x1+w,y1+h));
        fieldPoints.add(new PursuitPoint(x1,y1+h));
        fieldPoints.add(new PursuitPoint(x1,y1-tol));
        return fieldPoints;
    }

    public static ArrayList<PursuitPoint> defineArc(PursuitPoint center, double radius, double startAngleRad, double includedAngleRad, int points, pathDirection pd) {
        //Follow a circle of radius, offset by startAngleRad radians, for a total angle of includedAngleRad,
        ArrayList<PursuitPoint> fieldPoints = new ArrayList();
        //  divided into points
        double maxPoints = points;
        double scale = 1.0;
        if (pd.equals(pathDirection.NEGATIVE)) {
            scale = -1.0;
        }
        for (int i = 0; i < points; i++) {
            double angle = scale * (i / (maxPoints-1)) * includedAngleRad;
            double x = radius * Math.cos(angle - startAngleRad);
            double y = radius * Math.sin(angle - startAngleRad);
            fieldPoints.add(new PursuitPoint(x + center.x, y + center.y));
        }
        return fieldPoints;
    }

    //
    public enum pathDirection {POSITIVE, NEGATIVE}

    ;

}
