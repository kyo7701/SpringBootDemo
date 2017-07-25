package com.controller;

import com.entity.User;
import com.service.IUserService;
import com.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Author:Mr.Cris
 * Date:2017-06-24 22:17
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/all")
    public List<User> users() {
        return service.selectUser();
    }

    @Log(name = "测试log")
    @RequestMapping("/testRestTemplate")
    public String getHTML() {
        String url = "http://www.baidu.com";
        String html = restTemplate.getForObject(url,String.class);
        System.out.println(html);
        try {
            System.out.println(html.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return html;
    }

}
