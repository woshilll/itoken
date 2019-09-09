package com.funtl.itoken.web.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李洋
 * @date 2019-09-07 12:57
 */
@Controller
public class WebBackendController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "main";
    }

    @RequestMapping(value = "wel", method = RequestMethod.GET)
    public String wel() {
        return "welcome";
    }
}
