package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    /**
     * 根据roleId查询permission
     * @param roleId
     * @return
     */

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId} )")
    List<Permission> findPermissionByRoleId(String roleId);

    /**
     * 查询所有permission
     * @return
     */

    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * 新建permission
     * @param permission
     */

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
