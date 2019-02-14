package pro.friendlyted.service

import org.junit.Assert.assertSame
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import pro.friendlyted.api.ChordBuilderService
import pro.friendlyted.api.Interval
import pro.friendlyted.api.MusicPitch
import pro.friendlyted.api.OutOfDiapasonException
import javax.inject.Inject
import kotlin.test.assertEquals

@Configuration
@SpringBootApplication(
    scanBasePackages = ["pro.friendlyted.service"]
)
open class IntervalBuilderServiceImplTestConfig

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [IntervalBuilderServiceImplTestConfig::class])
class IntervalBuilderServiceImplTest {

    @Inject
    private val chordBuilder: ChordBuilderService? = null

    @Test
    fun buildIntervalTest() {
        val lower = MusicPitch.A0

        val upper1 = MusicPitch.A0
        val toCheck1 = chordBuilder!!.buildInterval(lower, Interval.p1)
        assertSame(upper1, toCheck1)

        val upper2 = MusicPitch.C1
        val toCheck2 = chordBuilder.buildInterval(lower, Interval.m3)
        assertSame(upper2, toCheck2)

        val upper3 = MusicPitch.A1
        val toCheck3 = chordBuilder.buildInterval(lower, Interval.p8)
        assertSame(upper3, toCheck3)
    }

    @Test(expected = OutOfDiapasonException::class)
    fun buildTripleSharpTest() {
        chordBuilder!!.buildInterval(MusicPitch.B0_DOUBLE_SHARP, Interval.A4)
    }

    @Test(expected = OutOfDiapasonException::class)
    fun buildTooHightTest() {
        chordBuilder!!.buildInterval(MusicPitch.C8_DOUBLE_FLAT, Interval.m2)
    }

    @Test
    fun buildChordTest() {
        val lower = MusicPitch.A0

        val upper1 = MusicPitch.C1
        val upper2 = MusicPitch.E1
        val toCheck1 = chordBuilder!!.buildChord(lower, Interval.m3, Interval.M3)
        assertSame(upper1, toCheck1.upper.get(0).second)
        assertSame(upper2, toCheck1.upper.get(1).second)

        val upper3 = MusicPitch.A1
        val upper4 = MusicPitch.A2
        val toCheck2 = chordBuilder.buildChord(lower, Interval.p8, Interval.p8)
        assertSame(upper3, toCheck2.upper.get(0).second)
        assertSame(upper4, toCheck2.upper.get(1).second)
    }

    @Test
    fun chordString() {
        val chord = chordBuilder!!.buildChord(MusicPitch.C1, Interval.M3, Interval.m3).toString();
        assertEquals(chord, "24-28-31")
    }
}