package dhbw.group2;

import dhbw.group2.humans.*;

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
        var correctPin = sect.reader.readPin(card, CentralConfig.getInstance().encryptionAlgorithm);
        for (int i = 0; i < 3; i++) {
            var enteredPin = sect.display.readInput();
            if (correctPin.equals(enteredPin)) return true;
            sect.display.printMessage("Wrong PIN!");
        }
        card.status = IDCardStatus.LOCKED;
        sect.display.printMessage("Wrong PIN entered three times in a row! Card locked!");
        return false;
    }

    public void startup(Human actor, int section) {
        if (!(actor instanceof ServiceAgent) || !checkID(((Employee)actor).getCard(), section) || state != StateEnum.OFF) return;
        state = StateEnum.ON;
    }

    public void shutdown(Human actor, int section) {
        if (!(actor instanceof ServiceAgent) || !checkID(((Employee)actor).getCard(), section) || state != StateEnum.ON) return;
        state = StateEnum.OFF;
    }

    public void unlock(Human actor, int section) {
        if (!(actor instanceof FederalPoliceOfficer) || !checkID(((Employee)actor).getCard(), section) || state != StateEnum.LOCKED) return;
        state = StateEnum.ON;
    }

    public void importFromCSV() {

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
