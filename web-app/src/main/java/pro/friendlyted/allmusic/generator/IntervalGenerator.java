package pro.friendlyted.allmusic.generator;

import pro.friendlyted.allmusic.model.MidiPitch;
import pro.friendlyted.allmusic.model.MusicPitch;

import javax.sound.midi.Sequence;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class IntervalGenerator {

    public static void main(String[] args) {
        try {
            final File outputFolder = new File("midi/interval");
            outputFolder.mkdirs();


            final List<MidiPitch> pitchList = new ArrayList<>();
            for (int i = MusicPitch.C3.getMidiPitch().pitch(); i < MusicPitch.C6.getMidiPitch().pitch(); i++) {
                pitchList.add(MidiPitch.forPitch(i));
            }

            final List<List<MidiPitch>> intervalList = Recombinator.recombineMidiPitches(pitchList, 2);

            for (List<MidiPitch> interval : intervalList) {
                final MidiPitch note1 = interval.get(0);
                final MidiPitch note2 = interval.get(1);

                final File file = new File(outputFolder, note1.pitch() + "-" + note2.pitch() + ".mid");
                final FileOutputStream out = new FileOutputStream(file);

                final Sequence sequence = new Recorder().singleChordSequence(interval);
                new Writer().save(out, sequence);

                out.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
