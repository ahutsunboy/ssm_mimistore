package com.ahut.ssm;

import com.ahut.mapper.ProductInfoMapper;
import com.ahut.pojo.ProductInfo;
import com.ahut.pojo.ProductInfoVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml","classpath:applicationContext_service.xml"})
public class TestDemo1 {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Test
    public void textSelectCondition(){
        ProductInfoVo productInfoVo = new ProductInfoVo();
        productInfoVo.setPname("4");
        productInfoVo.setLprice(2999);
        productInfoVo.setHprice(7999);
        /*productInfoVo.setTypeid(1);*/
        List<ProductInfo> productInfos = productInfoMapper.selectCondition(productInfoVo);
        productInfos.forEach(System.out::println);
    }
}
