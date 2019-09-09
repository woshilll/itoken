package com.funtl.itoken.web.admin.service.fallback;

import com.funtl.itoken.common.fallback.Fallback;
import com.funtl.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * @author 李洋
 * @date 2019-08-27 17:45
 */
@Component
public class AdminServiceFallbackImpl implements AdminService {

    @Override
    public String get(String userCode) {
        return null;
    }

    @Override
    public String save(String tbSysUserJson, String opt) {
        return null;
    }

    @Override
    public String page(int pageNum, int pageSize, String tbSysUserJson) {
        return null;
    }


}
