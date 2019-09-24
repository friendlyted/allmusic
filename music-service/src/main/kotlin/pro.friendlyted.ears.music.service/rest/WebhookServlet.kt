package pro.friendlyted.ears.music.service.rest

import com.google.gson.Gson
import org.slf4j.LoggerFactory
import pro.friendlyted.ears.music.service.EngineProvider
import pro.friendlyted.ears.music.service.webhook.AliceRequest
import pro.friendlyted.ears.music.service.webhook.WebhookService
import java.util.*
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class WebhookServlet : HttpServlet() {

    val webhookService = WebhookService()

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val start = Date().time
        req.characterEncoding = "UTF-8";
        resp.addHeader("Content-Type", "application/json;charset=UTF-8")
        val request = GSON.fromJson(req.reader, AliceRequest::class.java)
        val response = webhookService.webHook(request)
        val result = GSON.toJson(response)
        val stop = Date().time
        LOGGER.info("respond in " + (stop - start) + "ms")
        resp.writer.write(result)
        resp.writer.close()
    }

    companion object {
        private val GSON = Gson()
        val LOGGER = LoggerFactory.getLogger(WebhookServlet::class.java)!!
        //just init Camunda
        val runtimeService = EngineProvider.engine.runtimeService
    }
}
