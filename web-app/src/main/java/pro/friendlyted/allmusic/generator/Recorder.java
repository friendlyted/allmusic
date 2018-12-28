package pro.friendlyted.allmusic.generator;

import pro.friendlyted.allmusic.model.MidiPitch;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.util.List;

import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;

public class Recorder {

    public Sequence singleChordSequence(List<MidiPitch> chord) {
        try {
            final Sequence sequence = new Sequence(Sequence.PPQ, 1);
            final Track track = sequence.createTrack();
            new Recorder().addNotes(track, chord);
            return sequence;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNote(Track track, MidiPitch note) {
        try {
            track.add(new MidiEvent(new ShortMessage(NOTE_ON, 0, note.pitch(), 100), 0));
            track.add(new MidiEvent(new ShortMessage(NOTE_OFF, 0, note.pitch(), 100), 5));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNotes(Track track, List<MidiPitch> notes) {
        notes.forEach(note -> this.addNote(track, note));
    }

}
