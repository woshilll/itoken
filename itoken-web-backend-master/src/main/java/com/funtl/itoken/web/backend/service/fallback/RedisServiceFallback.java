package com.funtl.itoken.web.backend.service.fallback;

import com.funtl.itoken.web.backend.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * @author 李洋
 * @date 2019-08-29 11:13
 */
@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, Long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
