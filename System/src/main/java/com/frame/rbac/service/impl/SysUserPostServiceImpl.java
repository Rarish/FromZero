package com.frame.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.rbac.entity.SysUserPost;
import com.frame.rbac.mapper.SysUserPostMapper;
import com.frame.rbac.service.SysUserPostService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与岗位关联表 服务实现类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-03-04
 */
@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements SysUserPostService {

}
