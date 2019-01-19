package com.reed.ustc.service.impl;

import com.reed.ustc.controller.LoginController;
import com.reed.ustc.mapper.LoginTicketMapper;
import com.reed.ustc.mapper.TbUserMapper;
import com.reed.ustc.pojo.LoginTicket;
import com.reed.ustc.pojo.LoginTicketExample;
import com.reed.ustc.pojo.TbUser;
import com.reed.ustc.pojo.TbUserExample;
import com.reed.ustc.service.UserService;
import com.reed.ustc.utils.WendaUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * created by reedfan on 2019/1/19
 */

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    TbUserMapper tbUserMapper;

    TbUserExample tbUserExample = null;

    TbUserExample.Criteria criteria = null;

    @Autowired
    LoginTicketMapper loginTicketMapper;
    LoginTicketExample loginTicketExample= null;
    LoginTicketExample.Criteria loginTicketCriteria = null;

    @Override
    public TbUser selctUserByName(String userName) {
        tbUserExample =  new TbUserExample();
        criteria = tbUserExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<TbUser> tbUserList = tbUserMapper.selectByExample(tbUserExample);
        if(tbUserList.size()>0){
            return tbUserList.get(0);
        }
        return null;
    }

    @Override
    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        TbUser user = this. selctUserByName(username);

        if (user != null) {
            map.put("msg", "用户名已经被注册");
            return map;
        }

        // 密码强度
        user = new TbUser();
        user.setUserName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadUrl(head);
        user.setPassword(WendaUtil.MD5(password+user.getSalt()));
        logger.info(user.toString());

        tbUserMapper.insertSelective(user);
        logger.info(user.toString());


        // 登陆
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }

    @Override
    public Map<String, Object> login(String username, String password) {

        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        TbUser user = this. selctUserByName(username);

        if (user == null) {
            map.put("msg", "用户名不存在");
            return map;
        }

        if (!WendaUtil.MD5(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }

        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        map.put("userId", user.getId());
        return map;

    }

    @Override
    public LoginTicket selectLoginTicketByticket(String ticket) {
        loginTicketExample = new LoginTicketExample();
        loginTicketCriteria = loginTicketExample.createCriteria();
        loginTicketCriteria.andTicketEqualTo(ticket);
        List<LoginTicket> loginTicketList = loginTicketMapper.selectByExample(loginTicketExample);
        if(loginTicketList.size()>0){
            return loginTicketList.get(0);
        }
        return null;
    }

    @Override
    public TbUser selectUserById(Integer userId) {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(userId);
        return tbUser;
    }

    @Override
    public void logout(String ticket) {
        loginTicketExample = new LoginTicketExample();
        loginTicketCriteria = loginTicketExample.createCriteria();
        loginTicketCriteria.andTicketEqualTo(ticket);
        LoginTicket loginTicket = this.selectLoginTicketByticket(ticket);
        if(loginTicket != null){
            loginTicket.setStatus(1);
        }
        loginTicketMapper.updateByPrimaryKeySelective(loginTicket);
    }

    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
         loginTicketMapper.insertSelective(ticket);

         return ticket.getTicket();
    }
}
