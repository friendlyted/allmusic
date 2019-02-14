package pro.friendlyted.api

interface SupplierService {

    fun interval(): Pair<Interval, Pitches>

    fun triad(): Pair<Triad, Pitches>
}
