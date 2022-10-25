import dhbw.group2.Main;
import dhbw.group2.automata.FBDMachine;
import dhbw.group2.humans.Passenger;
import dhbw.group2.humans.ServiceAgent;
import dhbw.group2.plane.boarding.Baggage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestApp {
    private ServiceAgent agent;
    private FBDMachine auto;

    @BeforeEach
    public void setUp() {
        agent = new ServiceAgent();
        auto = new FBDMachine();

        auto.importFromCSV(agent);
        auto.warmSimulation();
        auto.checkIn();
        auto.export();
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
}
