package com.frame.rbac.controller;


import com.frame.rbac.base.BaseController;
import com.frame.rbac.entity.SysMenu;
import com.frame.rbac.service.impl.SysMenuServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController<SysMenuServiceImpl, SysMenu> {

}

