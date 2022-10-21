package dhbw.group2.automata;

import dhbw.group2.automata.peripherals.Display;
import dhbw.group2.humans.identification.IDCard;
import dhbw.group2.humans.identification.IDCardStatus;
import dhbw.group2.humans.*;
import dhbw.group2.humans.identification.Passport;
import dhbw.group2.plane.AirbusA350_900SeatMap;
import dhbw.group2.plane.IPlaneSeatMap;
import dhbw.group2.plane.boarding.Baggage;
import dhbw.group2.plane.boarding.BaggageTag;
import dhbw.group2.plane.ticket.BookingClass;
import dhbw.group2.plane.ticket.Ticket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class FBDMachine {
    private UUID serialNumber;
    private MachineManufacturer manufacturer;
    private StateEnum state;

    private final FBDSection[] sections;
    private final List<Passenger> leftQueue = new ArrayList<>();
    private final List<Passenger> rightQueue = new ArrayList<>();
    private final IPlaneSeatMap seatMap = new AirbusA350_900SeatMap();
    private final Map<String, Ticket> availableTickets = new HashMap<>();
    private final Map<Integer, BagBoardRecord> boardRecordMap = new HashMap<>();
    private final List<BaggageClassTuple> checkedInBaggage = new ArrayList<>();
    private final List<Passenger> checkedInPassengers = new ArrayList<>();
    private int boardRecordIndex = 0;

    public static final float weightLimit = 23;

    public FBDMachine() {
        sections = new FBDSection[] { new FBDSection(0), new FBDSection(1) };
    }

    public Map<String, Ticket> getAvailableTickets() {
        return availableTickets;
    }

    private boolean checkID(IDCard card, int section) {
        if (card.getStatus() == IDCardStatus.LOCKED) return false;
        var sect = sections[section];
        var correctPin = sect.reader.readPin(card, CentralConfig.getInstance().encryptionAlgorithm);
        for (int i = 0; i < 3; i++) {
            var enteredPin = sect.display.readPIN();
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

    private void checkIn(Passenger passenger, FBDSection section){
        passenger.receiveTicket(section.passportScan.ScanPassport(passenger.getPassport(), this));
        if (passenger.getTicket() == null) {
            section.display.printMessage("Sorry. No registered ticket found for " + passenger.getName() + " and flight LH2121");
            return;
        }


        //Find free seat
        var seat = seatMap.findSeat(passenger.getTicket().getBookingClass());
        if (seat == null) {
            section.display.printMessage("Sorry. No free seat found for flight LH2121");
            return;
        }
        seatMap.reserveSeat(passenger, seat);
        section.printerBoardingPass.setPlaneSeat(seat);

        section.display.printMessage("Proceed with check-in for flight LH2121?");
        section.display.showButtons(new String[] {"Yes", "No" });
        var answer = section.display.stallButtonSelection();
        if (answer == 1) {
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

            for(Object pass : rightQueue) {
                checkIn((Passenger)pass, sections[1]);
            }
    }

    public void baggageDrop(Passenger passenger, FBDSection section) {
        section.display.printMessage("Please enter number of checked-in baggage");
        var pieces = section.display.readInt();
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

            //Scan baggage tag
            if (!scanBaggageTag(section)) res = BagBoardResult.NOK;

            if (res == BagBoardResult.OK)
            {
                checkedInBaggage.add(new BaggageClassTuple(baggage, passenger.getTicket().getBookingClass()));
                checkedInPassengers.add(passenger);
            }

            //Note down record
            section.conveyor.setCurrentBaggage(null);
            boardRecordMap.put(boardRecordIndex++, new BagBoardRecord(Instant.now().getNano(), passenger.getTicket(), tag, res));
        }

        //Print boarding pass
        section.printerBoardingPass.setBaggageTags(baggageTags);
        var boardingPass = section.printerBoardingPass.print();
        passenger.receiveBoardingPass(boardingPass);

        //Print voucher
        if (passenger.getTicket().getBookingClass() == BookingClass.B) {
            section.printerVoucher.setVoucherType("Lounge");
            passenger.receiveVoucher(section.printerVoucher.print());
        } else if (passenger.getTicket().getBookingClass() == BookingClass.P) {
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

    public boolean scanBaggageTag(FBDSection section) {
        return section.conveyor.getCurrentBaggage() != null && section.conveyor.getCurrentBaggage().getTag() != null;
    }

    public void investigateExplosives(FederalPoliceOfficer responsibleOfficer, int section) {
        sections[section].conveyor.setCurrentBaggage(null);
        unlock(responsibleOfficer, section);
    }

    public void importFromCSV(Human actor) {

        if (!(actor instanceof ServiceAgent)) return;
        try{
            try (BufferedReader br = new BufferedReader(new FileReader("passenger_data.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    var ticket = new Ticket(values[9], values[0], BookingClass.valueOf(values[8].substring(0, 1)), values[1], values[2], values[4], values[7], Long.parseLong(values[6]), values[3], values[5]);
                    availableTickets.put(values[10], ticket);
                }
            }
        } catch( Exception ex) {
            throw new RuntimeException();
        }
    }

    public void warmSimulation() {
        //Creates the passengers waiting in line
        Baggage bag = null;
        try {
            bag = new Baggage(Files.readString(Path.of("specification/data/baggage_content.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (var pss : availableTickets.entrySet().stream().sorted(Comparator.comparingInt(x -> x.getValue().getBookingClass() == BookingClass.E ? 1 : 0)).toArray()) {
            var passenger = (Map.Entry<String, Ticket>)pss;
            var pp = new Passport(passenger.getKey());
            var ps = new Passenger(passenger.getValue().getName(), pp, new Baggage[] { bag });
            (passenger.getValue().getBookingClass() == BookingClass.B ? leftQueue : rightQueue).add(ps);
        }
    }

    public void analyseData(int section) {
        var weights = checkedInBaggage.stream().collect(Collectors.groupingBy(BaggageClassTuple::bookingClass, Collectors.summingDouble(x -> x.baggage().getWeight())));
        sections[section].display.printMessage(weights.toString());
        var pass = checkedInPassengers.stream().sorted(Comparator.comparing(x -> x.getName().split(" ")[1]))
                .collect(Collectors.groupingBy(x -> x.getTicket().getBookingClass(), Collectors.mapping(Passenger::toString, Collectors.toList())));
        sections[section].display.printMessage(pass.toString());
    }

    public void export() {

    }

}
