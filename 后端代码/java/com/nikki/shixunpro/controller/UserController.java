package com.nikki.shixunpro.controller;

import com.nikki.shixunpro.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nikki.shixunpro.biz.UserBiz;
import com.nikki.shixunpro.entity.Tenant;
import com.nikki.shixunpro.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBiz biz;

    //注册
    @RequestMapping("/addUser")
    public Map addUser(@RequestBody User user) {
        this.biz.addUser(user);
        Map result = new HashMap<>();
        result.put("isOk", true);
        result.put("msg","用户个人信息注册成功！");
        return result;
    }

    //注册租户信息
    @RequestMapping("/addTenant")
    public Map addTenant(@RequestBody Tenant tenant) {
        this.biz.addTenant(tenant);
        Map result = new HashMap<>();
        result.put("isOk", true);
        result.put("msg","租户信息注册成功");
        return result;
    }

    //登录
    @RequestMapping("/login")
    public Map login(String tenantName, String userName, String password) {
        Map result = new HashMap<>();
        String tenantId = biz.login(tenantName, userName, password);
        if (tenantId != null) {
            result.put("isOk", true);
            result.put("msg","登录成功！");
        }else {
            result.put("isOk", false);
            result.put("msg","登录失败！");
        }
        return result;
    }

    //更新个人信息
    @RequestMapping("/updateInfo")
    public Map updateInfo(User user) {
        this.biz.updateUser(user);
        Map result = new HashMap<>();
        result.put("isOk", true);
        result.put("msg","更新个人信息成功！");
        return result;
    }


    //修改密码
    @RequestMapping("/updatePw")
    public Map updatePw(String username, String newPw) {
        Map result = new HashMap<>();


        return result;
    }

    //依据username查询用户信息
    @RequestMapping("/searchByName")
    public Map searchUserByName(@RequestParam String username){
        Map result = new HashMap<>();
        List<User> user = biz.searchUserByName(username);
        if(user != null){
            result.put("isOk",true);
            result.put("msg","查询课程成功！");
            result.put("user",user);
        }else {
            result.put("isOk",false);
            result.put("msg","查询课程失败！");
        }
        return result;
    }

    //依据id查询租户Logo
    @RequestMapping("/searchLogo")
    public Map findTenantLogoByUserId(@RequestParam String username){
        Map result = new HashMap<>();
        String logo = biz.findTenantLogoByUserName(username);
        if(logo !=  null){
            result.put("isOk",true);
            result.put("msg","查询课程成功！");
            result.put("user",logo);
        }else {
            result.put("isOk",false);
            result.put("msg","查询课程失败！");
        }
        return result;
    }

}

