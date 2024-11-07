package com.nikki.shixunpro.controller;

import com.nikki.shixunpro.biz.SuperManagerBiz;
import com.nikki.shixunpro.entity.SuperManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/supermanager")
public class SuperManagerController {

    @Autowired
    private SuperManagerBiz biz;

    @RequestMapping("/login")
    public Map login(String name, String password) {
        Map result = new HashMap<>();
        SuperManager superManager = biz.login(name, password);

        if(superManager != null){
            result.put("isOk",true);
            result.put("msg","超级管理员登录成功！");
        }else {
            result.put("isOk",false);
            result.put("msg","用户名或密码错误！");
        }
        return result;
    }

}

