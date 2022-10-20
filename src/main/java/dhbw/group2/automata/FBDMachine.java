package dhbw.group2.automata;

import dhbw.group2.IDCard;
import dhbw.group2.IDCardStatus;
import dhbw.group2.humans.*;
import dhbw.group2.plane.AirbusA350_900SeatMap;
import dhbw.group2.plane.IPlaneSeatMap;

import java.util.List;

public class FBDMachine {
    private String serialNumber;
    private String manufacturer;
    private StateEnum state;

    private final FBDSection[] sections;
    private List<Passenger> leftQueue;
    private List<Passenger> rightQueue;
    private IPlaneSeatMap seatMap = new AirbusA350_900SeatMap();

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

    private void setState(StateEnum nuState, Human actor, int section) {
        //Set new state
        state = nuState;

        //Update buttons
        var dsp = sections[section].display;
        switch (state) {
            case ON:
                dsp.showButton("Export", () -> export());
                dsp.showButton("Shutdown", () -> shutdown(actor, section));
                break;
            case OFF:
                dsp.showButton("Startup", () -> startup(actor, section));
                break;
            case LOCKED:
                dsp.showButton("Unlock", () -> unlock(actor, section));
                break;
        }
    }

    public void startup(Human actor, int section) {
        if (!(actor instanceof ServiceAgent) || !checkID(((Employee)actor).getCard(), section) || state != StateEnum.OFF) return;
        setState(StateEnum.ON, actor, section);
    }

    public void shutdown(Human actor, int section) {
        if (!(actor instanceof ServiceAgent) || !checkID(((Employee)actor).getCard(), section) || state != StateEnum.ON) return;
        setState(StateEnum.OFF, actor, section);
    }

    public void unlock(Human actor, int section) {
        if (!(actor instanceof FederalPoliceOfficer) || !checkID(((Employee)actor).getCard(), section) || state != StateEnum.LOCKED) return;
        state = StateEnum.ON;
    }

    public void importFromCSV(Human actor) {
        if (!(actor instanceof ServiceAgent)) return;
    }

    private void checkIn(Passenger passenger, FBDSection section){
        var ticket = section.passportScan.ScanPassport(passenger.getPassport(), this);
        if (ticket == null) {
            section.display.printMessage("Sorry. No registered ticket found for " + passenger.getName() + " and flight LH2121");
            return;
        }
        section.display.printMessage("Proceed with check-in for flight LH2121?");
        section.display.showButton("No", () -> section.display.printMessage("Check-In cancelled by user"));
        section.display.showButton("Yes", () -> {
            section.display.printMessage("Please enter number of checked-in baggage");
            var pieces = Integer.parseInt(section.display.readInput());
        });
    }

    public void checkIn() {
        for(var pass : leftQueue) {
            checkIn(pass, sections[0]);
        }
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
