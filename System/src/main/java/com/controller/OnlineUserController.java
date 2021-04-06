package com.controller;

import com.constant.Constants;
import com.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.response.ResponseMessage;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/OnlineUser")
public class OnlineUserController {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/all")
    public ResponseMessage getAllOnline(){
        Map userInfo = new HashMap();
        Set<String> keys = redisTemplate.keys("*");
        for(String key : keys){
            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
            LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get(key);
            userInfo.put("uuid",loginUser.getUuid());
            userInfo.put("username",loginUser.getUser().getUserName());
            userInfo.put("loginTime", sdf.format(loginUser.getLoginTime()));
            userInfo.put("expireTime",sdf.format(loginUser.getExpireTime()));
            userInfo.put("ip",loginUser.getIpaddr());
        }
        return ResponseMessage.success(userInfo);
    }

    @PostMapping("/lineOff")
    public ResponseMessage kickOut(@RequestBody Map map){
        if(redisTemplate.delete(Constants.LOGIN_TOKEN_KEY + map.get("uuid"))){
            return ResponseMessage.success();
        }else {
            return ResponseMessage.fail();
        }

    }
}
