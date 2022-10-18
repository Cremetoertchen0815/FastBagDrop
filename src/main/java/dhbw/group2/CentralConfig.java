package dhbw.group2;

public class CentralConfig {
    //Singleton pattern implementation
    private static CentralConfig instance;
    private CentralConfig() {
        instance = new CentralConfig();
    }
    public static CentralConfig getInstance() {
        return instance;
    }

    //Fields
    public EncryptionAlgorithm encryptionAlgorithm;
}
