package com.service.impl;

import com.conf.datasource.DataSource;
import com.dao.ITuserDao;
import com.entity.User;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:Mr.Cris
 * Date:2017-06-23 09:01
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ITuserDao dao;

    @DataSource(name = DataSource.master)
    public List<User> selectUser() {
        return dao.selectUser();
    }
}
