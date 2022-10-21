package dhbw.group2;

import dhbw.group2.automata.FBDMachine;
import dhbw.group2.humans.ServiceAgent;

public class Main {
    public static void main(String[] args) {
        var agent = new ServiceAgent();
        var auto = new FBDMachine();
        auto.importFromCSV(agent);
        auto.warmSimulation();
        auto.checkIn();
        auto.export();
    }
}