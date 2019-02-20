package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    /**
     * 查询所有订单
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(id=true,property = "orderNum",column = "orderNum"),
            @Result(id=true,property = "orderTime",column = "orderTime"),
            @Result(id=true,property = "orderStatus",column = "orderStatus"),
            @Result(id=true,property = "peopleCount",column = "peopleCount"),
            @Result(id=true,property = "payType",column = "payType"),
            @Result(id=true,property = "orderDesc",column = "orderDesc"),
            @Result(id=true,property = "product",column = "productId",javaType = Product.class,one=@One(select="com.itheima.ssm.dao.IProductDao.findById"))
    })
    public List<Orders> findAll();


    /**
     * 根据ordersId ,查询一条orders
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one=@One(select="com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one=@One(select="com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id", javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId" ))
    })
    Orders findById(String ordersId);
}
