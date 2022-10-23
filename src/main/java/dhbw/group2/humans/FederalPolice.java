package dhbw.group2.humans;

import dhbw.group2.automata.FBDMachine;

public class FederalPolice {
    //Singleton pattern implementation
    private static final FederalPolice instance = new FederalPolice();
    private final FederalPoliceOfficer[] officers = new FederalPoliceOfficer[]{new FederalPoliceOfficer()};

    private FederalPolice() { }

    public static FederalPolice getInstance() {
        return instance;
    }

    public void reportForInvestigation(FBDMachine machine, int section) {
        for (var officer : officers) {
            if (officer != null) {
                machine.investigateExplosives(officer, section);
                return;
            }
        }
    }
}
