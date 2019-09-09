package com.funtl.itoken.service.posts.controller;

import com.funtl.itoken.common.domain.BaseDomain;
import com.funtl.itoken.common.domain.TbPostsPost;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.service.posts.service.TbPostsPostService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author 李洋
 * @date 2019-08-31 14:43
 */
@RestController
@RequestMapping(value = "/v1/posts")
public class PostController {
    @Autowired
    private TbPostsPostService<TbPostsPost> tbPostsPostService;
    /**
     * 通过文章的id来查找文章
     * @param postGuid
     * @return
     */
    @RequestMapping(value = "{postGuid}", method = RequestMethod.GET)
    public TbPostsPost get(
            @PathVariable("postGuid") String postGuid
    ) {
        TbPostsPost tbPostsPost = new TbPostsPost();
        tbPostsPost.setPostGuid(postGuid);
        tbPostsPost = tbPostsPostService.selectOne(tbPostsPost);
        return tbPostsPost;
    }

    /**
     * 新增或修改
     *
     * @param postsPostJson
     * @param opt
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(
            @RequestParam(required = true) String postsPostJson,
            @RequestParam(required = true) String opt
    ) {
        TbPostsPost tbPostsPost = null;
        int result = 0;
        try {
            tbPostsPost = MapperUtils.json2pojo(postsPostJson, TbPostsPost.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tbPostsPost != null) {
            if (StringUtils.isNotBlank(tbPostsPost.getPostGuid())) {
                //更新
                result = tbPostsPostService.update(tbPostsPost, opt);
                if (result > 0) {
                    return BaseResult.ok("更新成功");
                }
                return BaseResult.ok("更新失败");
            } else {
                //新增
                tbPostsPost.setPostGuid(UUID.randomUUID().toString());
                result = tbPostsPostService.insert(tbPostsPost, opt);
                if (result > 0) {
                    return BaseResult.ok("新增成功");
                }
                return BaseResult.ok("新增失败");
            }
        }
        return BaseResult.ok("数据异常");
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param tbPostsPostJson
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize,
            @RequestParam(required = false) String tbPostsPostJson
    ) {
        TbPostsPost tbPostsPost = null;
        if (StringUtils.isNotBlank(tbPostsPostJson)) {
            try {
                tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageInfo<TbPostsPost> pageInfo = tbPostsPostService.page(pageNum, pageSize, tbPostsPost);
        List<TbPostsPost> tbPostsPosts = pageInfo.getList();
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        return BaseResult.ok(tbPostsPosts, cursor);
    }
}
