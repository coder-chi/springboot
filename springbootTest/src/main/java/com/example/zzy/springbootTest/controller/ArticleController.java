package com.example.zzy.springbootTest.controller;

import com.example.zzy.springbootTest.constant.CommonSymbol;
import com.example.zzy.springbootTest.dto.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/"+ CommonSymbol.PORTAL+"/user")
public class ArticleController {
    @RequestMapping("/article")
    public Result article(){
        System.out.println("article seccess");
        return new Result("200","success",null);
    }
}
