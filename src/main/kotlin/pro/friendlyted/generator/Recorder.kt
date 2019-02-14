package pro.friendlyted.generator

import pro.friendlyted.api.MidiPitch
import javax.sound.midi.MidiEvent
import javax.sound.midi.Sequence
import javax.sound.midi.ShortMessage
import javax.sound.midi.ShortMessage.NOTE_OFF
import javax.sound.midi.ShortMessage.NOTE_ON
import javax.sound.midi.Track

/**
 * creates MIDI requence from provided MIDI pitches
 */
class Recorder {

    fun singleChordSequence(chord: List<MidiPitch>): Sequence {
        try {
            val sequence = Sequence(Sequence.PPQ, 1)
            val track = sequence.createTrack()
            Recorder().addNotes(track, chord)
            return sequence
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }

    fun addNote(track: Track, note: MidiPitch) {
        try {
            track.add(MidiEvent(ShortMessage(NOTE_ON, 0, note.pitch(), 100), 0))
            track.add(MidiEvent(ShortMessage(NOTE_OFF, 0, note.pitch(), 100), 5))
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }

    }

    fun addNotes(track: Track, notes: List<MidiPitch>) {
        notes.forEach { note -> this.addNote(track, note) }
    }

}
