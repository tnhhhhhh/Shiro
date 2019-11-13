package com.tnh.shrio.config;

import com.tnh.shrio.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * shrio配置类
 * @author: TNH
 * @create: 2019/11/13 11:05
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建 ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器，在下面
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        /**
         * Shiro内置过滤器，用于实现权限相关拦截
         * anon：无需认证即可访问
         * authc：必须认证才能访问
         * user：如果使用了 rememberMe的功能可以直接访问
         * role：该资源必须得到角色权限才能访问
         * perms：该资源必须得到资源权限才能访问
         */
        Map<String,String> filterMap=new LinkedHashMap<>();

        //用户添加页面需要认证才能访问
        filterMap.put("/add","authc");
        //设置未认证的页面
        shiroFilterFactoryBean.setLoginUrl("toLogin");

        //用户修改页面需要授权 user:update(字符串可随意填) 才能访问
        filterMap.put("/update","perms[user:update]");
        //设置未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("unAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm，在下面
        securityManager.setRealm(getRealm());
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
