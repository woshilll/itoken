package com.funtl.itoken.service.posts.mapper;

import com.funtl.itoken.common.domain.TbPostsPost;
import com.funtl.itoken.common.service.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.MyMapper;

/**
 * @author yangge666
 */
public interface TbPostsPostExtendMapper extends MyMapper<TbPostsPost> {
}