package dhbw.group2.plane.boarding;

public record Voucher(String voucherType) {
    public Voucher(String voucherType) {
        this.voucherType = voucherType;
    }
}
