package cheche.core.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * CHECHE异常
 * 
 * @author jieli
 *
 */
public class ChecheException extends NestedRuntimeException {
	/** HTTP状态码 */
	private Integer statusCode;
	/** 错误码 */
	private String errorCode;
	/** 错误信息 */
	private String errorMsg;

	public ChecheException(ChecheExceptionEnum cee) {
		super(cee.toString());
		this.statusCode = cee.getStatusCode();
		this.errorCode = cee.getErrorCode();
		this.errorMsg = cee.getErrorMsg();
	}

	public ChecheException(ChecheExceptionEnum cee, String msg) {
		super(msg);
		this.statusCode = cee.getStatusCode();
		this.errorCode = cee.getErrorCode();
		this.errorMsg = msg;
	}

	public ChecheException(ChecheExceptionEnum cee, Throwable cause) {
		super(cee.toString(), cause);
		this.statusCode = cee.getStatusCode();
		this.errorCode = cee.getErrorCode();
		this.errorMsg = cause.getLocalizedMessage();
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
