import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity
import org.slf4j.LoggerFactory
import pro.friendlyted.ears.music.base.Chord7
import pro.friendlyted.ears.music.base.Interval
import pro.friendlyted.ears.music.base.Pitches
import pro.friendlyted.ears.music.base.Triad
import pro.friendlyted.ears.music.service.*
import pro.friendlyted.ears.music.service.quest.SupplierService
import pro.friendlyted.ears.music.service.webhook.AliceMappingService

open class AliceTools(val execution: ExecutionEntity) {

    open fun set(varName: String, value: String) {
        execution.setVariable(varName, value)
        return
    }

    open fun add(varName: String, value: String) {
        var old = (execution.getVariable(varName) ?: "") as String
        if (old.isNotEmpty()) old += "\n"
        execution.setVariable(varName, old + value)
    }

    open fun setText(newText: String) = set("text", newText)

    open fun addText(newText: String) = add("text", newText)

    open fun setTts(newTts: String) = set("tts", newTts)

    open fun addTts(newTts: String) = add("tts", newTts)

    open fun addSound(sound: String) = add("tts", "<speaker audio='dialogs-upload/7ed29648-a8bf-461c-a362-726e4c2bae2a/$sound.opus' />")
    open fun setSound(sound: String) = set("tts", "<speaker audio='dialogs-upload/7ed29648-a8bf-461c-a362-726e4c2bae2a/$sound.opus' />")

    open fun setButtons(vararg text: String) {
        val buttons = ArrayList<String>()
        buttons.addAll(text)
        execution.setVariable("buttons", buttons)
    }

    open fun prepareQuest(mode: String) {
        supplierService ?: throw java.lang.Exception("There is no quest supplier service")
        val generateMode = mode.split(" ").random()
        val pair: Pair<Enum<*>, Pitches> = when (generateMode) {
            "interval" -> supplierService.interval()
            "triad" -> supplierService.triad()
            "chord7" -> supplierService.chord7()
            else -> throw Exception("There is no mode processor for $generateMode")
        }

        val chord = pair.first
        val pitches = pair.second

        val sound = pitches.toString()
        val harmonic = aliceMappingService.map(sound)
        val melodic = aliceMappingService.map(sound.replace("-", "_"))
        val answer = answerForCode((chord.name))

        execution.setVariable("questCode", chord.name)
        execution.setVariable("questHarmonicSound", harmonic)
        execution.setVariable("questMelodicSound", melodic)
        execution.setVariable("questAnswer", answer)

        LOGGER.info("[${execution.businessKey}] quest: {${chord.name} | $sound | $answer}")
    }

    open fun getButtonsForMode(mode: String): Array<String>? {
        val result = ArrayList<String>()
        if (mode.contains("interval")) result.addAll(intervalButtons)
        if (mode.contains("triad")) result.addAll(triadButtons)
        if (mode.contains("chord7")) result.addAll(chord7Buttons)
        return result.toTypedArray()
    }

    open fun getModeButtons() = arrayOf(
            "интервалы",
            "трезвучия",
            "септаккорды",
            "любые"
    )

    open fun parseCommand(command: String, expectedEntities: String): String {
        val cleanCommand = clean(command)
        val result = expectedEntities.split(Regex("[\\s,;]+"))
                .flatMap { Entity.entities[it] ?: error("no entity $it found") }.stream()
                .filter { it.eqFunc(cleanCommand) }
                .map { it.name }
                .findFirst().orElse("unknown")
        LOGGER.info("[${execution.businessKey}] parsing $command [$cleanCommand] to entity [$result] ($expectedEntities)")
        return result
    }

    open fun ensureRequest(unsure: String) {
        setText("Так, а точнее?")
        setTts("Так, а точнее?")
        when (unsure) {
            "unsure_2", "unsure_3", "unsure_6", "unsure_7" -> {
                addText("Малая? Или большая?")
                addTts("Малая? Или большая?")
                setButtons("большая", "малая")
            }
            "unsure_p" -> {
                addText("Что именно?")
                addTts("Что именно?")
                setButtons("кварта", "квинта")
            }
            "unsure_m", "unsure_M" -> {
                addText("Что именно?")
                addTts("Что именно?")
                setButtons("секунда", "терция", "секста", "септима")
            }
            "unsure_tm", "unsure_tM" -> {
                addText("Какое обращение?")
                addTts("Какое обращение?")
                setButtons("трезвучие", "секстаккорд", "квартсекстаккорд")
            }
            "unsure_53", "unsure_t6", "unsure_64" -> {
                addText("Мажор? Или минор?")
                addTts("Мажор? Или минор?")
                setButtons("мажор", "минор")
            }
        }
    }

    open fun mergeUnsure(unsure: String, choice: String): String {
        val toMerge = listOf(unsure, choice)

        val result = when {
            listOf("unsure_m", "unsure_2").containsAll(toMerge) -> "m2"
            listOf("unsure_M", "unsure_2").containsAll(toMerge) -> "M2"
            listOf("unsure_m", "unsure_3").containsAll(toMerge) -> "m3"
            listOf("unsure_M", "unsure_3").containsAll(toMerge) -> "M3"
            listOf("unsure_m", "unsure_6").containsAll(toMerge) -> "m6"
            listOf("unsure_M", "unsure_6").containsAll(toMerge) -> "M6"
            listOf("unsure_m", "unsure_7").containsAll(toMerge) -> "m7"
            listOf("unsure_M", "unsure_7").containsAll(toMerge) -> "M7"

            listOf("unsure_tM", "unsure_53").containsAll(toMerge) -> "M53"
            listOf("unsure_tM", "unsure_t6").containsAll(toMerge) -> "M6"
            listOf("unsure_tM", "unsure_64").containsAll(toMerge) -> "M64"

            listOf("unsure_tm", "unsure_53").containsAll(toMerge) -> "m53"
            listOf("unsure_tm", "unsure_t6").containsAll(toMerge) -> "m6"
            listOf("unsure_tm", "unsure_64").containsAll(toMerge) -> "m64"

            else -> choice
        }
        LOGGER.info("[${execution.businessKey}] merge answers: {$unsure + $choice => $result}")
        return result
    }

