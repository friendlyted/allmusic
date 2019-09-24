package pro.friendlyted.ears.music.service.webhook

import pro.friendlyted.ears.music.service.camunda.BpmRequest
import pro.friendlyted.ears.music.service.camunda.BpmResponse
import pro.friendlyted.ears.music.service.camunda.CamundaAliceService


class WebhookService() {

    val aliceService = CamundaAliceService()

    fun webHook(aliceRequest: AliceRequest): AliceResponse {
        val bpmResponse = convert(aliceRequest)
        val bpmRequest = aliceService.process(bpmResponse, aliceRequest.session.session_id)
        return convert(aliceRequest, bpmRequest)
    }

    private fun convert(request: AliceRequest): BpmResponse {
        val command = null
                ?: request?.request?.command
                ?: readTextPayload(request)
                ?: null

        return BpmResponse(command)
    }

    private fun readTextPayload(request: AliceRequest): String? {
        return (request?.request?.payload as Map<String, Object>)["text"] as String?
    }

    private fun convert(request: AliceRequest, bpmRequest: BpmRequest): AliceResponse {
        return AliceResponse(
                AliceResponse.Response(
                        bpmRequest.text,
                        bpmRequest.tts,
                        convertButtons(bpmRequest.buttons)
                ),
                AliceResponse.Session(
                        request.session.session_id,
                        request.session.message_id,
                        request.session.user_id
                )
        )
    }

    private fun convertButtons(buttons: List<String>?): ArrayList<AliceResponse.Button> {
        val result = ArrayList<AliceResponse.Button>()
        buttons?.forEach {
            result.add(AliceResponse.Button(it, mapOf("text" to it)))
        }
        return result
    }
}