package pro.friendlyted.ears.alice.webhook

class AliceResponse(
        val response: Response,
        val session: Session,
        val version: String
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
            val payload: Object? = null,
            val url: Object? = null,
            val hide: Boolean = false
    )
}
