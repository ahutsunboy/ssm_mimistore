package com.ahut.service;

import com.ahut.pojo.ProductInfo;
import com.ahut.pojo.ProductInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {

    /**
     * 获取全部产品信息
     */
    List<ProductInfo> getAll();
    PageInfo<ProductInfo> splitPage(int pageNum,int pageSize);


    /**
     * 添加商品
     * @param info
     * @return
     */
    int save(ProductInfo info);

    /**
     * 根据id查询商品信息
     * @param pId
     * @return
     */
    ProductInfo getById(int pId);


    /**
     * 修改商品信息
     * @param info
     * @return
     */
    int update(ProductInfo info);

    /**
     * 数据id删除
     * @param pId
     * @return
     */
    int delete(int pId);

    int delete(String[] pids);

    /**
     * 多条件查询
     * @param vo
     * @return
     */
    List<ProductInfo> selectCondition(ProductInfoVo vo);


    /**
     * 多条件查询的分页
     * @param vo
     * @param pageSize
     * @return
     */
    PageInfo<ProductInfo> selectByPage(ProductInfoVo vo,int pageSize);


}
