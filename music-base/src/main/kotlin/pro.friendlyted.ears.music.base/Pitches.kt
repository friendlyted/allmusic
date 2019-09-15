package pro.friendlyted.ears.music.base

data class Pitches(
        val lower: MusicPitch,
        val upper: ArrayList<Pair<Interval, MusicPitch>> = ArrayList()
) {

    fun add(interval: Interval, pitch: MusicPitch): Pitches {
        upper.add(Pair(interval, pitch))
        return this
    }

    override fun toString(): String {
        val sb = StringBuilder(lower.midiPitch.pitch().toString());
        upper.map { it.second }.forEach {
            sb.append("-").append(it.midiPitch.pitch())
        }
        return sb.toString()
    }
}
