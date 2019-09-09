package com.funtl.itoken.common.service.mapper;
import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.service.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.MyMapper;
/**
 * @author 李洋
 * @date 2019-08-31 09:00
 */

@CacheNamespace(implementation = RedisCache.class)
public interface TbSysUserMapper extends MyMapper<TbSysUser> {
}