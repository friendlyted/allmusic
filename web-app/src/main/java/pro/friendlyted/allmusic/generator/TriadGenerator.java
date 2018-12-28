package pro.friendlyted.allmusic.generator;

import pro.friendlyted.allmusic.model.MidiPitch;
import pro.friendlyted.allmusic.model.MusicPitch;

import javax.sound.midi.Sequence;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TriadGenerator {

    public static void main(String[] args) {
        try {
            final File outputFolder = new File("midi/triad");
            outputFolder.mkdirs();

            final List<MidiPitch> pitchList = new ArrayList<>();
            for (int i = MusicPitch.C3.getMidiPitch().pitch(); i < MusicPitch.C6.getMidiPitch().pitch(); i++) {
                pitchList.add(MidiPitch.forPitch(i));
            }

            final List<List<MidiPitch>> triadList = Recombinator.recombineMidiPitches(pitchList, 3);

            for (List<MidiPitch> chord : triadList) {
                final MidiPitch note1 = chord.get(0);
                final MidiPitch note2 = chord.get(1);
                final MidiPitch note3 = chord.get(2);

                final File file = new File(outputFolder, note1.pitch() + "-" + note2.pitch() + "-" + note3.pitch() + ".mid");
                final FileOutputStream out = new FileOutputStream(file);

                final Sequence sequence = new Recorder().singleChordSequence(chord);
                new Writer().save(out, sequence);

                out.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
