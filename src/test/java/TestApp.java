import dhbw.group2.Main;
import dhbw.group2.automata.BagBoardRecord;
import dhbw.group2.automata.BagBoardResult;
import dhbw.group2.automata.FBDMachine;
import dhbw.group2.automata.StateEnum;
import dhbw.group2.humans.*;
import dhbw.group2.humans.identification.IDCard;
import dhbw.group2.humans.identification.IDCardStatus;
import dhbw.group2.humans.identification.Passport;
import dhbw.group2.plane.boarding.Baggage;
import dhbw.group2.plane.boarding.OnlineBoardingPass;
import dhbw.group2.plane.ticket.BookingClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.plaf.metal.MetalBorders;
import javax.swing.plaf.nimbus.State;

import java.beans.FeatureDescriptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.*;
import static org.mockito.Mockito.*;

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
    public void testStart_Shutdown_Data_Export() {
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

        //test data analytics

        //test export
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
        BagBoardRecord person = auto.getBoardRecordMap().get(0);

        assertSame(BagBoardResult.OK, person.result());
        assertSame("Carolina Sharp", person.ticket().getName());
        assertSame(BookingClass.B, person.ticket().getBookingClass());
        assertSame("8QRI1IKHQ", person.passport().getId());
        assertSame("tzcfqdv5g4aefr3", person.ticket().getId());
        assertSame(0, person.baggageTag().getId());

    }

    @Test
    public void testBaggageTag() {

    }

    @Test
    public void testCheckInBaggageDropNorm() {

    }

    @Test
    public void testCheckInBaggageDropSpec() {

    }

    @Test
    public void testIDPassportFingerPrintFace() {

    }

    @Test
    public void testBaggageSearchAlgorithm() {

    }

    @Test
    public void testBaggageTagQR() {

    }

    @Test
    public void testUseApp() {

    }

}

