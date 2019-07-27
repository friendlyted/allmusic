package pro.friendlyted.generator

import pro.friendlyted.api.MidiPitch

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
