package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //未分页的查询所有
  /*  @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<Orders> list = ordersService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("ordersList",list);
        mv.setViewName("orders-list");
        return mv;
    }*/

    /**
     * 带分页的查询所有
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page" ,required = true,defaultValue = "1") int page,@RequestParam(name = "size",required = true,defaultValue = "4") int size){

        List<Orders> list = ordersService.findAll(page,size);
        ModelAndView mv = new ModelAndView();
        PageInfo info = new PageInfo(list);
        mv.addObject("pageInfo",info);
        mv.setViewName("orders-page-list");
        return mv;
    }

    /**
     * 根据订单id查询一条订单记录
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true) String ordersId){

        Orders orders = ordersService.findById(ordersId);
        ModelAndView mv  = new ModelAndView();
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;


    }
}
