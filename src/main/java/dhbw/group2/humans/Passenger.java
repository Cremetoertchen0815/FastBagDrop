package dhbw.group2.humans;

import dhbw.group2.humans.identification.Passport;
import dhbw.group2.plane.ticket.Ticket;

public class Passenger extends Human {
    private Passport passport;
    private Ticket ticket;
    private Baggage baggage;

    public Passport getPassport() {
        return passport;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Baggage getBaggage() {
        return baggage;
    }
}
