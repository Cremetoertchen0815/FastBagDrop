public class FireDepartment implements ISmokeDetectorListener {
    public void fireIgnited(String location) {
        turnOut(location);
    }

    public void turnOut(String location) {
        System.out.println("turned out : " + location);
    }
}