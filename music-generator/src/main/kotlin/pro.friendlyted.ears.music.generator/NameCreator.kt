package pro.friendlyted.ears.music.generator

import pro.friendlyted.ears.music.base.MidiPitch

class NameCreator {

    fun forPitchesHarmonic(pitches: List<MidiPitch>): String {
        return forPitches(pitches, "-")
    }

    fun forPitchesMelodic(pitches: List<MidiPitch>): String {
        return forPitches(pitches, "_")
    }

    private fun forPitches(pitches: List<MidiPitch>, separator: String) =
        pitches.map(MidiPitch::pitch).joinToString(separator)
}
