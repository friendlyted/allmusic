package pro.friendlyted.ears.music.base

enum class Interval constructor(
        val stepCount: Int,
        val semitonesCount: Int
) : IntervalStream {

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

    override fun intervals(): List<Interval> {
        return listOf(this)
    }

    override fun toString(): String {
        return "Interval(" + name +
                "){stepCount=" + stepCount +
                ", semitonesCount=" + semitonesCount +
                '}'.toString()
    }
}
