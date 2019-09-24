package pro.friendlyted.ears.music.service.camunda

import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity
import pro.friendlyted.ears.music.service.EngineProvider

class CamundaAliceService {

    fun process(response: BpmResponse, sessionId: String): BpmRequest {
        val exec = findExecForSession(sessionId)

        if (exec != null) {
            EngineProvider.engine.runtimeService
                    .signal(exec.id, mapOf("command" to response.command));
        } else {
            startForSession(sessionId)
        }

        val vars = findVarsForSession(sessionId)
        vars ?: throw Exception("process stopped")
        val request = BpmRequest(
                text = vars["text"] as String?,
                tts = vars["tts"] as String?,
                buttons = vars["buttons"] as List<String>?
        )

        return request
    }

    private fun startForSession(sessionId: String) {
        EngineProvider.engine.runtimeService
                .startProcessInstanceByKey(
                        "AliceProcess",
                        sessionId
                )
    }

    private fun findVarsForSession(sessionId: String): Map<String, Any>? {
        val exec = findExecForSession(sessionId)

        exec ?: return null
        return EngineProvider.engine.runtimeService
                .createVariableInstanceQuery()
                .processInstanceIdIn(exec.processInstanceId)
                .list().map { it.name to it.value }.toMap()

    }

    private fun findExecForSession(sessionId: String): ExecutionEntity? {
        val exec = EngineProvider.engine.runtimeService
                .createExecutionQuery()
                .processInstanceBusinessKey(sessionId)
                .activityId("AliceExchange")
                .list()
                .firstOrNull() as ExecutionEntity?
        return exec
    }
}
