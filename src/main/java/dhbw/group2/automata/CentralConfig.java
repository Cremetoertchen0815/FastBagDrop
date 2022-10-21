package dhbw.group2.automata;

public class CentralConfig {
    //Singleton pattern implementation
    private static CentralConfig instance;
    //Fields
    public EncryptionAlgorithm encryptionAlgorithm;

    private CentralConfig() {
        instance = new CentralConfig();
    }

    public static CentralConfig getInstance() {
        return instance;
    }
}
