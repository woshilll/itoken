package com.funtl.itoken.web.posts.controller;

import com.funtl.itoken.common.domain.TbPostsPost;
import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.common.web.constants.WebConstants;
import com.funtl.itoken.common.web.controller.BaseController;
import com.funtl.itoken.web.posts.service.PostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 李洋
 * @date 2019-09-03 15:59
 */
@Controller
public class WebPostsController extends BaseController<TbPostsPost, PostsService> {
    @Autowired
    private PostsService postsService;

    @ModelAttribute
    public TbPostsPost first(String postGuid, HttpServletRequest request) throws Exception {
        TbPostsPost tbPostsPost = null;
        if (StringUtils.isNotBlank(postGuid)) {
            String json = postsService.get(postGuid);
            tbPostsPost = MapperUtils.json2pojo(json, TbPostsPost.class);
        }
        if (tbPostsPost == null) {
            tbPostsPost = new TbPostsPost();
        }
        return tbPostsPost;
    }

    @RequestMapping(value = {"", "main"}, method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbPostsPost tbPostsPost, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        TbSysUser tbSysUser = (TbSysUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
        tbPostsPost.setUpdateBy(tbSysUser.getUserCode());
        tbPostsPost.setUpdateDate(new Date());
        tbPostsPost.setStatus("0");
        tbPostsPost.setTimePublished(new Date());
        String tbPostsPostJson = MapperUtils.obj2json(tbPostsPost);
        String json = postsService.save(tbPostsPostJson, tbSysUser.getUserCode());
        if (StringUtils.isNotBlank(json)) {
            BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);
            if (baseResult.getSuccess().endsWith("成功")) {
                return "redirect:/index";
            } else {
                return "redirect:/form";
            }
        }
        return "redirect:/form";
    }
}
