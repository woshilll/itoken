package com.funtl.itoken.service.admin.controller;

import com.funtl.itoken.common.domain.BaseDomain;
import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.service.admin.service.AdminService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/**
 * @author 李洋
 * @date 2019-08-27 12:09
 */
@RestController
@RequestMapping(value = "v1/admins")
public class AdminController {
    @Autowired
    private AdminService<TbSysUser> adminService;

    @RequestMapping(value = "{userCode}", method = RequestMethod.GET)
    public TbSysUser get(
            @PathVariable("userCode") String userCode
    ) {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(userCode);
        tbSysUser = adminService.selectOne(tbSysUser);
        return tbSysUser;
    }

    /**
     * 新增或删除
     * @param tbSysUserJson
     * @param opt
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(
            @RequestParam(required = true) String tbSysUserJson,
            @RequestParam(required = true) String opt
    ) {
        TbSysUser tbSysUser = null;
        int result = 0;
        try {
            tbSysUser = MapperUtils.json2pojo(tbSysUserJson, TbSysUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果tbSysUser为空，数据异常
        if (tbSysUser != null) {
            //判断主键是否为空
            if (StringUtils.isNotBlank(tbSysUser.getUserCode())) {
                //更新
                result = adminService.update(tbSysUser, opt);
                if (result > 0) {
                    return BaseResult.ok("更新成功");
                }
                return BaseResult.ok("更新失败");
            }
            else {
                //新增
                //密码加密
                tbSysUser.setPassword(DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes()));
                //生成主键
                String userCode = UUID.randomUUID().toString();
                tbSysUser.setUserCode(userCode);
                result = adminService.insert(tbSysUser, opt);
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
     * @param pageNum 起始页
     * @param pageSize 每页数据数
     * @param tbSysUserJson
     * @return
     */
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "tbSysUserJson", value = "tbSysUser对象Json数据", required = false, dataTypeClass = String.class, paramType = "json"),
    })
    @RequestMapping(value = "/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize,
            @RequestParam(required = false) String tbSysUserJson
    ) {
        TbSysUser tbSysUser = null;
        if (StringUtils.isNotBlank(tbSysUserJson)) {
            try {
                tbSysUser = MapperUtils.json2pojo(tbSysUserJson, TbSysUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageInfo<TbSysUser> pageInfo = adminService.page(pageNum, pageSize, tbSysUser);
        List<TbSysUser> tbSysUsers = pageInfo.getList();
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setLimit(pageInfo.getPageSize());
        cursor.setOffset(pageInfo.getPageNum());
        return BaseResult.ok(tbSysUsers, cursor);
    }
}
