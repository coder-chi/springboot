package com.example.zzy.springbootTest.server;

import com.example.zzy.springbootTest.dto.Result;
import com.example.zzy.springbootTest.dto.query.UserQuery;

/**
 * Created by zhangzhiyuan on 2017/8/16.
 */
public interface IUserService {

    Result login(UserQuery query);
}
