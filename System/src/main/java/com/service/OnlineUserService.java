package com.service;

import com.frame.rbac.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineUserService {
    @Autowired
    private RedisTemplate redisTemplate;

    public List<SysUser> getAllOnlineUser(){
        redisTemplate.opsForValue();
        return null ;
    }
}
