package com.example.zzy.springbootTest.util;


import com.example.zzy.springbootTest.dto.Result;

public final class ResultUtil {
	
	private static final String SUCCESS_CODE = "200";
	private static final String FAIL_CODE = "500";
	
	private static final String SUCCESS_TIP_SYMBOL = "success";
	private static final String FAIL_TIP_SYMBOL = "fail";
	
	/**
	 * 返回默认的成功结果，不带object
	 * @return
	 */
	public static final Result getDefaultSuccessResult(){
		String msg = TipUtil.getTip(SUCCESS_TIP_SYMBOL);
		return new Result(SUCCESS_CODE, msg);
	}
	
	/**
	 * 返回默认的失败结果，不带object
	 * @return
	 */
	public static final Result getDefaultFailResult(){
		String msg = TipUtil.getTip(FAIL_TIP_SYMBOL);
		return new Result(FAIL_CODE, msg);
	}
	
	/**
	 * 返回成功结果，不带object，带成功提示信息
	 * @param object
	 * @return
	 */
	public static final Result getSuccessResult(String key){
		return getSuccessResult(key, null);
	}
	/**
	 * 返回成功结果，带object以及默认的成功提示信息
	 * @param object
	 * @return
	 */
	public static final Result getSuccessResult(Object object){
		return getSuccessResult(SUCCESS_TIP_SYMBOL, object);
	}
	
	/**
	 * 返回成功结果，带object以及提示信息
	 * @param key
	 * @param object
	 * @return
	 */
	public static final Result getSuccessResult(String key, Object object){
		String msg = TipUtil.getTip(key);
		return new Result(SUCCESS_CODE, msg, object);
	}

	/**
	 * 返回失败结果，不带object，带失败提示信息
	 * @param object
	 * @return
	 */
	public static final Result getFailResult(String key){
		return getFailResult(key, null);
	}

	/**
	 * 返回失败结果，不带object，带失败提示信息,信息是从配置文件读取,还是由调用者传入,取决于isRaw
	 * @param object
	 * @return
	 */
	public static final Result getFailResult(String rawMsg, boolean isRaw){
		if(isRaw){
			return new Result(FAIL_CODE, rawMsg, null);
		}else{
			return getFailResult(rawMsg, null);
		}
	}

	/**
	 * 返回失败结果，带object以及默认的失败提示信息
	 * @param object
	 * @return
	 */
	public static final Result getFailResult(Object object){
		return getFailResult(FAIL_CODE, object);
	}
	
	/**
	 * 返回失败结果，带object以及提示信息
	 * @param key
	 * @param object
	 * @return
	 */
	public static final Result getFailResult(String key, Object object){
		String msg = TipUtil.getTip(key);
		return new Result(FAIL_CODE, msg, object);
	}
	
	public static final boolean isSuccessResult(Result result){
		return "200".equals(result.getCode()) ? true : false;
	}
}
