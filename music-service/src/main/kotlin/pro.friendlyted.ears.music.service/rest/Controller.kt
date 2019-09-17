package pro.friendlyted.ears.music.service.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pro.friendlyted.ears.alice.webhook.AliceRequest
import pro.friendlyted.ears.alice.webhook.AliceResponse
import pro.friendlyted.ears.alice.webhook.AliceSession
import pro.friendlyted.ears.music.service.AliceEarsService
import javax.inject.Inject

@RestController
class Controller {

    @Inject
    var aService: AliceEarsService? = null

    @RequestMapping("/webhook")
    fun webHook(aliceRequest: AliceRequest): AliceResponse {
        val session = AliceSession.load(aliceRequest.session.session_id)
        val response = AliceResponse.Response()
        val aliceResponse = AliceResponse(
                session = AliceResponse.Session(
                        session_id = aliceRequest.session.session_id,
                        message_id = aliceRequest.session.message_id,
                        user_id = aliceRequest.session.user_id
                ),
                response = response,
                version = "1.0"
        )

        aService?.process(aliceRequest, session, response) ?: throw Exception("alice service is not available")

        session.save()
        return aliceResponse
    }

}
