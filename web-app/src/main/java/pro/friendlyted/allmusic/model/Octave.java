package pro.friendlyted.allmusic.model;

public enum Octave {
    C0(0),
    /**
     * Contra
     */
    C1(1),
    /**
     * Great
     */
    C2(2),
    /**
     * Small
     */
    C3(3),
    /**
     * 1 Line
     */
    C4(4),
    /**
     * 2 Line
     */
    C5(5),
    /**
     * 3 Line
     */
    C6(6),
    /**
     * 4 Line
     */
    C7(7),
    /**
     * 5 Line
     */
    C8(8),
    /**
     * 6 Line
     */
    C9(9);

    private final int number;

    Octave(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
