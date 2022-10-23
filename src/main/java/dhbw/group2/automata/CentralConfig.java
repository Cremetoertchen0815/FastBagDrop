package dhbw.group2.automata;

public class CentralConfig {
    //Singleton pattern implementation
    private static CentralConfig instance;
    //Fields
    public EncryptionAlgorithm encryptionAlgorithm = EncryptionAlgorithm.AES;
    public ScanAlgorithm baggageScanAlgorithm = ScanAlgorithm.BRUTE_FORCE;

    private CentralConfig() {
        instance = new CentralConfig();
    }

    public static CentralConfig getInstance() {
        return instance;
    }
}
