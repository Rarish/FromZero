package com.entity;

import com.frame.rbac.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class LoginUser implements Serializable {
    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 权限列表
     */
    private Set<String> permission;

    /**
     * 用户信息
     */
    private SysUser user;
}
