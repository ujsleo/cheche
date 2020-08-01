package cheche.controller.vo;

import org.apache.commons.lang3.StringUtils;

import cheche.core.exception.ChecheException;
import cheche.core.exception.ChecheExceptionEnum;

/**
 * Base Response
 * 
 * @author jieli
 *
 * @param <T> 返回值的数据类型
 */
public class BaseResponse<T> {
	/** 返回值 */
	private T value = null;
	/** 是否成功 */
	private Boolean success = true;
	/** 错误码 */
	private String errorCode = ChecheExceptionEnum.OK.getErrorCode();
	/** 错误信息 */
	private String errorMsg = ChecheExceptionEnum.OK.getErrorMsg();

	/** 异常处理 */
	public void exceptionHandler(Exception e) {
		this.success = false;
		if (e instanceof ChecheException) {
			ChecheException ce = (ChecheException) e;
			this.errorCode = ce.getErrorCode();
			this.errorMsg = beautyErrorMsg(ce.getErrorMsg());
		} else {
			this.errorCode = ChecheExceptionEnum.E500.getErrorCode();
			this.errorMsg = e.getMessage();
		}
	}

	private String beautyErrorMsg(String errorMsg) {
		String searchSeq = "ChecheException";
		if (StringUtils.contains(errorMsg, searchSeq)) {
			int start = StringUtils.lastIndexOf(errorMsg, searchSeq) + searchSeq.length() + 1;
			return StringUtils.substring(errorMsg, start);
		}
		return errorMsg;
	}

	/** Redis分布式锁获取失败的异常处理 */
	public void lockFailedHandler() {
		this.success = false;
		this.errorCode = ChecheExceptionEnum.E500.getErrorCode();
		this.errorMsg = "客官请慢点哟~";
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Boolean isSuccess() {
		return success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
