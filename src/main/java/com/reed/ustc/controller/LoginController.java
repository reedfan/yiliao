package com.reed.ustc.controller;

import com.reed.ustc.common.CommonRet;
import com.reed.ustc.pojo.TbUser;
import com.reed.ustc.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * created by reedfan on 2019/1/19 0019
 */

@RestController("/user")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;


    @ApiOperation(value = "跳转到登录界面")
    @GetMapping("/reglogin")
    public ModelAndView regloginPage(Model model, @RequestParam(value = "next", required = false) String next) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("next", next);
        return modelAndView;
    }

    @ApiOperation(value = "注册")
    @RequestMapping(path = {"/reg/"}, method = {RequestMethod.POST})
    public ModelAndView reg( @RequestParam("username") String username,
                      @RequestParam("password") String password,
           //           @RequestParam("next") String next,
                @ApiParam("是否记住密码") @RequestParam(value="rememberme", defaultValue = "false") boolean rememberme,
                      HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> result = modelAndView.getModel();
        try {
            Map<String, Object> map = userService.register(username, password);
            if (map.containsKey("ticket")) {
                modelAndView.setViewName("/hello");
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if (rememberme) {
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);
//                if (StringUtils.isNotBlank(next)) {
//                    modelAndView.setViewName(next);
//                }
                return modelAndView;
            } else {
                modelAndView.setViewName("/login");

                logger.info(map.get("msg").toString());
                result.put("msg",map.get("msg"));
            }

        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());

        }
        return modelAndView;
    }


    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ModelAndView login( @RequestParam("username") String username,
                              @RequestParam("password") String password,
                       //       @RequestParam(value="next", required = false) String next,
                              @RequestParam(value="rememberme", defaultValue = "false") boolean rememberme,
                              HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("hello");
        try {
            Map<String, Object> result = modelAndView.getModel();
            Map<String, Object> map = userService.login(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if (rememberme) {
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);
            }else {
                modelAndView.setViewName("/login");

                logger.info(map.get("msg").toString());
                result.put("msg",map.get("msg"));
            }

        } catch (Exception e) {
            logger.error("登陆异常" + e.getMessage());

        }
        return modelAndView;
    }
}
