package com.funtl.itoken.service.admin.test;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.service.admin.ServiceAdminApplication;
import com.funtl.itoken.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * @author 李洋
 * @date 2019-08-27 10:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
@ActiveProfiles(value = "prod")
@Transactional
@Rollback
public class Test {
}
