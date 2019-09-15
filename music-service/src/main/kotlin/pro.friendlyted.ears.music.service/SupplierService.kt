package pro.friendlyted.ears.music.service

import pro.friendlyted.ears.music.base.Chord7
import pro.friendlyted.ears.music.base.Interval
import pro.friendlyted.ears.music.base.Pitches
import pro.friendlyted.ears.music.base.Triad

interface SupplierService {

    fun interval(): Pair<Interval, Pitches>

    fun triad(): Pair<Triad, Pitches>

    fun chord7(): Pair<Chord7, Pitches>
}
