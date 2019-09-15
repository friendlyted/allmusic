import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import pro.friendlyted.ears.alice.fileservice.AliceFileService
import pro.friendlyted.ears.music.base.Interval.*
import pro.friendlyted.ears.music.base.MidiPitch
import pro.friendlyted.ears.music.base.MusicPitch
import pro.friendlyted.ears.music.generator.NameCreator
import pro.friendlyted.ears.music.generator.Recombinator
import pro.friendlyted.ears.music.generator.Recorder
import pro.friendlyted.ears.music.generator.Writer
import java.io.File
import java.io.FileOutputStream
import java.util.Arrays.asList
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Ignore
@RunWith(SpringRunner::class)
@TestPropertySource("classpath:/alice.properties")
class ChordGenerator {

    private val resourceFolder = javaClass.getResource("/").file
    private val mediaFolder = "$resourceFolder/media"

    @Value("\${alice.skillId}")
    private val aliceSkillId: String? = null
    @Value("\${alice.oAuthToken}")
    private val aliceOAuthToken: String? = null

    val MID = ".mid"

    @Test
    fun generateMidiTest() {
        try {
            File(mediaFolder).mkdirs()

            val pitchList = ArrayList<MidiPitch>()
            (MusicPitch.C2.midiPitch.pitch() until MusicPitch.C7.midiPitch.pitch())
                    .map { MidiPitch.forPitch(it) }
                    .map(pitchList::add)

            val recombinator = Recombinator()
            val nameCreator = NameCreator()

            val intervals = recombinator.generateChord(pitchList, 1, values().toList(), ::intervalValidator)
            val triads = recombinator.generateChord(pitchList, 2, asList(M3, m3, p4), ::triadValidator)
            val chord7s = recombinator.generateChord(pitchList, 3, asList(m2, M2, m3, M3), ::chord7Validator)

            intervals.union(triads).union(chord7s)
                    .forEach { chord ->
                        val fileH = File(mediaFolder, nameCreator.forPitchesHarmonic(chord) + MID)
                        val fileM = File(mediaFolder, nameCreator.forPitchesMelodic(chord) + MID)

                        FileOutputStream(fileH).use {
                            Writer().save(it, Recorder().singleChordSequence(chord))
                        }
                        FileOutputStream(fileM).use {
                            Writer().save(it, Recorder().singleChordSequenceMelodic(chord))
                        }
                    }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @Test
    fun convertMidToWavTest() {
        ProcessBuilder("tools/timidity.exe", "-Ow", "$mediaFolder/*$MID".removePrefix("/"))
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start()
                .waitFor(60, TimeUnit.MINUTES)
    }

    @Test
    fun convertWavToOpusTest() {
        val executor = Executors.newFixedThreadPool(6)
        File(mediaFolder).walkTopDown()
                .filter { it.name.endsWith(".wav") }
                .forEach { file -> executor.execute({ convertWavToOpus(file) }) }

        executor.shutdown()
        while (!executor.isTerminated) {
        }
    }

    @Test
    fun deleteAllAliceSoundsTest() {
        val aService = AliceFileService().apply {
            skillId = aliceSkillId
            oAuthToken = aliceOAuthToken
        }

        val executor = Executors.newFixedThreadPool(6)
        aService.listSounds().sounds.forEach{ sound ->
            executor.execute {
                try {
                    aService.deleteSound(sound.id)
                } catch (ex: Exception) {
                    throw java.lang.Exception("Cannot delete ${sound.id}", ex)
                }
            }
        }
        executor.shutdown()
        while (!executor.isTerminated) {
        }
    }

    @Test
    fun uploadAliceSoundTest() {
        val aService = AliceFileService().apply {
            skillId = aliceSkillId
            oAuthToken = aliceOAuthToken
        }
        val executor = Executors.newFixedThreadPool(6)
        File(mediaFolder).walkTopDown()
                .filter { it.name.endsWith(".opus") }
                .forEach { file ->
                    executor.execute {
                        try {
                            Thread.sleep(400)
                            aService.uploadSound(file)
                        } catch (ex: Exception) {
                            throw java.lang.Exception("Cannot upload ${file.name}", ex)
                        }
                    }
                }

        executor.shutdown()
        while (!executor.isTerminated) {
        }
    }

    private fun convertWavToOpus(file: File) {
        val wav = file.absolutePath
        val opus = file.absolutePath.replace(".wav", ".opus")
        ProcessBuilder("tools/ffmpeg.exe", "-i", wav, "-c:a", "libopus", "-b:a", "320K", opus)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start()
                .waitFor(60, TimeUnit.MINUTES)
    }


    fun intervalValidator(@Suppress("UNUSED_PARAMETER")chord: List<MidiPitch>): Boolean {
        return true
    }

    fun triadValidator(chord: List<MidiPitch>): Boolean {
        if (chord.last().pitch() - chord.first().pitch() >= p4.semitonesCount * 2) {
            return false
        }
        return true
    }

    fun chord7Validator(chord: List<MidiPitch>): Boolean {
        var hasSecond = false
        var prevPitch: Int = 0
        for (pitch in chord) {
            if (pitch.pitch() - prevPitch <= M2.semitonesCount) {
                if (hasSecond) {
                    return false
                }
                hasSecond = true
            }
            prevPitch = pitch.pitch()
        }
        return true
    }


}
