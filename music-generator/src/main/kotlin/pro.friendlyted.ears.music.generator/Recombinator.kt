package pro.friendlyted.ears.music.generator

import pro.friendlyted.ears.music.base.Interval
import pro.friendlyted.ears.music.base.MidiPitch

class Recombinator {

    fun generateChord(
            pitchesList: List<MidiPitch>,
            times: Int,
            intervalVariants: List<Interval>,
            validator: (List<MidiPitch>) -> Boolean
    ): List<List<MidiPitch>> {
        val result = ArrayList<ArrayList<MidiPitch>>()
        pitchesList.forEach { base ->
            val chord = ArrayList<MidiPitch>()
            chord.add(base)
            addAllVariantsOf(chord, intervalVariants, times, result, validator)
        }
        return result
    }

    private fun addAllVariantsOf(
            chord: ArrayList<MidiPitch>,
            intervalVariants: List<Interval>,
            times: Int,
            result: ArrayList<ArrayList<MidiPitch>>,
            validator: (List<MidiPitch>) -> Boolean
    ) {
        intervalVariants.forEach l@{
            val nextPitch = chord.last().pitch() + it.semitonesCount
            if(nextPitch-chord.first().pitch() > Interval.p8.semitonesCount){
                return@l
            }
            val newChord = ArrayList(chord)
            newChord.add(MidiPitch.forPitch(nextPitch))
            if(!validator(newChord)){
                return@l
            }
            if (times == 1) {
                result.add(newChord)
            } else {
                addAllVariantsOf(newChord, intervalVariants, times - 1, result, validator)
            }
        }
    }

}
