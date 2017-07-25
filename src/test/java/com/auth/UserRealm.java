package com.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Author:Mr.Cris
 * Date:2017-06-28 16:08
 * 从指定数据源获取安全数据
 */
public class UserRealm implements Realm {



    @Override
    public String getName() {
        return "userRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {//仅支持用户名密码模式的认证
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取传入的配置的安全信息
        String username = (String)authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());
        //这里可以指定自定义数据源,对数据源存储的信息进行简单的比对
        if (!"24k".equals(username)) {
            throw new UnknownAccountException();
        }

        if (!"123456".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //成功返回
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
