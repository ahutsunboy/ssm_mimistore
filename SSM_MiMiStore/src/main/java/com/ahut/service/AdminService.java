package com.ahut.service;

import com.ahut.pojo.Admin;

public interface AdminService {

    /**
     * 查询登录用户
     */
    Admin login(String name,String password);
}
