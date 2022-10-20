public class Application {
    public static void main(String... args) {
        SmokeDetector smokeDetector = new SmokeDetector();
        FireDepartment fireDepartment = new FireDepartment();

        smokeDetector.addListener(fireDepartment);
        smokeDetector.falseAlarm();
    }
}