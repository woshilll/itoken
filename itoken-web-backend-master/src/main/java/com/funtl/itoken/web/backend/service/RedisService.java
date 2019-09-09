package com.funtl.itoken.web.backend.service;

import com.funtl.itoken.web.backend.service.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 李洋
 * @date 2019-08-29 11:08
 */
@FeignClient(value = "itoken-service-redis" , fallback = RedisServiceFallback.class)
public interface RedisService {
    @RequestMapping(value = "put" , method = RequestMethod.POST)
    public String put(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("seconds") Long seconds);
    @RequestMapping(value = "get" , method = RequestMethod.GET)
    public String get(@RequestParam("key") String key);
}
