package dhbw.group2.automata;

public class CentralConfig {
    //Singleton pattern implementation
    private static CentralConfig instance = new CentralConfig();;
    //Fields
    public EncryptionAlgorithm encryptionAlgorithm = EncryptionAlgorithm.AES;
    public ScanAlgorithm baggageScanAlgorithm = ScanAlgorithm.BRUTE_FORCE;

    private CentralConfig() { }

    public static CentralConfig getInstance() {
        return instance;
    }
}
