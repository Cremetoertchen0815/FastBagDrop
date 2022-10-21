package dhbw.group2.plane.ticket;

public class Ticket {
    private final TicketSection leftSection;
    private final AdvancedTicketSection rightSection;

    public Ticket(String name, String flight, BookingClass bookingClass, String source, String destination, String date, String id, long sequence, String gate, String boardingTime) {
        leftSection = new TicketSection(name, flight, bookingClass, source, destination, date, id, sequence);
        rightSection = new AdvancedTicketSection(name, flight, bookingClass, source, destination, date, id, sequence, gate, boardingTime);
    }

    public String getGate() {
        return rightSection.gate;
    }

    public String getBoardingTime() {
        return rightSection.boardingTime;
    }

    public String getName() {
        return leftSection.name;
    }

    public String getFlight() {
        return leftSection.flight;
    }

    public BookingClass getBookingClass() {
        return leftSection.bookingClass;
    }

    public String getSource() {
        return leftSection.source;
    }

    public String getDestination() {
        return leftSection.destination;
    }

    public String getDate() {
        return leftSection.date;
    }

    public String getId() {
        return leftSection.id;
    }

    public long getSequence() {
        return leftSection.sequence;
    }
}
