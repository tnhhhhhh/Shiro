package com.tnh.shrio.domain;

import lombok.Data;

/**
 * 权限类
 * @author: TNH
 * @create: 2019/11/13 9:50
 */
@Data
public class Permissions {
    private String id;
    private String permissionsName;

    public Permissions() {
    }

    public Permissions(String id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }
}
