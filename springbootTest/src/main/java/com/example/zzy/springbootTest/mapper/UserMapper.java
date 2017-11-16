package com.example.zzy.springbootTest.mapper;

import com.example.zzy.springbootTest.dto.query.UserQuery;
import com.example.zzy.springbootTest.model.User;

import java.util.List;

/**
 * Created by zhangzhiyuan on 2017/8/15.
 */
public interface UserMapper {

    List<User> selectByConditions(UserQuery query);

}
