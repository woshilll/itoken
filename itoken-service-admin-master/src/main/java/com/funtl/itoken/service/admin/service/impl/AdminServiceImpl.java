package com.funtl.itoken.service.admin.service.impl;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.service.mapper.TbSysUserMapper;
import com.funtl.itoken.common.service.service.impl.BaseServiceImpl;
import com.funtl.itoken.service.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 李洋
 * @date 2019-08-27 10:48
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService<TbSysUser> {

}
