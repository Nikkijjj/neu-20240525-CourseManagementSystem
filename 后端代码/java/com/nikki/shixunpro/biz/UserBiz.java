package com.nikki.shixunpro.biz;

import com.nikki.shixunpro.entity.Tenant;
import com.nikki.shixunpro.entity.User;
import com.nikki.shixunpro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserBiz {

    @Autowired
    private UserMapper userMapper;

    //登录
    public String login(String tenantName,String userName, String passowrd) {
        String tenantId = userMapper.selectUserByNameAndPw(userName, passowrd);
        if(tenantId == null) {
            tenantId = userMapper.selectTenantByName(tenantName);
        }
        return tenantId;
    }

    public List<User> searchUserByName(String username) {
        List<User> user = userMapper.selectUserByName(username);
        return user;
    }


    //注册添加用户信息到t_user
    public void addUser(User user) {
        this.userMapper.insertUser(user);
    }

    //注册添加企业信息到t_tenant
    public void addTenant(Tenant tenant) {
        this.userMapper.insertTenant(tenant);
    }


    //更新个人信息
    public void updateUser(User user) {
        this.userMapper.updateUser(user);
    }

    //获取旧密码
    public boolean checkOldPw(int userId, String oldPw){
        String storedPw = userMapper.selectPwByUserId(userId);

        if(storedPw != null && storedPw.equals(oldPw)){
            return true;
        }else {
            return false;
        }
    }

    public String findTenantLogoByUserName(String username) {
        String logo = userMapper.findTenantLogoByUserName(username);
        return logo;
    }

    //修改密码
    public void updatePw(@RequestParam("") int userId, String newPw) {
        this.userMapper.updatePassword(userId, newPw);
    }

    public void updatePassword(@RequestParam("") String username, String newPw) {
        this.userMapper.updatePassword2(username, newPw);
    }
}
