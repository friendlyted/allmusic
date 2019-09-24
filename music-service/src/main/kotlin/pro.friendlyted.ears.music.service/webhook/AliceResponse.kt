package pro.friendlyted.ears.music.service.webhook

class AliceResponse(
        val response: Response,
        val session: Session,
        val version: String = "1.0"
) {
    data class Response(
            var text: String? = null,
            var tts: String? = null,
            val buttons: ArrayList<Button> = ArrayList(),
            var end_session: Boolean? = null
    )

    data class Session(
            val session_id: String,
            val message_id: Int,
            val user_id: String
    )

    data class Button(
            val title: String,
            val payload: Any? = null,
            val url: String? = null,
            val hide: Boolean = true
    )
}


class AliceResponseBuilder(
        session_id: String,
        message_id: Int,
        user_id: String
) {
    val aliceResponse = AliceResponse(AliceResponse.Response(), AliceResponse.Session(session_id, message_id, user_id), "1.0")

    fun text(text: String) {
        aliceResponse.response.text = text
        if (aliceResponse.response.tts == null) aliceResponse.response.tts = text
    }

    fun tts(tts: String) {
        aliceResponse.response.tts = tts
    }

    fun button(title: String): AliceResponseBuilder {
        aliceResponse.response.buttons.add(AliceResponse.Button(title = title, payload = ButtonText(title)))
        return this
    }

    fun get() = aliceResponse

    data class ButtonText(val text: String)
}