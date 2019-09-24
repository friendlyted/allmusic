package pro.friendlyted.ears.alice.fileservice

import java.io.File
import java.io.FileOutputStream
import java.util.*

class FileServiceApplication {
    companion object {
        @JvmStatic
        open fun main(vararg args: String) {

            val aliceSkillId = args[0]
            val aliceOAuthToken = args[1]
            val outputFile = args[2]

            val service = AliceFileService().apply {
                skillId = aliceSkillId
                oAuthToken = aliceOAuthToken
            }

            val soundMap = Properties()

            service.listSounds().sounds.forEach {
                soundMap.setProperty(it.originalName.replace(".opus", ""), it.id)
            }

            soundMap.store(FileOutputStream(File(outputFile)), null)
        }
    }
}