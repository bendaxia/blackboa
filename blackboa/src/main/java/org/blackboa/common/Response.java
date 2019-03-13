package org.blackboa.common;

import java.util.HashMap;

import org.blackboa.utils.json.JsonUtils;


public class Response { 
	public static final int OK = 200;// 成功
	public static final int OPERATION_FAIL = 201;// 失败
	public static final int OPERATION_NULL = 202;// 失败
	public static final int ERROR_INTERNAL = 500;// 服务器内部错误
	public static final int NOT_DATA = 300;// 服务器内部错误
	/**
	 * 基本响应码
	 */
	private int code;

	/**
	 * 描述信息
	 */
	private String message;

	/**
	 * 基本对象
	 */
	private Object data = new HashMap<Object, Object>();

	/**
	 * 是否已经达到最后一页
	 */
	private boolean lastPage = true;

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public static String ok(String message) {
		Response response = new Response();
		response.setCode(Response.OK);
		response.setMessage(message);
		try {
			return JsonUtils.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String ok() {
		Response response = new Response();
		response.setCode(Response.OK);
		response.setMessage("成功");
		try {
			return JsonUtils.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String ok(String message, Object data) {
		Response response = new Response();
		response.setCode(Response.OK);
		response.setMessage(message);
		response.setData(data);
		try {
			return JsonUtils.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String ok(Object data) {
		Response response = new Response();
		response.setCode(OK);
		response.setMessage("成功");
		response.setData(data);
		try {
			return JsonUtils.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 服务器内部错误
	 * 
	 * @return
	 */
	public static String errorInternal() {
		Response response = new Response();
		response.setCode(ERROR_INTERNAL);
		response.setMessage("网络错误");
		try {
			return JsonUtils.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String errorInternal(String message) {
		Response response = new Response();
		response.setCode(ERROR_INTERNAL);
		response.setMessage(message);
		try {
			return JsonUtils.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Response error() {
		Response response = new Response();
		response.setCode(ERROR_INTERNAL);
		response.setMessage("网络错误");
		return response;
	}

	public static String result(int code, String message, Object data) {
		Response response = new Response();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		try {
			return JsonUtils.toJson(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String ok(Object returnMap, boolean lastPage) {
		Response response = new Response();
		response.setCode(Response.OK);
		response.setData(returnMap);
		response.setLastPage(lastPage);
		response.setMessage("成功");
		try {
			return JsonUtils.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
