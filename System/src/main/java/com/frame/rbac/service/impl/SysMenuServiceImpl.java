package com.frame.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.rbac.entity.SysMenu;
import com.frame.rbac.mapper.SysMenuMapper;
import com.frame.rbac.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public Set<String> getMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.getMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (perm != null) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
