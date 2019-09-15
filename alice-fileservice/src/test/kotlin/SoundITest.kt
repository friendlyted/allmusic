import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import pro.friendlyted.ears.alice.fileservice.AliceFileService
import java.io.File

@RunWith(SpringRunner::class)
@TestPropertySource("classpath:/alice.properties")
class SoundITest {

    @Value("\${alice.skillId}")
    private val aliceSkillId: String? = null
    @Value("\${alice.oAuthToken}")
    private val aliceOAuthToken: String? = null

    private val service = { AliceFileService().apply {
        skillId = aliceSkillId
        oAuthToken = aliceOAuthToken
    } }

    @Test
    fun testUpload() {
        val response = service().uploadSound(File(javaClass.getResource("test.mp3").file))
        val id = response.sound.id
        val info = service().getSound(id)
        assert(id == info.sound.id)
        assert(service().listSounds().sounds.filter { it.id == id }.any())
        service().deleteSound(id)
        assert(service().listSounds().sounds.filter { it.id == id }.none())
    }
}
