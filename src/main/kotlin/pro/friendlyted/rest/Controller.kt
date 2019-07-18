package pro.friendlyted.rest

import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import pro.friendlyted.api.Quest
import pro.friendlyted.api.SupplierService
import pro.friendlyted.api.YandexMappingService
import java.io.IOException
import javax.inject.Inject

@RestController
class Controller {

    @Inject
    private val supplierService: SupplierService? = null

    @Inject
    private val yaService: YandexMappingService? = null

    @RequestMapping("/quest/interval")
    fun intervalQuest(): Quest {
        val (interval, pitches) = supplierService!!.interval()
        val sound = yaService!!.map(pitches.toString())
        return Quest(pitches.toString(), interval.name, sound)
    }

    @RequestMapping("/quest/triad")
    fun triadQuest(): Quest {
        val (triad, pitches) = supplierService!!.triad()
        val sound = yaService!!.map(pitches.toString())
        return Quest(pitches.toString(), triad.name, sound)
    }

    @RequestMapping(path = ["/media/{file}"], method = [RequestMethod.GET])
    @Throws(IOException::class)
    fun download(
        @PathVariable("file") file: String
    ): ResponseEntity<Resource> {
        val headers = HttpHeaders()
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate")
        headers.add("Pragma", "no-cache")
        headers.add("Expires", "0")

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.parseMediaType("audio/mpeg"))
            .body(
                InputStreamResource(
                    javaClass.classLoader.getResourceAsStream("media/$file.mp3")
                        ?: return ResponseEntity.notFound().build()
                )
            )
    }
}
