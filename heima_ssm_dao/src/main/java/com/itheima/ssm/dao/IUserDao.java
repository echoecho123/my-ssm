package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IUserDao {

    /**
     * 根据用户名查询用户，用于登录验证
     * @param username
     * @return
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findUserByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param userInfo
     */
    @Insert("insert into users(username,password,email,phoneNum,status) values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    /**
     * 根据id查询用户
     * @param usersId
     * @return
     */

    @Select("select * from users where id = #{usersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String usersId);


    /**
     * 根据usersId查询所有能添加的role
     * @param usersId
     * @return
     */

    @Select("select * from role where id not in (select roleId from users_role where userId = #{usersId})")
    List<Role> findOtherRolesByUsersId(String usersId);


    /**
     * 两个以上独立参数，要用@Param("参数名")来标记，不然，参数无法识别
     * 向users_role中添加记录
     * @param userId
     * @param roleId
     */

    @Insert("insert into users_role values (#{userId},#{roleId}) ")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
