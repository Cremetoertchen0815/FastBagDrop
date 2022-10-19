package dhbw.group2.humans;

import dhbw.group2.Passport;
import dhbw.group2.ticket.Ticket;

public class Passenger extends Human {
    private Passport passport;
    private Ticket ticket;

    public Passport getPassport() {
        return passport;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
