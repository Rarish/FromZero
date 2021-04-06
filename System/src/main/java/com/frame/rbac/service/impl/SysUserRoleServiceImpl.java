package com.frame.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.rbac.entity.SysUserRole;
import com.frame.rbac.mapper.SysUserRoleMapper;
import com.frame.rbac.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
