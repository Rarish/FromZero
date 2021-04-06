package com.frame.rbac.controller;

import com.frame.rbac.base.BaseController;
import com.frame.rbac.entity.SysUserRole;
import com.frame.rbac.service.impl.SysUserRoleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户和角色关联表 前端控制器
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController extends BaseController<SysUserRoleServiceImpl, SysUserRole> {

}

