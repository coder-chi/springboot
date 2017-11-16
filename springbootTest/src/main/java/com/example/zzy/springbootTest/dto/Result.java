package com.example.zzy.springbootTest.dto;

public class Result {
	
	private String code;
	private String msg;
	private Object obj;

	public Result(){

	}

	public Result(String code, String msg){
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public Result(String code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
