package pro.friendlyted.ears.music.service.webhook

import java.util.*

class AliceMappingService {

    fun map(sound: String): String {
        return soundMap[sound] ?: throw Exception("No Alice sound for $sound")
    }

    companion object {
        val soundMap: Map<String, String> by lazy {
            val properties = Properties()
            properties.load(javaClass.classLoader.getResourceAsStream(("alice.map")))
            properties.toMap() as Map<String, String>
        }
    }
}