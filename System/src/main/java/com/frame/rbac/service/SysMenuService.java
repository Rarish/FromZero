package com.frame.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.rbac.entity.SysMenu;

import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> getMenuPermsByUserId(Long userId);
}
