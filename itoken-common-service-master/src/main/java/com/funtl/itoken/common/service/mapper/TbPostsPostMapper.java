package com.funtl.itoken.common.service.mapper;

import com.funtl.itoken.common.domain.TbPostsPost;
import com.funtl.itoken.common.service.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.MyMapper;
@CacheNamespace(implementation = RedisCache.class)
public interface TbPostsPostMapper extends MyMapper<TbPostsPost> {
}