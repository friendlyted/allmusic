package pro.friendlyted.ears.music.service.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pro.friendlyted.ears.music.base.Chord7
import pro.friendlyted.ears.music.base.Interval
import pro.friendlyted.ears.music.base.Pitches
import pro.friendlyted.ears.music.base.Triad
import pro.friendlyted.ears.music.service.AliceMappingService
import pro.friendlyted.ears.music.service.Quest
import pro.friendlyted.ears.music.service.SupplierService
import javax.inject.Inject
import kotlin.reflect.KFunction0

@RestController
class Controller {

    @Inject
    private val supplierService: SupplierService? = null

    @Inject
    private val aService: AliceMappingService? = null

    @RequestMapping("/quest/interval")
    fun intervalQuest(): Quest {
        supplierService ?: throw Exception("wrong supplier service")
        return generateQuest(supplierService::interval, ::intervalText)
    }

    @RequestMapping("/quest/triad")
    fun triadQuest(): Quest {
        supplierService ?: throw Exception("wrong supplier service")
        return generateQuest(supplierService::triad, ::triadText)
    }

    @RequestMapping("/quest/chord7")
    fun chord7Quest(): Quest {
        supplierService ?: throw Exception("wrong supplier service")
        return generateQuest(supplierService::chord7, ::chord7Text)
    }

    fun <T : Enum<T>>generateQuest(generator: ()->Pair<T, Pitches>, nameProvider: (T)->String ): Quest {
        val (chord, pitches) = generator()
        val harmonic = aService?.map(pitches.toString()) ?: throw Exception("wrong alice service")
        val melodic = aService?.map(pitches.toString().replace("-", "_"))
        val quest = Quest(pitches.toString(), chord.name, nameProvider(chord), harmonic, melodic)
        println(quest)
        return quest
    }

    fun intervalText(interval: Interval): String {
        return when (interval) {
            Interval.p1 -> "чистая прима"
            Interval.m2 -> "малая секунда"
            Interval.M2 -> "большая секунда"
            Interval.m3 -> "малая терция"
            Interval.M3 -> "большая терция"
            Interval.p4 -> "чистая кварта"
            Interval.A4 -> "тритон"
            Interval.d5 -> "чистая квинта"
            Interval.p5 -> "тритон"
            Interval.m6 -> "малая секста"
            Interval.M6 -> "большая секста"
            Interval.m7 -> "малая септима"
            Interval.M7 -> "большая септима"
            Interval.p8 -> "чистая октава"
        }
    }

    fun triadText(triad: Triad): String {
        return when (triad) {
            Triad.M53 -> "мажорное трезвучие"
            Triad.M6 -> "мажорный секстаккорд"
            Triad.M64 -> "мажорный квартсекстаккорд"
            Triad.m53 -> "минорное трезвучие"
            Triad.m6 -> "минорный секстаккорд"
            Triad.m64 -> "минорный квартсекстаккорд"
            Triad.d53 -> "уменьшённое трезвучие"
            Triad.A53 -> "увеличенное трезвучие"
        }
    }

    fun chord7Text(chord7: Chord7): String {
        return when (chord7) {
            Chord7.MMm2 -> "мажорное трезвучие"
            else -> ""
        }
    }
}
