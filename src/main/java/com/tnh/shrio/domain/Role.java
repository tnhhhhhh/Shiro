package com.tnh.shrio.domain;

import lombok.Data;

import java.util.List;

/**
 * 角色类
 * @author: TNH
 * @create: 2019/11/13 9:48
 */
@Data
public class Role {
    private String id;
    private String roleName;
    private List<Permissions> permissionsList;

    public Role() {
    }

    public Role(String id, String roleName, List<Permissions> permissionsList) {
        this.id = id;
        this.roleName = roleName;
        this.permissionsList = permissionsList;
    }
}
