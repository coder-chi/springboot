package com.example.zzy.springbootTest.controller;

import com.alibaba.fastjson.JSON;
import com.example.zzy.springbootTest.constant.CommonSymbol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AbstractController {

	private static final Log LOGGER = LogFactory
			.getLog(AbstractController.class);

	protected <T> T constructEntity(HttpServletRequest request, Class<T> clazz) {
		String reqStr = request.getParameter(CommonSymbol.REQUEST_STRING);
		LOGGER.info("req url is : " + request.getRequestURI());
		LOGGER.info(reqStr);
		return JSON.parseObject(reqStr, clazz);
	}

	protected <T> List<T> constructEntityList(HttpServletRequest request, Class<T> classz) {
		String reqStr = request.getParameter(CommonSymbol.REQUEST_STRING);
		return JSON.parseArray(reqStr, classz);
	}

	protected String getManagerIdFromRequest(HttpServletRequest request) {
		return request.getHeader(CommonSymbol.MANAGER_ID);
	}

	protected String getUserIdFromRequest(HttpServletRequest request) {
		return request.getHeader(CommonSymbol.USER_ID);
	}



}
