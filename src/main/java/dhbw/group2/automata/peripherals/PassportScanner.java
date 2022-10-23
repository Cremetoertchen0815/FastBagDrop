package dhbw.group2.automata.peripherals;

import dhbw.group2.automata.FBDMachine;
import dhbw.group2.humans.Passenger;
import dhbw.group2.humans.identification.IPassengerIdentificator;
import dhbw.group2.plane.ticket.Ticket;

public class PassportScanner implements IPassengerIdentificator {

    @Override
    public Ticket identityPassenger(Passenger passenger, FBDMachine machine) {
        return machine.getAvailableTickets().get(passenger.getPassport().getId());
    }
}
