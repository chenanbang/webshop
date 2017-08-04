package com.cab.graduation.utils;

public class JsonResult {
	private static final int SUCCESS = 1;
	private static final int ERROR = 0;
	
	private int status;
	private String message;
	private Object data;
	
	public JsonResult() {
		super();
	}
	
	public JsonResult(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public static JsonResult newSuccessInstance(String message, Object data){
		return new JsonResult(SUCCESS, message, data);
	}
	
	public static JsonResult newErrorInstance(String message){
		return new JsonResult(ERROR, message, null);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
}
