package pro.friendlyted.ears.music.generator

import javax.sound.midi.MidiSystem
import javax.sound.midi.Sequence
import java.io.IOException
import java.io.OutputStream

class Writer {

    @Throws(IOException::class)
    fun save(out: OutputStream, sequence: Sequence) {
        MidiSystem.write(sequence, 1, out)
    }
}
