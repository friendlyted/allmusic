package pro.friendlyted.service

import org.springframework.stereotype.Component
import pro.friendlyted.api.*
import pro.friendlyted.api.MusicConsts.GUESSING_ACCIDENTAL
import pro.friendlyted.api.MusicConsts.GUESSING_OCTAVES
import javax.inject.Inject

@Component
class SupplierServiceImpl : SupplierService {

    @Inject
    private val intervalBuilder: ChordBuilderService? = null

    override fun interval(): Pair<Interval, Pitches> {
        val lowerPitch = randomPitch()
        val interval = Interval.values().random()
        if(interval == Interval.p1){
            return interval()
        }

        try {
            val pitches = intervalBuilder!!.buildChord(lowerPitch, interval)
            return Pair(interval, pitches);
        } catch (ex: OutOfDiapasonException) {
            ex.printStackTrace()
            throw RuntimeException(ex)
        }
    }

    override fun triad(): Pair<Triad, Pitches> {
        val lowerPitch = randomPitch()
        val triad = Triad.values().random();

        try {
            val pitches = intervalBuilder!!.buildChord(lowerPitch, *triad.intervals().toTypedArray())
            return Pair(triad, pitches);
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
