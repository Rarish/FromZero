package com.frame.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.LoginUser;
import com.exception.JwtException;
import com.frame.rbac.entity.SysUser;
import com.frame.rbac.service.SysMenuService;
import com.frame.rbac.service.SysRoleService;
import com.frame.rbac.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LoginService {
    @Autowired
    private SysUserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysMenuService menuService;

    public String login(String name, String password){
        SysUser user = userService.getOne(new QueryWrapper<SysUser>().eq("user_name",name).eq("password",password));
        if(user != null){
            String uuid = tokenService.isLoginAlready(user.getUserId());

            Set<String> perms = menuService.getMenuPermsByUserId(user.getUserId());
            LoginUser loginUser = new LoginUser();
            loginUser.setPermission(perms);
            loginUser.setUser(user);

            if(uuid != null){
               loginUser.setUuid(uuid);
            }
            return tokenService.createToken(loginUser);
        }else {
            throw new JwtException("用户名或密码错误");
        }
    }

    public Boolean logOut(String token){
        return tokenService.deleteLoginUser(token);
    }
}
