package pro.friendlyted.ears.music.service

import com.google.gson.Gson
import org.springframework.stereotype.Component
import pro.friendlyted.ears.alice.webhook.AliceRequest
import pro.friendlyted.ears.alice.webhook.AliceResponse
import pro.friendlyted.ears.alice.webhook.AliceSession
import pro.friendlyted.ears.music.base.Chord7
import pro.friendlyted.ears.music.base.Interval
import pro.friendlyted.ears.music.base.Pitches
import pro.friendlyted.ears.music.base.Triad
import javax.inject.Inject

@Component
class AliceEarsService {

    @Inject
    private val supplierService: SupplierService? = null

    @Inject
    private val aService: AliceMappingService? = null

    val SELECT_STEP = "SELECT_STEP"
    val QUEST_STEP = "QUEST_STEP"

    fun process(aliceRequest: AliceRequest, session: AliceSession, response: AliceResponse.Response) {
        if (checkGreetings(aliceRequest, session, response)) return
        if (checkHelp(aliceRequest, session, response)) return
        //if(checkSettings(aliceRequest,session,response)) return
        if (checkQuest(aliceRequest, session, response)) return
        if (checkResponse(aliceRequest, session, response)) return
        defaultResponse(aliceRequest, session, response)
    }

    private fun defaultResponse(aliceRequest: AliceRequest, session: AliceSession, response: AliceResponse.Response) {
        response.text = MSG_UNKNOWN
        response.tts = MSG_UNKNOWN_TTS
    }


    private fun checkGreetings(request: AliceRequest, session: AliceSession, response: AliceResponse.Response): Boolean {
        if (request.session.new || request.request.command.nearToAny("заново", "сначала")) {
            response.text = MSG_GREETINGS
            response.tts = MSG_GREETINGS_TTS

            response.buttons.add(AliceResponse.Button("интервалы"))
            response.buttons.add(AliceResponse.Button("трезвучия"))
            response.buttons.add(AliceResponse.Button("септаккорды"))
            response.buttons.add(AliceResponse.Button("все подряд"))

            session.step = SELECT_STEP

            return true
        }
        return false
    }

    fun checkHelp(request: AliceRequest, session: AliceSession, response: AliceResponse.Response): Boolean {
        if (request.request.command.nearToAny("помощь", "справка")) {
            response.text = MSG_HELP
            response.tts = MSG_HELP_TTS
            return true
        }
        return false
    }

    private fun checkQuest(aliceRequest: AliceRequest, session: AliceSession, response: AliceResponse.Response): Boolean {
        // if (session.step != SELECT_STEP) return false
        val command = aliceRequest.request.command
        when {
            command.nearTo("интервал") -> questInterval(response, session)
            command.nearTo("трезвучие") -> questTriad(response, session)
            command.nearTo("септаккорд") -> questChord7(response, session)
            command.nearToAny("все", "все подряд", "любые") -> questAny(response, session)
            else -> return false
        }

        session.step = QUEST_STEP
        return true
    }

    private fun questInterval(response: AliceResponse.Response, session: AliceSession) {
        val quest = intervalQuest()

        response.text = "Послушайте"
        response.tts = "<speaker audio='dialogs-upload/7ed29648-a8bf-461c-a362-726e4c2bae2a/${quest.harmonicSound}.opus' />"
        response.buttons.add(AliceResponse.Button("секунда"))
        response.buttons.add(AliceResponse.Button("терция"))
        response.buttons.add(AliceResponse.Button("кварта"))
        response.buttons.add(AliceResponse.Button("тритон"))
        response.buttons.add(AliceResponse.Button("квинта"))
        response.buttons.add(AliceResponse.Button("секста"))
        response.buttons.add(AliceResponse.Button("септима"))
        response.buttons.add(AliceResponse.Button("октава"))

        session.quest = GSON.toJson(quest)
        session.mode = AliceSession.Mode.Interval
    }

    private fun questTriad(response: AliceResponse.Response, session: AliceSession) {
        val quest = triadQuest()

        response.text = "Послушайте"
        response.tts = "<speaker audio='dialogs-upload/7ed29648-a8bf-461c-a362-726e4c2bae2a/${quest.harmonicSound}.opus' />"
        response.buttons.add(AliceResponse.Button("м53"))
        response.buttons.add(AliceResponse.Button("м6"))
        response.buttons.add(AliceResponse.Button("м64"))
        response.buttons.add(AliceResponse.Button("Б54"))
        response.buttons.add(AliceResponse.Button("Б6"))
        response.buttons.add(AliceResponse.Button("Б64"))
        response.buttons.add(AliceResponse.Button("ум53"))
        response.buttons.add(AliceResponse.Button("ув53"))

        session.quest = GSON.toJson(quest)
        session.mode = AliceSession.Mode.Triad
    }

