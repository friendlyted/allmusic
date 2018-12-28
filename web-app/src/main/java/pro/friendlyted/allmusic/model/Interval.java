package pro.friendlyted.allmusic.model;

public enum Interval {

    p1(0, 0),
    m2(1, 1),
    M2(1, 2),
    m3(2, 3),
    M3(2, 4),
    p4(3, 5),
    A4(3, 6),
    d5(4, 6),
    p5(4, 7),
    m6(5, 8),
    M6(5, 9),
    m7(6, 10),
    M7(6, 11),
    p8(7, 12);

    private final int stepCount;
    private final int semitonesCount;

    Interval(int stepCount, int semitonesCount) {
        this.stepCount = stepCount;
        this.semitonesCount = semitonesCount;
    }

    public int getStepCount() {
        return stepCount;
    }

    public int getSemitonesCount() {
        return semitonesCount;
    }

    @Override
    public String toString() {
        return "Interval(" + name() +
            "){stepCount=" + stepCount +
            ", semitonesCount=" + semitonesCount +
            '}';
    }
}
