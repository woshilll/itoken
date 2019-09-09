package com.funtl.itoken.service.redis.service;

/**
 * @author 李洋
 * @date 2019-08-28 16:27
 */
public interface RedisService {
    /**
     * put
     * @param key 键
     * @param value 值
     * @param seconds 事件
     */
    public void put(String key, Object value, Long seconds);

    /**
     * get
     * @param key 键
     * @return
     */
    public Object get(String key);
}
