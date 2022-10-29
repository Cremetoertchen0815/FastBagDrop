import dhbw.group2.automata.FBDMachine;
import dhbw.group2.automata.StateEnum;
import dhbw.group2.humans.FederalPoliceOfficer;
import dhbw.group2.humans.Passenger;
import dhbw.group2.humans.ServiceAgent;
import dhbw.group2.humans.identification.IDCard;
import dhbw.group2.humans.identification.IDCardStatus;
import dhbw.group2.humans.identification.Passport;
import dhbw.group2.plane.PlaneSeat;
import dhbw.group2.plane.boarding.Baggage;
import dhbw.group2.plane.boarding.BaggageTag;
import dhbw.group2.plane.boarding.OnlineBoardingPass;
import dhbw.group2.plane.ticket.BookingClass;
import dhbw.group2.plane.ticket.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestApp {
    private FBDMachine auto;
    private ServiceAgent agent;

    private FBDMachine testAuto;
    private ServiceAgent testAgent;
    private FederalPoliceOfficer testFPO;
    private Passenger testP;
    private IDCard card;

    @BeforeEach
    public void setUp() {
        auto = new FBDMachine();
        agent = new ServiceAgent();
        testFPO = new FederalPoliceOfficer();
        testP = new Passenger("Test", new Passport("TESTID"), null);

    }

    @Test
    public void testInitQueues() {
        assertNotNull(auto.getRightQueue());
        assertNotNull(auto.getLeftQueue());
        for (Passenger p : auto.getLeftQueue()) {
            assertNotNull(p);
            for (Baggage b : p.getBaggage()) {
                assertNotNull(b);
            }
        }
        for (Passenger p : auto.getRightQueue()) {
            assertNotNull(p);
            for (Baggage b : p.getBaggage()) {
                assertNotNull(b);
            }
        }
    }

    @Test
    public void testStart_Shutdown() {
        testAuto = new FBDMachine();
        testAgent = new ServiceAgent();

        //test startup() -> state can only be changed, if human is service agent
        testAuto.startup(testFPO, 0);
        assertSame(StateEnum.OFF, testAuto.getState());

        testAuto.startup(testP, 0);
        assertSame(StateEnum.OFF, testAuto.getState());

        testAuto.startup(testAgent, 0);
        assertSame(StateEnum.ON, testAuto.getState());


        //test shutdown() -> same as above
        testAuto.shutdown(testFPO, 0);
        assertSame(StateEnum.ON, testAuto.getState());

        testAuto.shutdown(testP, 0);
        assertSame(StateEnum.ON, testAuto.getState());

        testAuto.shutdown(testAgent, 0);
        assertSame(StateEnum.OFF, testAuto.getState());

    }

    @Test
    public void testData_Export() {
        FBDMachine auto_mach = new FBDMachine();
        Passport passport = new Passport("TESTID");
        Baggage b = new Baggage("content");
        b.setWeight(5);
        Baggage[] blist = {b};
        BookingClass bC = BookingClass.E;

        Ticket t = new Ticket(
                "Test",
                "Test",
                BookingClass.B,
                "Test",
                "Test",
                "Test",
                "Test",
                0,
                "Test",
                "Test"
        );

        Passenger p = new Passenger("TEST TEST", passport, blist);
        ServiceAgent ag_ent = new ServiceAgent();
        auto_mach.addTicket(p, t);

        auto_mach.checkIn(p, auto_mach.getSections()[0]);
//        auto_mach.baggageDrop(p, auto_mach.getSections()[0]);

        //test data analytics
        auto_mach.analyseData(ag_ent, 0);

        auto_mach.analyseData(agent, 0);
        assertEquals(5.0, auto_mach.getWeights().get(p.getTicket().getBookingClass()));

        //export
        auto_mach.export(ag_ent);
        assertEquals(1, auto_mach.getCountExports());
    }

    @Test
    public void testStartUpShutdown() {

        //method startup(Human actor, int section) works correctly if the following requirements are correct
        //1. actor is instance of service agent (already checked, with the test before)
        //2. section can either be 0 or 1 (0 already checked, with the test before)
        //3. if status is ON and startup() method is executed the status will not change
        //4. id status must be valid (not locked)


        //requirement 2
        auto.startup(agent, 1);
        assertSame(StateEnum.ON, auto.getState());

        //requirement 3
        auto.startup(agent, 1);
        assertSame(StateEnum.ON, auto.getState());

        auto.setState(StateEnum.OFF);

        //requirement 4
        agent.getCard().setStatus(IDCardStatus.LOCKED);
        auto.startup(agent, 0);
        assertSame(StateEnum.OFF, auto.getState());


        //method shutdown(Human actor, int section) works the same way as startup(Human actor, int section) method
        //same requirements:
        //1. actor is instance of service agent (already checked, with the test before)
        //2. section can either be 0 or 1 (0 already checked, with the test before)
        //3. if status is OFF and shutdown() method is executed the status will not change
        //4. id status must be valid (not locked)

        //requirement 2
        auto.shutdown(agent, 1);
        assertSame(StateEnum.OFF, auto.getState());

        //requirement 3
        auto.shutdown(agent, 0);
        assertSame(StateEnum.OFF, auto.getState());

        auto.setState(StateEnum.ON);

        //requirement 4
        agent.getCard().setStatus(IDCardStatus.LOCKED);
        auto.shutdown(agent, 0);
        assertSame(StateEnum.ON, auto.getState());

    }

    @Test
    public void testBoardingPass() {
        OnlineBoardingPass bp = new OnlineBoardingPass();
        BaggageTag tag = new BaggageTag();
        BaggageTag[] tagList = {tag};
        PlaneSeat p = new PlaneSeat(0, 'x');


        bp.setDestination("TESTDEST");
        bp.setBaggageTags(Arrays.stream(tagList).toList());
        bp.setFlight("TestFlight");
        bp.setSource("TESTSOURCE");
        bp.setPlaneSeat(p);

        assertEquals("TESTDEST", bp.getDestination());
        for (BaggageTag bt : tagList) {
            assertEquals(tag, bt);
        }
        assertEquals("TestFlight", bp.getFlight());
        assertEquals("TESTSOURCE", bp.getSource());
        assertEquals(p, bp.getSeat());


    }

    @Test
    public void testBaggageTag() {
        BaggageTag tag = new BaggageTag();
        assertEquals(0, tag.getId());
    }

    @Test
    public void testCheckInBaggageDropNorm() {
        FBDMachine auto_mach = new FBDMachine();
        Passport passport = new Passport("TESTID");
        Baggage b = new Baggage("content");
        b.setWeight(5);
        Baggage[] blist = {b};
        BookingClass bC = BookingClass.E;

        Ticket t = new Ticket(
                "Test",
                "Test",
                BookingClass.B,
                "Test",
                "Test",
                "Test",
                "Test",
                0,
                "Test",
                "Test"
        );

        Passenger p = new Passenger("TEST TEST", passport, blist);
        auto_mach.addTicket(p, t);

        auto_mach.checkIn(p, auto_mach.getSections()[0]);
        auto_mach.baggageDrop(p, auto_mach.getSections()[0]);

        for (Passenger pass : auto_mach.getCheckedInPassengers()) {
            assertSame(pass, p);
        }
    }

    @Test
    public void testCheckInBaggageDropSpec() {
        FBDMachine auto_mach = new FBDMachine();
        Passport passport = new Passport("TESTID");
        Baggage b = new Baggage("explosives");
        Baggage ba = new Baggage("cont");
        b.setWeight(5);
        Baggage[] blist = {b, ba};
        BookingClass bC = BookingClass.E;

        Ticket t = new Ticket(
                "Test",
                "Test",
                BookingClass.B,
                "Test",
                "Test",
                "Test",
                "Test",
                0,
                "Test",
                "Test"
        );

        Passenger p = new Passenger("TEST TEST", passport, blist);
        auto_mach.addTicket(p, t);

        auto_mach.checkIn(p, auto_mach.getSections()[0]);
        auto_mach.baggageDrop(p, auto_mach.getSections()[0]);
        //should be 2 times because checkin() calls baggagedrop()
        assertEquals(2, auto_mach.getTimesLocked());
    }

}

