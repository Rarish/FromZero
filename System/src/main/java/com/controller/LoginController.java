package com.controller;

import com.constant.Constants;
import com.frame.rbac.entity.SysUser;
import com.frame.rbac.service.SysUserService;
import com.frame.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.response.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SysUserService userService;

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody Map map){
        String token = loginService.login((String) map.get("username"),(String) map.get("password"));
        return ResponseMessage.success("登陆成功!", Constants.TOKEN_PREFIX + token);
    }

    @PostMapping("/logOut")
    public ResponseMessage logOut(HttpServletRequest request){
        loginService.logOut(request.getHeader(Constants.TOKEN));
        return ResponseMessage.success("登出成功");
    }

    @PostMapping("/signIn")
    public ResponseMessage signIn(@RequestBody SysUser user){
        if(userService.save(user)){
            String token = Constants.TOKEN_PREFIX + loginService.login(user.getUserName(),user.getPassword());
            return ResponseMessage.success("登陆成功!", token);
        }else {
            return ResponseMessage.fail();
        }
    }
}
