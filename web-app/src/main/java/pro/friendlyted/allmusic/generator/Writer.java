package pro.friendlyted.allmusic.generator;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.IOException;
import java.io.OutputStream;

public class Writer {

    public void save(OutputStream out, Sequence sequence) throws IOException {
        MidiSystem.write(sequence, 1, out);
    }
}
