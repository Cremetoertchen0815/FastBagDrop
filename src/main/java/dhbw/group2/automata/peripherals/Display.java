package dhbw.group2.automata.peripherals;

import dhbw.group2.Action;

public class Display {
    public String readInput() {
        return "";
    }

    public int readInt() { return 0; }

    public String readPIN() { return "1234"; }

    public void showButtonAsync(String message, Action action) {

    }

    public void showButtons(String[] messages) {

    }

    //Stalls the code and waits for a button to be pressed
    public int stallButtonSelection() {
        return 0;
    };

    public void printMessage(String message) {

    }
}
