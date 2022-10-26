import dhbw.group2.Main;
import dhbw.group2.automata.FBDMachine;
import dhbw.group2.automata.StateEnum;
import dhbw.group2.humans.*;
import dhbw.group2.humans.identification.IDCard;
import dhbw.group2.humans.identification.IDCardStatus;
import dhbw.group2.plane.boarding.Baggage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.plaf.metal.MetalBorders;

import java.beans.FeatureDescriptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.*;
import static org.mockito.Mockito.*;

public class TestApp {
    private ServiceAgent agent;
    private FBDMachine auto;

    @BeforeEach
    public void setUp() {
        agent = new ServiceAgent();
        auto = new FBDMachine();
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
    public void testStartUp() {
        FBDMachine testAuto = new FBDMachine();
        ServiceAgent testAgent = new ServiceAgent();
        IDCard card = new IDCard();

        testAgent.setCard(card);
        card.setStatus(IDCardStatus.ACTIVE);

        testAuto.startup(testAgent, 0);
        assertTrue(StateEnum.LOCKED == testAuto.getState());
    }
}
