package dhbw.group2.automata;

import dhbw.group2.automata.peripherals.*;
import dhbw.group2.humans.identification.IPassengerIdentificator;

public class FBDSection {
    public final BoardingPassPrinter printerBoardingPass = new BoardingPassPrinter();
    public final BaggageTagPrinter printerBaggageTag = new BaggageTagPrinter();
    public final VoucherPrinter printerVoucher = new VoucherPrinter();
    public final IPassengerIdentificator identityScanner = new PassportScanner();
    public final Display display = new Display();
    public final IDCardReader reader = new IDCardReader();
    public final BaggageScanner baggageScan = new BaggageScanner();
    public final ConveyorBelt conveyor = new ConveyorBelt();
    public final int section;
    public FBDSection(int section) {
        this.section = section;
    }
}
