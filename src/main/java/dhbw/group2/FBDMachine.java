package dhbw.group2;

public class FBDMachine {
    private String serialNumber;
    private String manufacturer;
    private StateEnum state;
    private FBDSection[] sections;

    public FBDMachine() {
        sections = new FBDSection[] { new FBDSection(), new FBDSection() };
    }

    public void startup() {

    }

    public void shutdown() {

    }

    public void unlock() {

    }

    public CSVItem importFromCSV() {
        return null;
    }

    public void checkIn() {

    }

    public void analyseData() {

    }

    public void export() {

    }

    public float determineWeight() {
        return 0;
    }

    public void scanBaggageTag() {

    }

    public void investigateExplosives(FederalPoliceOfficer responsibleOfficer) {

    }

}
