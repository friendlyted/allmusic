package pro.friendlyted.ears.music.service.webhook

class AliceRequest(
        val meta: Meta,
        val request: Request,
        val session: Session,
        val version: String
) {
    data class Meta(
            val locale: String,
            val timezone: String,
            val client_id: String,
            val interfaces: Map<String, Object>
    )

    data class Request(
            val command: String?,
            val original_utterance: String,
            val type: RequestType,
            val markup: Markup?,
            val payload: Object?
    )

    data class Session(
            val new: Boolean,
            val message_id: Int,
            val session_id: String,
            val skill_id: String,
            val user_id: String
    )

    data class Markup(
            val dangerous_context: Boolean
    )

    enum class RequestType {
        SimpleUtterance,
        ButtonPressed
    }
}


