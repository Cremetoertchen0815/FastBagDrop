package dhbw.group2.humans;

import dhbw.group2.humans.identification.Passport;
import dhbw.group2.plane.boarding.Baggage;
import dhbw.group2.plane.boarding.BoardingPass;
import dhbw.group2.plane.boarding.Voucher;
import dhbw.group2.plane.ticket.Ticket;

public class Passenger extends Human {
    private Passport passport;
    private Ticket ticket;
    private Baggage[] baggage;
    private BoardingPass boardingPass;
    private Voucher voucher;


    public Passport getPassport() {
        return passport;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Baggage[] getBaggage() {
        return baggage;
    }

    public void receiveBoardingPass(BoardingPass boardingPass) {
        this.boardingPass = boardingPass;
    }

    public void receiveVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
}
