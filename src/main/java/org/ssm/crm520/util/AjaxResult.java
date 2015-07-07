package org.ssm.crm520.util;

/**
 * AjaxResult替代原来的map
 * @author 李璨
 * @since 2014.04.29
 */
public class AjaxResult {
	private boolean success = true;
	private String message = "操作成功!";
	private Integer errorCode = -99;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public AjaxResult(String message) {
		this.message = message;
	}

	public AjaxResult() {
	}
}
