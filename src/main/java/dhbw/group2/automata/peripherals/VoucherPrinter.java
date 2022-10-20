package dhbw.group2.automata.peripherals;

import dhbw.group2.plane.boarding.Voucher;

public class VoucherPrinter implements IPrinter<Voucher> {
    private String voucherType;

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    @Override
    public Voucher print() {
        return new Voucher(voucherType);
    }
}
