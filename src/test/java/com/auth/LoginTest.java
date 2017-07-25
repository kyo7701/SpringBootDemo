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
 * Date:2017-06-28 16:41
 */
public class LoginTest {

    @Test
    public void customRealmLogin() {
        Factory<SecurityManager> managerFactory = new IniSecurityManagerFactory("classpath:shiro-customrealm.ini");
        SecurityManager manager = managerFactory.getInstance();

        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("24k","12345678");
        subject.login(token);


    }

    @Test
    public void multiRealmLogin() {
        Factory<SecurityManager> managerFactory = new IniSecurityManagerFactory("classpath:shiro-multiplerealm.ini");
        SecurityManager manager = managerFactory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("24k","123456");
        subject.login(token);
    }

}
