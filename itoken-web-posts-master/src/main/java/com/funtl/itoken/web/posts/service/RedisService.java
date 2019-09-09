package com.funtl.itoken.web.posts.service;

import com.funtl.itoken.web.posts.service.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 李洋
 * @date 2019-09-03 15:41
 */
@FeignClient(value = "itoken-service-redis", fallback = RedisServiceFallback.class)
public interface RedisService {
    @RequestMapping(value = "get", method = RequestMethod.GET)
    String get(@RequestParam("key") String key);
    @RequestMapping(value = "put", method = RequestMethod.POST)
    String put(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("seconds") Long seconds);
}
