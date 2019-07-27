package pro.friendlyted.generator

import pro.friendlyted.api.Interval
import pro.friendlyted.api.MidiPitch
import pro.friendlyted.api.MusicPitch
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.sound.midi.Sequence

object ChordGenerator {

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val outputFolder = File(args[0])
            outputFolder.mkdirs()

            val pitchList = ArrayList<MidiPitch>()
            (MusicPitch.C2.midiPitch.pitch() until MusicPitch.C8.midiPitch.pitch())
                .map { MidiPitch.forPitch(it) }
                .map(pitchList::add)

            val recombinator = Recombinator()
            val nameCreator = NameCreator()

            val intervals = recombinator.recombineMidiPitches(pitchList, 2, this::intervalValidating)
            val triads = recombinator.recombineMidiPitches(pitchList, 3, this::triadValidating)

            intervals
                .union(triads)
                .forEach { chord ->
                    val fileH = File(outputFolder, nameCreator.forPitchesHarmonic(chord) + ".mid")
                    val fileM = File(outputFolder, nameCreator.forPitchesMelodic(chord) + ".mid")

                    FileOutputStream(fileH).use {
                        Writer().save(it, Recorder().singleChordSequence(chord))
                    }
                    FileOutputStream(fileM).use {
                        Writer().save(it, Recorder().singleChordSequenceMelodic(chord))
                    }
                }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun intervalValidating(chord: List<MidiPitch>, toAdd: MidiPitch): Boolean {
        //first externally
        if (chord.isEmpty())
            return true
        //already exits
        if (chord.contains(toAdd))
            return false
        //lower then existed
        if (toAdd.pitch() < chord.last().pitch())
            return false
        //more then octave
        if (toAdd.pitch() > chord.last().pitch() + Interval.p8.semitonesCount)
            return false
        return true
    }

    private fun triadValidating(chord: List<MidiPitch>, toAdd: MidiPitch): Boolean {
        //first externally
        if (chord.isEmpty())
            return true
        if (!intervalValidating(chord, toAdd))
            return false
        //intervals m3, M3, p4
        val upperInterval = toAdd.pitch() - chord.last().pitch()
        if (upperInterval < Interval.m3.semitonesCount || upperInterval > Interval.p4.semitonesCount)
            return false
        //not p4+p4
        val fullInterval = toAdd.pitch() - chord.first().pitch()
        if (fullInterval == Interval.p4.semitonesCount * 2)
            return false

        return true
    }

}
