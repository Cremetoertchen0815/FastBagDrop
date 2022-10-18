package dhbw.group2;

import dhbw.group2.humans.FederalPoliceOfficer;
import dhbw.group2.humans.Human;
import dhbw.group2.humans.Passenger;
import dhbw.group2.humans.ServiceAgent;

import java.util.List;

public class FBDMachine {
    private String serialNumber;
    private String manufacturer;
    private StateEnum state;

    private FBDSection[] sections;
    private List<Passenger> leftQueue;
    private List<Passenger> rightQueue;

    public FBDMachine() {
        sections = new FBDSection[] { new FBDSection(), new FBDSection() };
    }

    private boolean checkID(IDCard card, int section) {
        if (card.status == IDCardStatus.LOCKED) return false;
        var sect = sections[section];
        var correctPin = sect.reader.readPin(card, encryptionAlgorithm);
        var enteredPin = sect.display.readInput();
        return correctPin.equals(enteredPin);
    }

    public void startup(Human actor, int section) {
        if (!(actor instanceof ServiceAgent) || !checkID(((ServiceAgent) actor).getCard(), section) || state != StateEnum.OFF) return;
        state = StateEnum.ON;
    }

    public void shutdown(Human actor, int section) {
        if (!(actor instanceof ServiceAgent) || !checkID(((ServiceAgent) actor).getCard(), section) || state != StateEnum.ON) return;
        state = StateEnum.OFF;
    }

    public void unlock(Human actor) {

    }

    public CSVItem importFromCSV() {
        return null;
    }

    public void checkIn(Human actor) {

    }

    public void BaggageDrop(Human actor) {

    }

    public float determineWeight() {
        return 0;
    }

    public void scanBaggage() {

    }

    public void scanBaggageTag() {

    }

    public void investigateExplosives(FederalPoliceOfficer responsibleOfficer) {

    }

    public void analyseData() {

    }

    public void export() {

    }

}
