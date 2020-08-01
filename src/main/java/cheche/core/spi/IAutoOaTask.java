package cheche.core.spi;

/**
 * IAutoOaTask自动审批接口
 * 
 * @author jieli
 */
public interface IAutoOaTask extends IOaTask {
	/**
	 * 自动审批
	 * 
	 * @return true-自动审批通过 false-自动驳回
	 */
	boolean canAutoPass();
}
