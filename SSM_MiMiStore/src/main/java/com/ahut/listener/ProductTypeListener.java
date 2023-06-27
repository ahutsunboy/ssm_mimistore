package com.ahut.listener;

import com.ahut.pojo.ProductType;
import com.ahut.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //手工从spring容器中取出ProductTypeServiceImpl对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTypeService productTypeService = context.getBean(ProductTypeService.class);
        List<ProductType> typeList = productTypeService.getAll();
        //放到全局作用域
        servletContextEvent.getServletContext().setAttribute("typeList",typeList);
        //打印测试
        typeList.forEach(System.out::println);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}