package com.frame.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.rbac.entity.SysRole;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRolePermissionByUserId(Long userId);
}
