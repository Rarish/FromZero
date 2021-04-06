package com.frame.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.frame.rbac.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> getMenuPermsByUserId(Long userId);
}
