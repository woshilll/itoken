package com.funtl.itoken.common.service.service;

import com.funtl.itoken.common.domain.BaseDomain;
import com.github.pagehelper.PageInfo;

/**
 * @author 李洋
 * @date 2019-08-31 09:57
 */
public interface BaseService<T extends BaseDomain> {
    /**
     * 增
     * @param t
     * @param createBy
     * @return
     */
    int insert(T t, String createBy);
    /**
     * 删
     * @param t
     * @return
     */
    int delete(T t);
    /**
     * 改
     * @param t
     * @param updateBy
     * @return
     */
    int update(T t, String updateBy);
    /**
     * 数量
     * @param t
     * @return
     */
    int count(T t);
    /**
     * 查单个
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 分页查询
     * @param pageNums
     * @param pageSize
     * @param t
     * @return
     */
    PageInfo<T> page(int pageNums, int pageSize, T t);
}
