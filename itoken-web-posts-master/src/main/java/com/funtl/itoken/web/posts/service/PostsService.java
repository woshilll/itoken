package com.funtl.itoken.web.posts.service;

import com.funtl.itoken.common.web.service.BaseClientService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 李洋
 * @date 2019-09-03 17:24
 */
@FeignClient(value = "itoken-service-posts")
public interface PostsService extends BaseClientService {

    @Override
    @RequestMapping(value = "v1/posts/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    String page(
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize,
            @RequestParam("tbPostsPostJson") String tbPostsPostJson
    );
    @RequestMapping(value = "v1/posts/{postGuid}", method = RequestMethod.GET)
    String get(
            @PathVariable("postGuid") String postGuid
    );
    @RequestMapping(value = "v1/posts", method = RequestMethod.POST)
    String save(
           @RequestParam(value = "postsPostJson",required = true) String tbPostsPostJson,
           @RequestParam(value = "opt",required = true) String opt
    );
}
