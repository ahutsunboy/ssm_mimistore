package com.ahut.mapper;

import com.ahut.pojo.ProductInfo;
import com.ahut.pojo.ProductInfoExample;
import com.ahut.pojo.ProductInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoMapper {
    int countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Integer pId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    ProductInfo selectByPrimaryKey(Integer pId);

    int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    /**
     * 根据多个id删除
     * @param ids
     * @return
     */
    int deletebyIds(String[] ids);

    /**
     * 多条件查询
     */
    List<ProductInfo> selectCondition(ProductInfoVo productInfoVo);
}