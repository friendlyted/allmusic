package pro.friendlyted.ears.music.service

import org.springframework.stereotype.Component
import pro.friendlyted.ears.music.base.*
import javax.inject.Inject

@Component
class SupplierServiceImpl : SupplierService {

    val GUESSING_OCTAVES = listOf(Octave.C3, Octave.C4, Octave.C5)
    val GUESSING_ACCIDENTAL = listOf(Accidental.NATURAL)

    @Inject
    private val intervalBuilder: ChordBuilderService? = null

    override fun interval(): Pair<Interval, Pitches> {
        val lowerPitch = randomPitch()
        val interval = Interval.values().random()
        if (interval == Interval.p1) {
            return interval()
        }

        try {
            val pitches = intervalBuilder?.buildChord(lowerPitch, interval)
                    ?: throw Exception("wrong interval service")
            return Pair(interval, pitches)
        } catch (ex: OutOfDiapasonException) {
            ex.printStackTrace()
            throw RuntimeException(ex)
        }
    }

    override fun triad(): Pair<Triad, Pitches> {
        val lowerPitch = randomPitch()
        val triad = Triad.values().random()

        try {
            val pitches = intervalBuilder?.buildChord(lowerPitch, *triad.intervals().toTypedArray())
                    ?: throw Exception("wrong supplier service")
            return Pair(triad, pitches)
        } catch (ex: OutOfDiapasonException) {
            ex.printStackTrace()
            throw RuntimeException(ex)
        }
    }

    override fun chord7(): Pair<Chord7, Pitches> {
        val lowerPitch = randomPitch()
        val chord7 = Chord7.values().random()

        try {
            val pitches = intervalBuilder?.buildChord(lowerPitch, *chord7.intervals().toTypedArray())
                    ?: throw Exception("wrong supplier service")
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
