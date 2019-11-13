package com.tnh.shrio.domain;

import lombok.Data;

import java.util.List;

/**
 * 用户实体类
 * @author: TNH
 * @create: 2019/11/13 9:45
 */
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private List<Role> roleList;

    public User(String id, String username, String password, List<Role> roleList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }
}
