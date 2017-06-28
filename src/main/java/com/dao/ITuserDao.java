package com.dao;

import com.entity.User;

import java.util.List;

/**
 * Author:Mr.Cris
 * Date:2017-06-21 11:28
 */
public interface ITuserDao {
    List<User> selectUser();

}
