package com.ahut.service.Impl;

import com.ahut.mapper.ProductTypeMapper;
import com.ahut.pojo.ProductType;
import com.ahut.pojo.ProductTypeExample;
import com.ahut.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    /**
     * 获取全部类别
     * @return
     */
    @Override
    public List<ProductType> getAll() {
        ProductTypeExample productTypeExample = new ProductTypeExample();
        List<ProductType> types = productTypeMapper.selectByExample(productTypeExample);
        return types;
    }
}
