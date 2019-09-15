package pro.friendlyted.ears.music.base

import pro.friendlyted.ears.music.base.Interval.*

enum class Triad(
        val lowerInterval: Interval,
        val upperInterval: Interval
) : IntervalStream {
    M53(M3, m3),
    M6(m3, p4),
    M64(p4, M3),
    m53(m3, M3),
    m6(M3, p4),
    m64(p4, m3),
    d53(m3, m3),
    A53(M3, M3);

    override fun intervals(): List<Interval> {
        return listOf(lowerInterval, upperInterval)
    }
}
