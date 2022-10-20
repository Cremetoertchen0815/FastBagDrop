package dhbw.group2.automata;

import dhbw.group2.automata.peripherals.*;

public class FBDSection {
    public FBDSection(int section) {
        this.section = section;
    }
    public final BoardingPassPrinter printerBoardingPass = new BoardingPassPrinter();
    public final BaggageTagPrinter printerBaggageTag = new BaggageTagPrinter();
    public final VoucherPrinter printerVoucher = new VoucherPrinter();
    public final PassportScanner passportScan = new PassportScanner();
    public final Display display = new Display();
    public final IDCardReader reader = new IDCardReader();
    public final BaggageScanner baggageScan = new BaggageScanner();
    public final ConveyorBelt conveyor = new ConveyorBelt();
    public final int section;
}
