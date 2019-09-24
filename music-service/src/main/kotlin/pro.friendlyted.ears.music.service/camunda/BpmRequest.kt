package pro.friendlyted.ears.music.service.camunda

data class BpmRequest(
        var text: String?,
        var tts: String?,
        val buttons: List<String>?
)