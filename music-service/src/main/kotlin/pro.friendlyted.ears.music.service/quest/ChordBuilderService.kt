package pro.friendlyted.ears.music.service.quest

import pro.friendlyted.ears.music.base.*

class ChordBuilderService {

    @Throws(OutOfDiapasonException::class)
    fun buildChord(basePitch: MusicPitch, vararg intervals: Interval): Pitches {
        val chord = Pitches(basePitch)
        var currentPitch = basePitch
        for (interval in intervals) {
            val nextPitchValue = buildInterval(currentPitch, interval)
            chord.add(interval, nextPitchValue)
            currentPitch = nextPitchValue
        }
        return chord
    }

    fun buildInterval(basePitch: MusicPitch, interval: Interval): MusicPitch {
        val lowerPitchValue = basePitch.midiPitch.pitch()
        val upperPitchValue = lowerPitchValue + interval.semitonesCount

        val lowerStep = basePitch.pitch.ordinal
        val upperStep = (lowerStep + interval.stepCount) % 7
        val upperPitch = Pitch.values()[upperStep]

        val variants = MusicPitch.forPitch(MidiPitch.forPitch(upperPitchValue))
        for (variant in variants) {
            if (variant.pitch === upperPitch) {
                return variant
            }
        }

        throw OutOfDiapasonException("Cannot create $interval from $basePitch")
    }
}

