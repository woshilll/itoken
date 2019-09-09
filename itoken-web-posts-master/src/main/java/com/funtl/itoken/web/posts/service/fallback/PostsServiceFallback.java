package com.funtl.itoken.web.posts.service.fallback;

import com.funtl.itoken.web.posts.service.PostsService;
import org.springframework.stereotype.Component;

/**
 * @author 李洋
 * @date 2019-09-03 17:30
 */
@Component
public class PostsServiceFallback implements PostsService {
    @Override
    public String page(int pageNum, int pageSize, String tbPostsPostJson) {
        return null;
    }

    @Override
    public String get(String postGuid) {
        return null;
    }

    @Override
    public String save(String tbPostsPostJson, String opt) {
        return null;
    }
}
