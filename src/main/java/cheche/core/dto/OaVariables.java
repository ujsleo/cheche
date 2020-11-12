package cheche.core.dto;

/**
 * 流程变量
 * 
 * @author jieli
 */
public class OaVariables {
    /** 流程ID */
    private Long    processId = null;
    /** task_id */
    private Long    taskId    = null;
    /** 级次 */
    private Integer step      = null;

    public OaVariables() {
    }

    public OaVariables(Long processId, Integer step) {
        this.processId = processId;
        this.step = step;
    }

    public OaVariables(Long processId, Long taskId, Integer step) {
        this.processId = processId;
        this.taskId = taskId;
        this.step = step;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
