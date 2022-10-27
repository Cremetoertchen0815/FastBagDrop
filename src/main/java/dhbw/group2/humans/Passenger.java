package dhbw.group2.humans;

import dhbw.group2.humans.identification.Passport;
import dhbw.group2.plane.boarding.Baggage;
import dhbw.group2.plane.boarding.IBoardingPass;
import dhbw.group2.plane.boarding.Voucher;
import dhbw.group2.plane.ticket.Ticket;

public class Passenger extends Human {
    private final Passport passport;
    private final Baggage[] baggage;
    private Ticket ticket;
    private IBoardingPass boardingPass;
    private Voucher voucher;

    public Passenger(String name, Passport passport, Baggage[] baggage) {
        this.name = name;
        this.passport = passport;
        this.baggage = baggage;
    }


    public Passport getPassport() {
        return passport;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Baggage[] getBaggage() {
        return baggage;
    }

    public IBoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void receiveBoardingPass(IBoardingPass boardingPass) {
        this.boardingPass = boardingPass;
    }

    public void receiveVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public void receiveTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "(" + name + "|" + ticket.getBookingClass().toString() + "|" + boardingPass.getSeat().toString() + "|" + baggage.length + ")";
    }
}
