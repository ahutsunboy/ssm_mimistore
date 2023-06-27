package com.ahut.controller;

import com.ahut.pojo.ProductType;
import com.ahut.service.ProductInfoService;
import com.ahut.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/???")
public class ProductTypeAction {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/??")
    public String getAllType(Model model){
        List<ProductType> types = productTypeService.getAll();
        model.addAttribute("types",types);
        return "???";
    }

}
