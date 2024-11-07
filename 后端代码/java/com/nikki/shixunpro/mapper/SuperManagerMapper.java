package com.nikki.shixunpro.mapper;

import java.util.List;

import com.nikki.shixunpro.entity.SuperManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SuperManagerMapper {
    @Select("select * from t_supermanager")
    List<SuperManager> selectSuperManager();

    @Select("select * from t_supermanager where name=#{name}")
    SuperManager selectSuperManagerByName(@Param("name") String name);

    @Select("select * from t_supermanager where name=#{name} and password=#{password}")
    SuperManager selectSuperManagerByNameAndPw(@Param("name") String name, @Param("password") String password);

}
