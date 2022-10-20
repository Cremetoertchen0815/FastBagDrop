package dhbw.group2.humans;

import dhbw.group2.automata.FBDMachine;

import java.util.ArrayList;

public class FederalPolice {
    //Singleton pattern implementation
    private static FederalPolice instance = new FederalPolice();
    private FederalPolice() { }

    public static FederalPolice getInstance() {
        return instance;
    }

    private FederalPoliceOfficer[] officers = new FederalPoliceOfficer[] { new FederalPoliceOfficer()};

    public void reportForInvestigation(FBDMachine machine, int section) {
        for (var officer : officers) {
            if (officer != null) {
                machine.investigateExplosives(officer, section);
                return;
            }
        }
    }
}
