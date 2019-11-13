package com.tnh.shrio;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShrioApplicationTests {
    @Test
    void fun1() {
        //加载配置文件，返回工厂
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shrio.ini");
        //通过工厂创建实例
        SecurityManager instance = iniSecurityManagerFactory.getInstance();
        //SecurityUtils设置刚创建好的实例
        SecurityUtils.setSecurityManager(instance);
        //通过实例获取主体
        Subject subject = SecurityUtils.getSubject();
        //模拟用户名和密码
        UsernamePasswordToken tnh = new UsernamePasswordToken("tnh", "123");
        //认证，失败会报错
        subject.login(tnh);
        //获取成功信息
        boolean isOk = subject.isAuthenticated();
        System.out.println(isOk);
    }
}
