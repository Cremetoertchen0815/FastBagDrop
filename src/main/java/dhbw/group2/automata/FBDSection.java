package dhbw.group2.automata;

import dhbw.group2.*;
import dhbw.group2.peripherals.*;

public class FBDSection {
    public IPrinter printerBoardingPass;
    public IPrinter printerBaggageTag;
    public IPrinter printerVoucher;
    public PassportScanner passportScan;
    public Sensor sensor;
    public Display display;
    public IDCardReader reader;
    public BaggageScanner baggageScan;
    public ConveyorBelt conveyor;
}
