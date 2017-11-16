package com.example.zzy.springbootTest.server.impl;

import com.example.zzy.springbootTest.constant.CommonSymbol;
import com.example.zzy.springbootTest.dto.Result;
import com.example.zzy.springbootTest.dto.query.UserQuery;
import com.example.zzy.springbootTest.mapper.UserMapper;
import com.example.zzy.springbootTest.model.User;
import com.example.zzy.springbootTest.model.ext.UserExt;
import com.example.zzy.springbootTest.server.IUserService;
import com.example.zzy.springbootTest.util.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by zhangzhiyuan on 2017/8/16.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result login(UserQuery query) {
        System.out.println("执行");
        List<User> users = userMapper.selectByConditions(query);
        if(CollectionUtil.isListNotEmpty(users)){
            UserExt user = new UserExt();
            BeanUtils.copyProperties(users.get(0),user);
            String ticket = UUID.randomUUID().toString();
            CacheUtil.put(CommonSymbol.MANAGER_CACHE,user.getManagerId(),ticket);
            user.setTicket(ticket);
            return ResultUtil.getSuccessResult(user);
        }
        else return ResultUtil.getFailResult("user.login.error");
    }
}
