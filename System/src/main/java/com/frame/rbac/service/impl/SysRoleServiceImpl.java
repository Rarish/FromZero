package com.frame.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.rbac.entity.SysRole;
import com.frame.rbac.mapper.SysRoleMapper;
import com.frame.rbac.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> roles = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> userRoles = new HashSet<>();
        for(SysRole role : roles){
            if(role != null){
                userRoles.addAll(Arrays.asList(role.getRoleKey().trim().split(",")));
            }
        }
        return userRoles;
    }
}
