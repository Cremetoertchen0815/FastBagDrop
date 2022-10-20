package dhbw.group2.automata;

import dhbw.group2.humans.identification.IDCard;
import dhbw.group2.humans.identification.IDCardStatus;
import dhbw.group2.humans.*;
import dhbw.group2.plane.AirbusA350_900SeatMap;
import dhbw.group2.plane.IPlaneSeatMap;

import java.util.List;
import java.util.UUID;

public class FBDMachine {
    private UUID serialNumber;
    private MachineManufacturer manufacturer;
    private StateEnum state;

    private final FBDSection[] sections;
    private List<Passenger> leftQueue;
    private List<Passenger> rightQueue;
    private IPlaneSeatMap seatMap = new AirbusA350_900SeatMap();

    public FBDMachine() {
        sections = new FBDSection[] { new FBDSection(), new FBDSection() };
    }

    private boolean checkID(IDCard card, int section) {
        if (card.getStatus() == IDCardStatus.LOCKED) return false;
        var sect = sections[section];
        var correctPin = sect.reader.readPin(card, CentralConfig.getInstance().encryptionAlgorithm);
        for (int i = 0; i < 3; i++) {
            var enteredPin = sect.display.readInput();
            if (correctPin.equals(enteredPin)) return true;
            sect.display.printMessage("Wrong PIN!");
        }
        card.setStatus(IDCardStatus.LOCKED);
        sect.display.printMessage("Wrong PIN entered three times in a row! Card locked!");
        return false;
    }

    private void setState(StateEnum nuState, Human actor, int section) {
        //Set new state
        state = nuState;

        //Update buttons
        var dsp = sections[section].display;
        switch (state) {
            case ON -> {
                dsp.showButtonAsync("Export", this::export);
                dsp.showButtonAsync("Shutdown", () -> shutdown(actor, section));
            }
            case OFF -> dsp.showButtonAsync("Startup", () -> startup(actor, section));
            case LOCKED -> dsp.showButtonAsync("Unlock", () -> unlock(actor, section));
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
        section.display.showButtons(new String[] { "No", "Yes" });
        var answer = section.display.stallButtonSelection();
        if (answer == 0) {
            section.display.printMessage("Check-In cancelled by user");
            return;
        }

        section.display.printMessage("Please enter number of checked-in baggage");
        var pieces = Integer.parseInt(section.display.readInput());
        section.conveyor.currentBaggage = passenger.getBaggage();

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
