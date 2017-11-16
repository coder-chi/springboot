package com.example.zzy.springbootTest.controller;

import com.example.zzy.springbootTest.constant.CommonSymbol;
import com.example.zzy.springbootTest.dto.Result;
import com.example.zzy.springbootTest.dto.query.UserQuery;
import com.example.zzy.springbootTest.server.IUserService;
import com.example.zzy.springbootTest.util.NullCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangzhiyuan on 2017/8/15.
 */
@RestController
@RequestMapping("/" + CommonSymbol.PORTAL + "/user")

public class UserController extends AbstractController{

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response){
        UserQuery userQuery = constructEntity(request,UserQuery.class);
        NullCheckUtil.checkNotNull(userQuery.getUserName(),userQuery.getPassword());
        return userService.login(userQuery);
    }

}
