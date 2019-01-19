package com.reed.ustc.controller;

import com.reed.ustc.utils.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by reedfan on 2019/1/19 0019
 */

@RestController
public class HelloController {
    @GetMapping("/haha")
    public String haha(){
        RedisUtils.set("aha","ss");
       String res = (String) RedisUtils.get("aha");
       return res;
    }
}
