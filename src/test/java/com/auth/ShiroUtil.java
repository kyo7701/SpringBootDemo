package com.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;

/**
 * Author:Mr.Cris
 * Date:2017-07-04 20:33
 */
public class ShiroUtil {


    public static SecurityManager getManager(String configPath) {
        Factory<SecurityManager> managerFactory = new IniSecurityManagerFactory(configPath);
        SecurityManager manager = managerFactory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        return manager;
    }

}
