package com.funtl.itoken.common.web.service;

import com.funtl.itoken.common.fallback.Fallback;

/**
 * @author 李洋
 * @date 2019-09-03 17:05
 */
public interface BaseClientService {
    default String page(int start, int length, String domainJson){
        return Fallback.BadGateway();
    }
}
