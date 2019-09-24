import org.camunda.bpm.engine.impl.bpmn.behavior.AbstractBpmnActivityBehavior
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution

class AliceServiceTask : AbstractBpmnActivityBehavior() {
    override fun execute(execution: ActivityExecution?) {
    }

    override fun signal(execution: ActivityExecution?, signalEvent: String?, signalData: Any?) {
        leave(execution);
    }
}
