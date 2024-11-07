package com.nikki.shixunpro.mapper;

import com.nikki.shixunpro.entity.Tenant;
import com.nikki.shixunpro.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT t_tenant.logo " +
            "FROM t_user " +
            "JOIN t_tenant ON t_user.tenantId = t_tenant.tenantId " +
            "WHERE t_user.username = #{username}")
    String findTenantLogoByUserName(@Param("username") String username);

    @Select("select tenantId from t_user where userName=#{username} and password=#{password}")
    String selectUserByNameAndPw(@Param("username") String userName, @Param("password") String password);

    //查找
    @Select("select tenantId from t_tenant where tenantName=#{tenantName}")
    String selectTenantByName(@Param("tenantName") String tenantName);

    //查找用户信息
    @Select("select nickname, phone, email, tenantId, role, createTime from t_user where username=#{username}")
    List<User> selectUserByName(@Param("username") String username);

    //查找指定用户的密码，返回找到的密码
    @Select("select password from t_user where userId=#{userId}")
    String selectPwByUserId(@Param("userId") int userId);

    //注册（添加）用户个人信息
    @Insert("insert into t_user values (null,#{username},#{password},#{role},#{nickname},#{gender},#{deptId},#{post},#{phone},#{email},#{state},#{createTime},#{description},#{tenantId})")
    int insertUser(User user);

    //注册或添加租户信息
    @Insert("insert into t_tenant values (#{tenantId},#{tenantName},#{logo},#{contactName},#{contactPhone},#{description})")
    int insertTenant(Tenant tenant);

    //更新用户信息
    @Update("update t_user set nickname=#{nickname},phone=#{phone},email=#{email},role=#{role}, tenantId=#{tenantId} where username=#{username}")
    int updateUser(User user);

    //更新密码
    @Update("update t_user set password=#{newPw} where userId={userId}")
    int updatePassword(@Param("userId") int userId, @Param("newPw") String newPw);

    //更新密码
    @Update("update t_user set password=#{newPw} where username={username}")
    int updatePassword2(@Param("username") String username, @Param("newPw") String newPw);


}

