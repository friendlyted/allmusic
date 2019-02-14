package pro.friendlyted.generator

import pro.friendlyted.api.MidiPitch

class NameCreator {

    fun forPitches(pitches: List<MidiPitch>): String {
        return pitches.map(MidiPitch::pitch).joinToString("-")
    }
}
