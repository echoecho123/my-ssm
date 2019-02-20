package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询所有产品
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        System.out.println("表现层方法执行了");
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList",ps);
        mv.setViewName("product-list");
        return mv;

    }

    /**
     * 新建product
     * @param product
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);

        return "redirect:findAll.do";
    }


}
