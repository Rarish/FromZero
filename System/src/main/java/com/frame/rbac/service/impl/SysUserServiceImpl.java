package com.frame.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.rbac.entity.SysUser;
import com.frame.rbac.mapper.SysUserMapper;
import com.frame.rbac.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
