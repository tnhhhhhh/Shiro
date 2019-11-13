package com.tnh.shrio.service.impl;

import com.tnh.shrio.domain.Permissions;
import com.tnh.shrio.domain.Role;
import com.tnh.shrio.domain.User;
import com.tnh.shrio.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: TNH
 * @create: 2019/11/13 9:54
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public User getUserByName(String username) {
        //模拟从数据库查询用户信息
        return getMapByName(username);
    }

    private User getMapByName(String username){
        //创建查询的添加的权限
        Permissions permissions1=new Permissions("1","user:add");
        Permissions permissions2=new Permissions("2","user:update");
        //创建权限集合，并将创建好的权限放入进去
        List<Permissions> permissionsList=new ArrayList<>();
        permissionsList.add(permissions1);
        permissionsList.add(permissions2);
        //创建管理员角色，并放入集合
        Role role=new Role("1","admin",permissionsList);
        List<Role> roleList=new ArrayList<>();
        roleList.add(role);
        //创建用户，并放入集合
        User user1=new User("1","tnh","123",roleList);
        User user2=new User("2","tnh2","123",roleList);
        Map<String,User> map=new HashMap<>();
        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
        //根据传入的用户名返回对应的用户数据
        return map.get(username);
    }
}
