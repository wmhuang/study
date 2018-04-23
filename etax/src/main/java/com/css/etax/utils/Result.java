package com.css.etax.utils;

public class Result {
	private boolean isSuccess;
	private String msg;
	private Object o;

	public Result() {
		this.isSuccess = true;
		this.msg = "操作成功";
	}

	public Result(boolean isSuccess, String msg) {
		this.isSuccess = isSuccess;
		this.msg = msg;
	}

	public Result(boolean isSuccess, String msg, Object o) {
		this.isSuccess = isSuccess;
		this.msg = msg;
		this.o = o;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

}
