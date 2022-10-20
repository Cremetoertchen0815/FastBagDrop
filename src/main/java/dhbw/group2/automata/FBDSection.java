package dhbw.group2.automata;

import dhbw.group2.automata.peripherals.*;

public class FBDSection {
    public IPrinter printerBoardingPass;
    public IPrinter printerBaggageTag;
    public IPrinter printerVoucher;
    public PassportScanner passportScan;
    public Display display;
    public IDCardReader reader;
    public BaggageScanner baggageScan;
    public ConveyorBelt conveyor;
}
