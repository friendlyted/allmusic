package pro.friendlyted.ears.music.service

import pro.friendlyted.ears.music.base.Interval
import pro.friendlyted.ears.music.base.MusicPitch
import pro.friendlyted.ears.music.base.Pitches

interface ChordBuilderService {

    fun buildInterval(basePitch: MusicPitch, interval: Interval): MusicPitch

    @Throws(exceptionClasses = [OutOfDiapasonException::class])
    fun buildChord(basePitch: MusicPitch, vararg intervals: Interval): Pitches
}
