package dhbw.group2.humans;

import dhbw.group2.humans.identification.IDCard;

public abstract class Employee extends Human {
    protected IDCard card = new IDCard();

    public IDCard getCard() {
        return card;
    }

}
