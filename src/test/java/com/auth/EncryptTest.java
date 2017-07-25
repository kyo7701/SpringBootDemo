package com.auth;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * Author:Mr.Cris
 * Date:2017-07-24 21:11
 */
public class EncryptTest {

    /**
     * 用户名+密码+盐 ->hash
     * @param
     * @return
     */
    @Test
    public void testHash() {
        String userName = "zhang";
        String password = "hello";
        String pwd = userName + password;
        String UUID = java.util.UUID.randomUUID().toString();
        String md5P = new Md5Hash(pwd,UUID).toString();
        System.out.println(md5P);
    }

    /**
    *密码验证方式
    *传入的用户名密码加盐Base64 ->hash与数据库里面做比较
    */
    @Test
    public void testHash2() {
        String username = "zhang";
        String password = "hello";
        String pwd = username + password;
        String pwdEnc = Base64.encodeToString(pwd.getBytes());
        System.out.println(pwdEnc);
        String UUID = java.util.UUID.randomUUID().toString();
        String salt = UUID;
        String hash = new Md5Hash(pwdEnc,UUID).toString();
        String saltHash = new Md5Hash(pwdEnc,salt).toString();
        System.out.println(hash);
        System.out.println("-------------salt -----------------");
        System.out.println(saltHash);
    }

}
