package com.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Author:Mr.Cris
 * Date:2017-07-03 09:18
 */
public class UserRealm2 implements Realm {


    @Override
    public String getName() {
        return "myRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());
        if (!"24k".equals(username)) {
            throw new UnknownAccountException();
        }

        if ("123456".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
