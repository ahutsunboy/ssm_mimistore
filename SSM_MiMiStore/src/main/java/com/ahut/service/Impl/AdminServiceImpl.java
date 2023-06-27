package com.ahut.service.Impl;

import com.ahut.mapper.AdminMapper;
import com.ahut.pojo.Admin;
import com.ahut.pojo.AdminExample;
import com.ahut.service.AdminService;
import com.ahut.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String name,String password) {
        //根据传入的用户到数据库中查询相应的用户对象
        //如果有查询条件，则一定要创建AdminExample的对象，用来封装条件
        AdminExample example = new AdminExample();
        //添加查询条件，都要先进行createCriteria创造条件，然后再添加条件
        example.createCriteria().andANameEqualTo(name);
        //将设置了查询条件的信息传给adminMapper对象
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size() > 0){
            Admin admin = list.get(0);
            //将查到的的对象进行对比确认
            String passwordMD5 = MD5Util.getMD5(password);
            if(passwordMD5.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
