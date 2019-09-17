package pro.friendlyted.ears.alice.webhook

import com.google.gson.Gson
import java.io.File

class AliceSession(
        val sessionId: String,
        var quest: String? = null,
        var settings: String? = null,
        var step: String? = null,
        var mode: Mode? = null
) {

    enum class Mode {
        Interval,
        Triad,
        Chord7,
        All
    }

    fun save() {
        sessionFile(sessionId).writeText(GSON.toJson(this))
    }

    companion object {
        @JvmStatic
        val GSON = Gson()

        fun load(sessionId: String): AliceSession {
            val sessionFile = sessionFile(sessionId)
            if (sessionFile.exists()) {
                return GSON.fromJson(sessionFile.readText(), AliceSession::class.java)
            }
            return AliceSession(sessionId)
        }

        fun sessionFile(sessionId: String): File {
            return File("session/" + sessionId.replace("-", "/") + ".json")
        }

    }
}
