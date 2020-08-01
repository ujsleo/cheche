package cheche.core.exception;

/**
 * CHECHE异常
 * 
 * @author jieli
 *
 */
public enum ChecheExceptionEnum {
	// === 成功 ===
	/** 200 OK */
	OK(200, "0", "OK"),
	// === 请求错误 ===
	/** 400 Bad Request */
	E400(400, "400", "输入参数错误"),
	/** 401 Unauthorized */
	E401(401, "401", "Unauthorized"),
	/** 403 Forbidden */
	E403(403, "403", "Forbidden"),
	/** 404 Not Found */
	E404(404, "404", "Not Found"),
	// === 服务器错误 ===
	/** 500 Internal Server Error */
	E500(500, "500", "系统开小差了，请稍后再试~"),
	/** 501 Not Implemented */
	E501(501, "501", "Not Implemented"),
	/** 502 Bad Gateway */
	E502(502, "502", "Bad Gateway"),
	/** 503 Service Unavailable */
	E503(503, "503", "Service Unavailable");

	/** HTTP状态码 */
	private Integer statusCode;
	/** 错误码 */
	private String errorCode;
	/** 错误信息 */
	private String errorMsg;

	ChecheExceptionEnum(Integer statusCode, String errorCode, String errorMsg) {
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "errorCode: " + errorCode + ", errorMsg: " + errorMsg;
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
