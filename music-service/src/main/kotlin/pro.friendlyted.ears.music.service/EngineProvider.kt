package pro.friendlyted.ears.music.service

import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration
import org.camunda.bpm.engine.impl.history.HistoryLevel

class EngineProvider {
    companion object {
        val config = StandaloneProcessEngineConfiguration()
        val engine: ProcessEngine by lazy {
            config.dataSourceJndiName = "java:comp/env/jdbc/camunda"
            config.databaseSchemaUpdate = "true"
            config.historyLevel = HistoryLevel.HISTORY_LEVEL_NONE
            val result = config.buildProcessEngine()

            result.repositoryService!!
                    .createDeployment()
                    .addClasspathResource("alice.bpmn")
                    .name("alice").deploy();
            result
        }
    }

}