package dhbw.group2.automata;

import dhbw.group2.humans.identification.IDCard;
import dhbw.group2.humans.identification.IDCardStatus;
import dhbw.group2.humans.*;
import dhbw.group2.plane.AirbusA350_900SeatMap;
import dhbw.group2.plane.IPlaneSeatMap;
import dhbw.group2.plane.boarding.Baggage;
import dhbw.group2.plane.boarding.BaggageTag;
import dhbw.group2.plane.ticket.BookingClass;

import java.time.Instant;
import java.util.*;

public class FBDMachine {
    private UUID serialNumber;
    private MachineManufacturer manufacturer;
    private StateEnum state;

    private final FBDSection[] sections;
    private List<Passenger> leftQueue;
    private List<Passenger> rightQueue;
    private final IPlaneSeatMap seatMap = new AirbusA350_900SeatMap();
    private final Map<Integer, BagBoardRecord> boardRecordMap = new HashMap<>();
    private int boardRecordIndex = 0;

    public static final float weightLimit = 23;

    public FBDMachine() {
        sections = new FBDSection[] { new FBDSection(0), new FBDSection(1) };
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

        baggageDrop(passenger, section);
    }

    private void beep() {

    }

    public void checkIn() {
        for(var pass : leftQueue) {
            checkIn(pass, sections[0]);
        }
    }

    public void baggageDrop(Passenger passenger, FBDSection section) {
        section.display.printMessage("Please enter number of checked-in baggage");
        var pieces = Integer.parseInt(section.display.readInput());
        var baggageTags = new ArrayList<BaggageTag>();
        //Tag baggage
        for (var baggage : passenger.getBaggage()) {
            section.conveyor.setCurrentBaggage(baggage);
            var res = BagBoardResult.NOK;
            BaggageTag tag = null;

            //Check baggage
            if (determineWeight(section) > weightLimit)
            {
                beep();
                section.display.printMessage("Baggage exceeds weight limit of 23 kg");
            } else if (scanBaggage(section))
            {
                state = StateEnum.LOCKED;
                FederalPolice.getInstance().reportForInvestigation(this, section.section);
            } else
            {
                //Baggage is alright, so print tag
                tag = section.printerBaggageTag.print();
                baggage.attachTag(tag);
                baggageTags.add(tag);
                res = BagBoardResult.OK;
            }

            section.conveyor.setCurrentBaggage(null);
            boardRecordMap.put(boardRecordIndex++, new BagBoardRecord(Instant.now().getNano(), passenger.getTicket(), tag, res));
        }

        //Find free seat
        var seat = seatMap.findSeat(passenger.getTicket().leftSection.bookingClass);
        seatMap.reserveSeat(passenger, seat);

        //Print boarding pass
        section.printerBoardingPass.setBaggageTags(baggageTags);
        section.printerBoardingPass.setPlaneSeat(seat);
        var boardingPass = section.printerBoardingPass.print();

        //Print voucher
        if (passenger.getTicket().leftSection.bookingClass == BookingClass.B) {
            section.printerVoucher.setVoucherType("Lounge");
            passenger.receiveVoucher(section.printerVoucher.print());
        } else if (passenger.getTicket().leftSection.bookingClass == BookingClass.P) {
            section.printerVoucher.setVoucherType("AC/DC");
            passenger.receiveVoucher(section.printerVoucher.print());
        }
    }

    public float determineWeight(FBDSection section) {
        return section.conveyor.getMeasuredWeight();
    }

    public boolean scanBaggage(FBDSection section) {
        return section.baggageScan.scanBaggageForExplosives(section.conveyor.getCurrentBaggage());
    }

    public void scanBaggageTag() {

    }

    public void investigateExplosives(FederalPoliceOfficer responsibleOfficer, int section) {
        sections[section].conveyor.setCurrentBaggage(null);
        unlock(responsibleOfficer, section);
    }

    public void analyseData() {

    }

    public void export() {

    }

}
