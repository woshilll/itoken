package com.funtl.itoken.web.admin.controller;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.common.web.controller.BaseController;
import com.funtl.itoken.web.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 李洋
 * @date 2019-08-27 17:29
 */
@Controller
public class AdminController extends BaseController<TbSysUser, AdminService> {
    @Autowired
    private AdminService adminService;


    @ModelAttribute
    public TbSysUser first(String userCode) throws Exception {
        TbSysUser tbSysUser = null;
        if (StringUtils.isNotBlank(userCode)) {
            String json = adminService.get(userCode);
            if (StringUtils.isNotBlank(json)) {
                tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
            }
        }
        if (tbSysUser == null) {
            tbSysUser = new TbSysUser();
        }
        return tbSysUser;
    }

    @RequestMapping(value = {"", "main"}, method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    @ResponseBody
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get() {
        return adminService.get("735b25c7-55c4-4f74-a58d-8d9b572abfe8");
    }


}
