package pro.friendlyted.ears.music.service

import org.junit.Test

class NearTest {

    @Test
    open fun testDistance() {
        val d1 = "большая секста".nearTo("большая септима")
    }

    @Test
    open fun testClean(){
        println("наверное тритон".replace(Regex("(^| )(ну|я|думаю|полагаю|скорее|скорее всего|наверное|вероятно|это|же|может|уж|точно) "), " "))
    }
}