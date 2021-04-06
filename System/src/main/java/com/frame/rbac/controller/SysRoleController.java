package com.frame.rbac.controller;

import com.frame.rbac.base.BaseController;
import com.frame.rbac.entity.SysRole;
import com.frame.rbac.service.impl.SysRoleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController<SysRoleServiceImpl, SysRole> {

}

