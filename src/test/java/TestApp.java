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
    private FBDMachine auto;
    private ServiceAgent agent;

    private FBDMachine testAuto;
    private ServiceAgent testAgent;
    private IDCard card;
    @BeforeEach
    public void setUp() {
        auto = new FBDMachine();
        agent = new ServiceAgent();


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

        testAuto = new FBDMachine();
        testAgent = new ServiceAgent();
        card = new IDCard();

        testAgent.setCard(card);
        card.setStatus(IDCardStatus.ACTIVE);

        testAuto.startup(testAgent, 0);
        assertSame(StateEnum.ON, testAuto.getState());
    }
}
