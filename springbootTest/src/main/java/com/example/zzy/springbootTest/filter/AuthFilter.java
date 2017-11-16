package com.example.zzy.springbootTest.filter;

import com.example.zzy.springbootTest.constant.CommonSymbol;
import com.example.zzy.springbootTest.util.CacheUtil;
import com.example.zzy.springbootTest.util.JsonUtil;
import com.example.zzy.springbootTest.util.ResultUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by chloe on 2017/6/4.
 */
public class AuthFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        boolean isAdmin = httpRequest.getRequestURI().contains("/" + CommonSymbol.ADMIN + "/");
        String id = (isAdmin ? httpRequest.getHeader(CommonSymbol.MANAGER_ID) : httpRequest.getHeader(CommonSymbol.USER_ID));
        System.out.println("id==== "+id);
        String ticket = httpRequest.getHeader(CommonSymbol.TICKET);
        System.out.println("ticket====="+ticket);
        System.out.println("ticketInCache ==== "+CacheUtil.get(CommonSymbol.MANAGER_CACHE,"1"));
        String ticketInCache = (id == null ? null : (isAdmin ? CacheUtil.get(CommonSymbol.MANAGER_CACHE, id) + "" : CacheUtil.get(CommonSymbol.USER_CACHE, id) + ""));
        if (ticketInCache == null || !ticketInCache.equals(ticket)) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(
                    JsonUtil.obj2JsonString(ResultUtil.getFailResult("ticket.invalid")));
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }

}
