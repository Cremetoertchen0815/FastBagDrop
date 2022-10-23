package dhbw.group2.automata.peripherals;

import dhbw.group2.automata.FBDMachine;
import dhbw.group2.humans.Passenger;
import dhbw.group2.humans.identification.IPassengerIdentificator;
import dhbw.group2.plane.ticket.Ticket;

public class FingerprintScanner implements IPassengerIdentificator {
    @Override
    public Ticket identityPassenger(Passenger passenger, FBDMachine machine) {
        //TODO: Implement identification via fingerprint
        return null;
    }
}
