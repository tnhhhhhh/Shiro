package com.tnh.shrio.shiro;

import com.tnh.shrio.domain.Permissions;
import com.tnh.shrio.domain.Role;
import com.tnh.shrio.domain.User;
import com.tnh.shrio.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: TNH
 * @create: 2019/11/13 10:43
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户登陆名
        String name = principalCollection.getPrimaryPrincipal().toString();
        //根据用户名查询用户数据
        User user = loginService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoleList()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //权限
            for (Permissions permissions : role.getPermissionsList()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = loginService.getUserByName(token.getPrincipal().toString());
        if (user==null){
            return null;
        }
        //参数（ 用户名，密码，[加盐及次数]，当前类的名字 ）
        //密码必填其他可以空，SimpleAuthenticationInfo("",pwd,"");
        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
    }
}
