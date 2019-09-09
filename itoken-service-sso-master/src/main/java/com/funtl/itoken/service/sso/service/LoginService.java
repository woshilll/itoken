package com.funtl.itoken.service.sso.service;


import com.funtl.itoken.common.domain.TbSysUser;

/**
 * @author 李洋
 * @date 2019-08-29 11:06
 */
public interface LoginService {
    public TbSysUser login(String loginCode, String password);
}
