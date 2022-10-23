package dhbw.group2.humans.identification;

import dhbw.group2.automata.FBDMachine;
import dhbw.group2.humans.Passenger;
import dhbw.group2.plane.ticket.Ticket;

public interface IPassengerIdentificator {
    Ticket identityPassenger(Passenger passenger, FBDMachine machine);
}
