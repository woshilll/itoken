package com.funtl.itoken.service.posts.service.impl;

import com.funtl.itoken.common.domain.TbPostsPost;
import com.funtl.itoken.common.service.mapper.TbPostsPostMapper;
import com.funtl.itoken.common.service.service.impl.BaseServiceImpl;
import com.funtl.itoken.service.posts.service.TbPostsPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 李洋
 * @date 2019-08-31 14:41
 */
@Service
@Transactional(readOnly = true)
public class TbPostsPostServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostMapper> implements TbPostsPostService<TbPostsPost> {
}
