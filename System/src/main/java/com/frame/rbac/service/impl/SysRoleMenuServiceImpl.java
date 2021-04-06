package com.frame.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.rbac.entity.SysRoleMenu;
import com.frame.rbac.mapper.SysRoleMenuMapper;
import com.frame.rbac.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

}
