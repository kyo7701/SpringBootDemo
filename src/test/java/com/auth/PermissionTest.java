package com.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;


/**
 * Author:Mr.Cris
 * Date:2017-07-04 20:41
 */
public class PermissionTest {

    /**基于角色的访问权限控制(查看是否拥有对应角色，根据角色去区分权限)
     * 隐式角色控制粒度较粗(通过判断角色来判断相应权限，需要更改角色的权限时候可能会改动多处代码)
     * @param
     * @return
     */
    @Test
    public void checkPermission() {
        SecurityManager manager = ShiroUtil.getManager("classpath:shiro-permissions.ini");
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        boolean flag = subject.hasAllRoles(Arrays.asList("role1","role2"));
        System.out.println(flag);
    }

    /**
     * 基于资源的访问权限控制(通过资源聚合权限，粒度较细)
     * @param
     * @return
     */
    @Test
    public void checkExplicitPermission() {
        SecurityManager manager= ShiroUtil.getManager("classpath:shiro-explicit.ini");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken zhangToken = new UsernamePasswordToken("zhang","123456");
        subject.login(zhangToken);
        System.out.println(subject.isPermitted("user:create"));

        UsernamePasswordToken wangToken = new UsernamePasswordToken("wang","123456");
        subject.login(wangToken);
        System.out.println(subject.isPermitted("user:create"));

        UsernamePasswordToken liToken = new UsernamePasswordToken("li","123456");
        subject.login(liToken);
        System.out.println(subject.isPermitted("user:create"));

    }




}