    open fun getCorrectText(): String = listOf(
            "Верно!",
            "Точно!",
            "Правильно!",
            "Угадали!",
            "А теперь?"
    ).random()

    open fun getIncorrectText(): String = listOf(
            "Неверно!",
            "Нет!",
            "Неправильно!",
            "Не угадали!",
            "Мимо!"
    ).random()

    companion object {
        fun clean(command: String): String {
            return command.replace(Regex("(^| )(ну|я|думаю|полагаю|скорее|скорее всего|наверное|вероятно|" +
                    "вот|это|же|да|ж|может|уж|точно|а|допустим|давай|скажем|что|предполагаю|тоже) "), " ")
        }

        val intervalButtons = arrayOf(
                "секунда",
                "терция",
                "кварта",
                "тритон",
                "квинта",
                "секста",
                "септима",
                "октава"
        )
        val triadButtons = arrayOf(
                "Б53",
                "Б6",
                "Б64",
                "м53",
                "м6",
                "м64",
                "ум53",
                "ув53"
        )
        val chord7Buttons = arrayOf(
                "бУв7",
                "бУв65",
                "бУв43",
                "бУв2",
                "бМаж7",
                "бМаж65",
                "бМаж43",
                "бМаж2",
                "мМаж7",
                "мМаж65",
                "мМаж43",
                "мМаж2",
                "бМин7",
                "бМин65",
                "бМин43",
                "бМин2",
                "мМин7",
                "мМин65",
                "мМин43",
                "мМин2",
                "мУм7",
                "мУм65",
                "мУм43",
                "мУм2",
                "ум7"
        )

        fun answerForCode(code: String): String {
            return when (code) {
                Interval.p1.name -> "чистая прима"
                Interval.m2.name -> "малая секунда"
                Interval.M2.name -> "большая секунда"
                Interval.m3.name -> "малая терция"
                Interval.M3.name -> "большая терция"
                Interval.p4.name -> "чистая кварта"
                Interval.A4.name,
                Interval.d5.name -> "тритон"
                Interval.p5.name -> "чистая квинта"
                Interval.m6.name -> "малая секста"
                Interval.M6.name -> "большая секста"
                Interval.m7.name -> "малая септима"
                Interval.M7.name -> "большая септима"
                Interval.p8.name -> "чистая октава"
                Triad.M53.name -> "мажорное трезвучие"
                Triad.M6.name -> "мажорный секстаккорд"
                Triad.M64.name -> "мажорный квартсекстаккорд"
                Triad.m53.name -> "минорное трезвучие"
                Triad.m6.name -> "минорный секстаккорд"
                Triad.m64.name -> "минорный квартсекстаккорд"
                Triad.d53.name -> "уменьшённое трезвучие"
                Triad.A53.name -> "увеличенное трезвучие"

                Chord7.MMm7.name -> "большой септаккорд с увеличенной квинтой"
                Chord7.MMm65.name -> "большой квинтсекстаккорд с увеличенной квинтой"
                Chord7.MMm43.name -> "большой терцквартаккорд с увеличенной квинтой"
                Chord7.MMm2.name -> "большой секундаккорд с увеличенной квинтой"

                Chord7.MmM7.name -> "большой мажорный септаккорд"
                Chord7.MmM65.name -> "большой мажорный квинтсекстаккорд"
                Chord7.MmM43.name -> "большой мажорный терцквартаккорд"
                Chord7.MmM2.name -> "большой мажорный секундаккорд"

                Chord7.Mmm7.name -> "малый мажорный септаккорд"
                Chord7.Mmm65.name -> "малый мажорный квинтсекстаккорд"
                Chord7.Mmm43.name -> "малый мажорный терцквартаккорд"
                Chord7.Mmm2.name -> "малый мажорный секундаккорд"

                Chord7.mMM7.name -> "большой минорный септаккорд"
                Chord7.mMM65.name -> "большой минорный квинтсекстаккорд"
                Chord7.mMM43.name -> "большой минорный терцквартаккорд"
                Chord7.mMM2.name -> "большой минорный секундаккорд"

                Chord7.mMm7.name -> "малый минорный септаккорд"
                Chord7.mMm65.name -> "малый минорный квинтсекстаккорд"
                Chord7.mMm43.name -> "малый минорный терцквартаккорд"
                Chord7.mMm2.name -> "малый минорный секундаккорд"

                Chord7.mmM7.name -> "малый септаккорд с уменьшенной квинтой"
                Chord7.mmM65.name -> "малый квинтсекстаккорд с уменьшенной квинтой"
                Chord7.mmM43.name -> "малый терцквартаккорд с уменьшенной квинтой"
                Chord7.mmM2.name -> "малый секундаккорд с уменьшенной квинтой"

                Chord7.mmm7.name -> "уменьшенный септаккорд"
                else -> throw Exception("no name for code $code")
            }
        }

        var supplierService = SupplierService()
        var aliceMappingService = AliceMappingService()

        val LOGGER = LoggerFactory.getLogger(AliceTools::class.java)!!
    }
}