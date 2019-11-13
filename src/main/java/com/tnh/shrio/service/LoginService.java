package com.tnh.shrio.service;

import com.tnh.shrio.domain.User;

/**
 * @author: TNH
 * @create: 2019/11/13 9:54
 */
public interface LoginService {
    /**
     * 根据用户名查询对应用户数据
     * @param username 用户名
     * @return 用户数据
     */
    User getUserByName(String username);
}
