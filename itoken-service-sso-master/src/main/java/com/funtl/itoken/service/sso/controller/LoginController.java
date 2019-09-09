package com.funtl.itoken.service.sso.controller;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.CookieUtils;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.service.sso.service.LoginService;
import com.funtl.itoken.service.sso.service.hystrix.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 李洋
 * @date 2019-08-29 11:36
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String url, HttpServletRequest request, Model model) {
        //获取cookie
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isNotBlank(token)) {
            //获取loginCode
            String loginCode = redisService.get(token);
            if (StringUtils.isNotBlank(loginCode)) {
                String json = redisService.get(loginCode);
                if (StringUtils.isNotBlank(json)) {
                    try {
                        TbSysUser tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        if (tbSysUser != null) {
                            if (StringUtils.isNotBlank(url)) {
                                return "redirect:" + url;
                            }
                            model.addAttribute("tbSysUser", tbSysUser);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        if (StringUtils.isNotBlank(url)) {
            model.addAttribute("url", url);
        }
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String loginCode, String password, @RequestParam(required = false) String url,
                        HttpServletRequest request, HttpServletResponse response,
                        RedirectAttributes redirectAttributes) {
        TbSysUser login = loginService.login(loginCode, password);
        if (login == null) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误!");
            return "redirect:login";
        }
        else {
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60L * 60 * 24);
            if (StringUtils.isNotBlank(result) && "ok".equals(result)) {
                //说明token放置成功
                //设置cookie返回给浏览器
                CookieUtils.setCookie(request, response, "token", token, 60 * 60 * 24);
                if (StringUtils.isNotBlank(url)) {
                    return "redirect:" + url;
                }
                else {
                    return "redirect:login";
                }

            } else {
                redirectAttributes.addFlashAttribute("message", "服务器异常，请稍后再试!");
                return "redirect:login";
            }

        }
    }
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String url, Model model) {
        CookieUtils.deleteCookie(request, response, "token");
        return login(url, request, model);
    }
}
