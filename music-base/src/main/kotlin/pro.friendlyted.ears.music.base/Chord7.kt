package pro.friendlyted.ears.music.base

import pro.friendlyted.ears.music.base.Interval.*

enum class Chord7(
        val lowerInterval: Interval,
        val middleInterval: Interval,
        val upperInterval: Interval
) : IntervalStream {
    /**
     * дважды увеличенный
     */
    // (M3,M3,M3),
    MMm7(M3, M3, m3),
    MMm65(M3, m3, m2),
    MMm43(m3, m2, M3),
    MMm2(m2, M3, M3),

    MmM7(M3, m3, M3),
    MmM65(m3, M3, m2),
    MmM43(M3, m2, M3),
    MmM2(m2, M3, m3),

    Mmm7(M3, m3, m3),
    Mmm65(m3, m3, M2),
    Mmm43(m3, M2, M3),
    Mmm2(M2, M3, m3),

    mMM7(m3, M3, M3),
    mMM65(M3, M3, m2),
    mMM43(M3, m2, m3),
    mMM2(m2, m3, M3),

    mMm7(m3, M3, m3),
    mMm65(M3, m3, M2),
    mMm43(m3, M2, m3),
    mMm2(M2, m3, M3),

    mmM7(m3, m3, M3),
    mmM65(m3, M3, M2),
    mmM43(M3, M2, m3),
    mmM2(M2, m3,m3),

    mmm7(m3, m3, m3);

    override fun intervals(): List<Interval> {
        return listOf(lowerInterval, middleInterval, upperInterval)
    }
}