package com.example.zzy.springbootTest.controller;

import com.example.zzy.springbootTest.dto.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/a")
    public Result greet(){
        System.out.println("test");
        return new Result("200","success");
    }


}
