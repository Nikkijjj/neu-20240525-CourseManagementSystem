package com.nikki.shixunpro.biz;

import com.nikki.shixunpro.entity.SuperManager;
import com.nikki.shixunpro.mapper.SuperManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperManagerBiz {
    @Autowired

    private SuperManagerMapper superManagerMapper;
    public SuperManager login(String name, String password) {
        SuperManager superManager = superManagerMapper.selectSuperManagerByNameAndPw(name, password);
        return superManager;
    }

}
