package com.example.zzy.springbootTest.mapper;

import com.example.zzy.springbootTest.model.Manager;

public interface ManagerMapper {

    Manager selectByPrimaryKey(Integer id);

}