package pro.friendlyted.ears.music.generator

import pro.friendlyted.ears.music.base.MidiPitch
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

    fun singleChordSequenceMelodic(chord: List<MidiPitch>): Sequence {
        try {
            val sequence = Sequence(Sequence.PPQ, 1)
            val track = sequence.createTrack()
            Recorder().addNotesMelodic(track, chord)
            return sequence
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }

    fun addNote(track: Track, note: MidiPitch, tick: Long = 0) {
        try {
            track.add(MidiEvent(ShortMessage(NOTE_ON, 0, note.pitch(), 100), tick))
            track.add(MidiEvent(ShortMessage(NOTE_OFF, 0, note.pitch(), 100), tick+2))
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }

    fun addNotes(track: Track, notes: List<MidiPitch>) {
        notes.forEach { note -> this.addNote(track, note) }
    }

    fun addNotesMelodic(track: Track, notes: List<MidiPitch>) {
        var tick = 0L;
        notes.forEach { note -> this.addNote(track, note, tick); tick += 2 }
    }

}
