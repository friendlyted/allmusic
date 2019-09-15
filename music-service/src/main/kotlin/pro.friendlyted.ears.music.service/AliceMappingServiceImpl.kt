package pro.friendlyted.ears.music.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import pro.friendlyted.ears.alice.fileservice.AliceFileService
import javax.annotation.PostConstruct

@Component
@PropertySource("classpath:/alice.properties")
class AliceMappingServiceImpl : AliceMappingService {

    @Value("\${alice.skillId}")
    private val aliceSkillId: String? = null
    @Value("\${alice.oAuthToken}")
    private val aliceOAuthToken: String? = null


    val soundMap = HashMap<String, String>()

    @PostConstruct
    fun init() {
        val aService = AliceFileService().apply {
            skillId = aliceSkillId
            oAuthToken = aliceOAuthToken
        }
        soundMap.clear()
        aService.listSounds().sounds.forEach {
            soundMap.put(it.originalName.replace(".opus",""), it.id)
        }
    }

    override fun map(sound: String): String {
        return soundMap[sound] ?: throw Exception("No Alice sound for $sound")
    }
}