package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    @Select("select * from role where id in(select roleId from users_role where userId = #{userId})")
    @Results({
         @Result(id = true,property = "id",column = "id"),
         @Result(property = "roleName",column = "roleName"),
         @Result(property = "roleDesc",column = "roleDesc"),
         @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId);


    /**
     * 查询所有角色
     * @return
     */

    @Select("select * from role")
    List<Role> findAll();


    /**
     * 新建角色
     * @param role
     */

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据roleId查询一个role
     * @param roleId
     * @return
     */
    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId);

    /**
     * 根据roleId查询可以添加的权限
     * @param roleId
     * @return
     */

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissionsByRoleId(String roleId);

    /**
     * 给role添加permission
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission  values (#{permissionId},#{roleId}) ")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);





}
