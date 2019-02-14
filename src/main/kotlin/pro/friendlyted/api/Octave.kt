package pro.friendlyted.api

enum class Octave private constructor(val number: Int) {
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
    C9(9)
}
