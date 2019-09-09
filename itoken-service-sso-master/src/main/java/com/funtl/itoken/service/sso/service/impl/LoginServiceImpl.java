package com.funtl.itoken.service.sso.service.impl;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.service.sso.mapper.TbSysUserMapper;
import com.funtl.itoken.service.sso.service.LoginService;
import com.funtl.itoken.service.sso.service.hystrix.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 李洋
 * @date 2019-08-29 11:07
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private TbSysUserMapper tbSysUserMapper;
    @Override
    public TbSysUser login(String loginCode, String password) {
        TbSysUser tbSysUser = null;
        String json = redisService.get(loginCode);
        //没数据
        if (json == null) {

            password = DigestUtils.md5DigestAsHex(password.getBytes());
            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode" , loginCode);
            tbSysUser = tbSysUserMapper.selectOneByExample(example);
            if (tbSysUser != null && tbSysUser.getPassword().equals(password)) {
                try {
                    redisService.put(loginCode, MapperUtils.obj2json(tbSysUser),  60L * 60 * 24);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tbSysUser;
            }
            return null;

        } else {
                try {
                    tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                } catch (Exception e) {
                    logger.warn(json);
                }
        }
        return tbSysUser;
    }
}
