package com.funtl.itoken.web.posts.interceptor;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.CookieUtils;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.common.web.constants.WebConstants;
import com.funtl.itoken.web.posts.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李洋
 * @date 2019-09-03 15:39
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
        if (StringUtils.isBlank(token)) {
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8602/" + request.getServletPath());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TbSysUser tbSysUser = (TbSysUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
        //已经登录
        if (tbSysUser != null) {
            if (modelAndView != null) {
                modelAndView.addObject(WebConstants.SESSION_USER, tbSysUser);
            }
        }
        //未登录
        else {
            String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
            if (StringUtils.isNotBlank(token)) {
                //获取登录账号
                String loginCode = redisService.get(token);
                if (StringUtils.isNotBlank(loginCode)) {
                    String tbSysUserJson = redisService.get(loginCode);
                    if (StringUtils.isNotBlank(tbSysUserJson)) {
                        tbSysUser = MapperUtils.json2pojo(tbSysUserJson, TbSysUser.class);
                        if (tbSysUser != null) {
                            request.getSession().setAttribute(WebConstants.SESSION_USER, tbSysUser);
                            if (modelAndView != null) {
                                modelAndView.addObject(WebConstants.SESSION_USER, tbSysUser);
                            }
                        }
                    }
                }
            }
        }
        if (tbSysUser == null) {
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8602/" + request.getServletPath());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
