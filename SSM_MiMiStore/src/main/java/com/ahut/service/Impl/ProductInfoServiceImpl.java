package com.ahut.service.Impl;

import com.ahut.mapper.ProductInfoMapper;
import com.ahut.pojo.ProductInfo;
import com.ahut.pojo.ProductInfoExample;
import com.ahut.pojo.ProductInfoVo;
import com.ahut.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * 查询全部信息
     * @return
     */
    @Override
    public List<ProductInfo> getAll() {
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(new ProductInfoExample());
        return productInfos;
    }

    /**
     * 分页展示信息
     * @return
     */
    @Override
    public PageInfo<ProductInfo> splitPage(int pageNum,int pageSize) {
        //用PageHelper工具类设置分页大小
        PageHelper.startPage(pageNum, pageSize);
        //创建example用作查询条件
        ProductInfoExample productInfoExample = new ProductInfoExample();
        //查询条件是倒叙展示信息
        productInfoExample.setOrderByClause("p_id desc");
        //查询到的全部信息
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(productInfoExample);
        //将获取的信息存入分页信息PageInfo中
        PageInfo<ProductInfo> info = new PageInfo<>(productInfos);
        //返回分页信息对象
        return info;
    }

    /**
     * 添加商品
     * @param info
     * @return
     */
    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    /**
     * 根据id查询是商品信息
     * @param pId
     * @return
     */
    @Override
    public ProductInfo getById(int pId) {
        ProductInfo info = productInfoMapper.selectByPrimaryKey(pId);
        return info;
    }

    /**
     * 修改商品信息
     * @param info
     * @return
     */
    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    /**
     * 根据id删除
     * @param pId
     * @return
     */
    @Override
    public int delete(int pId) {
        return productInfoMapper.deleteByPrimaryKey(pId);
    }


    /**
     * 根据id的数组进行删除
     * @param pids
     * @return
     */
    @Override
    public int delete(String[] pids) {
        return productInfoMapper.deletebyIds(pids);
    }

    /**
     * 多条件查询
     * @param vo
     * @return
     */
    @Override
    public List<ProductInfo> selectCondition(ProductInfoVo vo) {
        return productInfoMapper.selectCondition(vo);
    }

    /**
     * d多条件查询的分页
     * @param vo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ProductInfo> selectByPage(ProductInfoVo vo, int pageSize) {
        //要分页先设置分页属性
        PageHelper.startPage(vo.getPage(),pageSize);
        List<ProductInfo> productInfos = productInfoMapper.selectCondition(vo);
        //查询的数据放在分页中
        PageInfo<ProductInfo> info = new PageInfo<>(productInfos);
        return info;
    }


}
