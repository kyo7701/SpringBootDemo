package com.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;


/**
 * Author:Mr.Cris
 * Date:2017-06-28 16:00
 * ini硬编码方式身份认证
 */
public class AuthSimpleExample1 {
    @Test
    public void testAuth() {
        Factory<SecurityManager> managerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = managerFactory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("24k","123456");
        try {
            subject.login(token);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
