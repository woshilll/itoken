package com.funtl.itoken.web.posts.service.fallback;

import com.funtl.itoken.web.posts.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * @author 李洋
 * @date 2019-09-03 15:44
 */
@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public String put(String key, String value, Long seconds) {
        return null;
    }
}
