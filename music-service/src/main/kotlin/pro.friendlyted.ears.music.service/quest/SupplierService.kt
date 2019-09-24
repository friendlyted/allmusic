package pro.friendlyted.ears.music.service.quest

import pro.friendlyted.ears.music.base.*

class SupplierService {

    val GUESSING_OCTAVES = listOf(Octave.C3, Octave.C4, Octave.C5)
    val GUESSING_ACCIDENTAL = listOf(Accidental.NATURAL)

    private val intervalBuilder = ChordBuilderService()

    fun interval(): Pair<Interval, Pitches> {
        val lowerPitch = randomPitch()
        val interval = Interval.values().random()
        if (interval == Interval.p1) {
            return interval()
        }

        try {
            val pitches = intervalBuilder?.buildChord(lowerPitch, interval)
            return Pair(interval, pitches)
        } catch (ex: OutOfDiapasonException) {
            ex.printStackTrace()
            throw RuntimeException(ex)
        }
    }

    fun triad(): Pair<Triad, Pitches> {
        val lowerPitch = randomPitch()
        val triad = Triad.values().random()

        try {
            val pitches = intervalBuilder?.buildChord(lowerPitch, *triad.intervals().toTypedArray())
            return Pair(triad, pitches)
        } catch (ex: OutOfDiapasonException) {
            ex.printStackTrace()
            throw RuntimeException(ex)
        }
    }

    fun chord7(): Pair<Chord7, Pitches> {
        val lowerPitch = randomPitch()
        val chord7 = Chord7.values().random()

        try {
            val pitches = intervalBuilder.buildChord(lowerPitch, *chord7.intervals().toTypedArray())
            return Pair(chord7, pitches)
        } catch (ex: OutOfDiapasonException) {
            ex.printStackTrace()
            throw RuntimeException(ex)
        }
    }

    private fun randomPitch(): MusicPitch {
        val octave = GUESSING_OCTAVES.random()
        val pitch = Pitch.values().random()
        val accidental = GUESSING_ACCIDENTAL.random()

        return MusicPitch.forValues(
                octave,
                pitch,
                accidental
        );
    }
}
