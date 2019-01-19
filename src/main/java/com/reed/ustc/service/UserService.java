package com.reed.ustc.service;

import com.reed.ustc.pojo.LoginTicket;
import com.reed.ustc.pojo.TbUser;

import java.util.Map;

/**
 * created by reedfan on 2019/1/19 0019
 */
public interface UserService {
    Map<String, Object> login(String username, String password);
    Map<String, Object> register(String username, String password);

    TbUser selctUserByName(String userName);

    void logout(String ticket);
    LoginTicket selectLoginTicketByticket(String ticket);
    TbUser selectUserById(Integer userId);
}
