package com.funtl.itoken.web.admin.service;
import com.funtl.itoken.common.web.service.BaseClientService;
import com.funtl.itoken.web.admin.service.fallback.AdminServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 李洋
 * @date 2019-08-27 17:41
 */
@FeignClient(value = "itoken-service-admin" , fallback = AdminServiceFallbackImpl.class)
public interface AdminService extends BaseClientService {
    @RequestMapping(value = "v1/admins/{userCode}", method = RequestMethod.GET)
    String get(
            @PathVariable("userCode") String userCode
    );
    @RequestMapping(value = "v1/admins", method = RequestMethod.POST)
    String save(
            @RequestParam(value = "tbSysUserJson", required = true) String tbSysUserJson,
            @RequestParam(value = "opt", required = true) String opt
    );

    @Override
    @RequestMapping(value = "v1/admins/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    String page(
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize,
            @RequestParam(value = "tbSysUserJson", required = false) String tbSysUserJson
    );

}
