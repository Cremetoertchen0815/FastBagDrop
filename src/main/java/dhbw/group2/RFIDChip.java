package dhbw.group2;

public class RFIDChip {
    private String pin;

    public RFIDChip(String pin) {
        setPin(pin);
    }

    public String getEncryptedPin() {
        return pin;
    }

    private void setPin(String unencrypted) {
        pin = unencrypted;
    }
}
