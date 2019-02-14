package pro.friendlyted.api

data class Pitches(
    val lower: MusicPitch
) {
    val upper = ArrayList<Pair<Interval, MusicPitch>>()

    fun add(interval: Interval, pitch: MusicPitch): Pitches {
        upper.add(Pair(interval, pitch))
        return this
    }

    override fun toString(): String {
        val sb = StringBuilder(lower.midiPitch.pitch().toString());
        upper.forEach { (interval, pitch) ->
            sb.append("-").append(pitch.midiPitch.pitch())
        }
        return sb.toString()
    }
}
