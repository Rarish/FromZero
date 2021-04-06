package com.frame.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.frame.rbac.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<SysRole> selectRolePermissionByUserId(Long userId);
}