    private fun questChord7(response: AliceResponse.Response, session: AliceSession) {
        val quest = chord7Quest()

        response.text = "Послушайте"
        response.tts = "<speaker audio='dialogs-upload/7ed29648-a8bf-461c-a362-726e4c2bae2a/${quest.harmonicSound}.opus' />"

        session.quest = GSON.toJson(quest)
        session.mode = AliceSession.Mode.Chord7
    }

    private fun questAny(response: AliceResponse.Response, session: AliceSession) {
        val quest = intervalQuest()

        response.text = "Послушайте"
        response.tts = "<speaker audio='dialogs-upload/7ed29648-a8bf-461c-a362-726e4c2bae2a/${quest.harmonicSound}.opus' />"
        session.quest = GSON.toJson(quest)
        session.mode = AliceSession.Mode.All
    }

    fun checkResponse(aliceRequest: AliceRequest, session: AliceSession, response: AliceResponse.Response): Boolean {
        if (session.step != QUEST_STEP) return false
        val quest = GSON.fromJson(session.quest, Quest::class.java) ?: return false
        val command = aliceRequest.request.command

        if(command.nearTo(quest.answerText)) {
            when(session.mode){
                AliceSession.Mode.Interval -> questInterval(response, session)
                AliceSession.Mode.Triad -> questTriad(response, session)
                AliceSession.Mode.Chord7 -> questChord7(response, session)
                AliceSession.Mode.All -> questAny(response, session)
            }
            return true
        }

        if (checkHalfIntervalResponse(aliceRequest, session, response, quest)) return true

        return false
    }

    private fun checkHalfIntervalResponse(aliceRequest: AliceRequest, session: AliceSession, response: AliceResponse.Response, quest: Quest): Boolean {
        val command = aliceRequest.request.command
        if(quest.answerText.contains(command)){
            response.text = "$command, а точнее?"
            response.tts = response.text

            response.buttons.add(AliceResponse.Button("большая $command"))
            response.buttons.add(AliceResponse.Button("малая $command"))

            return true
        }
        return false
    }

    private fun intervalQuest(): Quest {
        supplierService ?: throw Exception("wrong supplier service")
        return generateQuest(supplierService::interval, ::intervalText)
    }

    private fun triadQuest(): Quest {
        supplierService ?: throw Exception("wrong supplier service")
        return generateQuest(supplierService::triad, ::triadText)
    }

    private fun chord7Quest(): Quest {
        supplierService ?: throw Exception("wrong supplier service")
        return generateQuest(supplierService::chord7, ::chord7Text)
    }

    private fun <T : Enum<T>> generateQuest(generator: () -> Pair<T, Pitches>, nameProvider: (T) -> String): Quest {
        val (chord, pitches) = generator()
        aService ?: throw Exception("wrong alice service")
        val harmonic = aService.map(pitches.toString())
        val melodic = aService.map(pitches.toString().replace("-", "_"))
        val quest = Quest(pitches.toString(), chord.name, nameProvider(chord), harmonic, melodic)
        println(quest)
        return quest
    }

    private fun intervalText(interval: Interval): String {
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

    private fun triadText(triad: Triad): String {
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

    private fun chord7Text(chord7: Chord7): String {
        return when (chord7) {
            Chord7.MMm2 -> "мажорное трезвучие"
            else -> ""
        }
    }

    companion object {
        val GSON = Gson()

        const val MSG_GREETINGS = "Навык является закрытым для общего использования, поскольку находится в процессе разработки.\n" +
                "Что будем угадывать? Трезвучия? Или интервалы?"
        const val MSG_GREETINGS_TTS = MSG_GREETINGS

        const val MSG_HELP = "В навыке слуховой викторины Вы можете заказать исполнение музыкального интервала или трезвучия и попытаться их угадать.\n" +
                "Вы можете узнать, что прозвучало, сказав \"сдаюсь\" или \"не знаю\".\n" +
                "Вы в любой момент можете вернуться к выбору новых интервала или трезвучия, сказав \"интервал\", \"трезвучие\" или \"сначала\".\n" +
                "Чтобы повторно прослушать загаданный интервал, просто скажите \"повторить\".\n" +
                "Чтобы прослушать следующий интервал, скажите \"другой\".\n" +
                "Чтобы узнать возможные варианты ответов, скажите \"варианты\"."
        const val MSG_HELP_TTS = MSG_HELP

        const val MSG_UNKNOWN = "Извините, непонятно"
        const val MSG_UNKNOWN_TTS = MSG_UNKNOWN

    }

}