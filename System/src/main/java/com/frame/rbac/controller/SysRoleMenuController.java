package com.frame.rbac.controller;

import com.frame.rbac.base.BaseController;
import com.frame.rbac.entity.SysRoleMenu;
import com.frame.rbac.service.impl.SysRoleMenuServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色和菜单关联表 前端控制器
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@RestController
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController extends BaseController<SysRoleMenuServiceImpl, SysRoleMenu> {

}

