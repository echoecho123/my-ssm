package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;


    /**
     * 查询所有订单
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Orders> findAll(int page, int size) {
        //借助分页插件完成分页
        PageHelper.startPage(page,size);
        List<Orders> list = ordersDao.findAll();
        return list;
    }

    /**
     * 根据订单id查询一条订单
     * @param id
     * @return
     */
    @Override
    public Orders findById(String ordersId) {
       Orders orders = ordersDao.findById(ordersId);
        return orders;
    }
}
