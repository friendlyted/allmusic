package pro.friendlyted.ears.music.service.rest

import pro.friendlyted.ears.music.service.EngineProvider
import java.util.*
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CleanupServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -5)
        EngineProvider.engine.runtimeService.createExecutionQuery()
                .variableValueLessThan("date", calendar.time.time)
                .list()
                .forEach { EngineProvider.engine.runtimeService.deleteProcessInstance(it.id, "cleanup") }
    }
}