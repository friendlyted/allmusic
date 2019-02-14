package pro.friendlyted.api

interface ChordBuilderService {

    fun buildInterval(basePitch: MusicPitch, interval: Interval): MusicPitch

    @Throws(exceptionClasses = [OutOfDiapasonException::class])
    fun buildChord(basePitch: MusicPitch, vararg intervals: Interval): Pitches
}
