package com.funtl.itoken.common.service.service.impl;

import com.funtl.itoken.common.domain.BaseDomain;
import com.funtl.itoken.common.service.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;

/**
 * @author 李洋
 * @date 2019-08-31 10:01
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public abstract class BaseServiceImpl<T extends BaseDomain, D extends MyMapper<T>> implements BaseService<T> {
    @Autowired
    private D dao;
    @Override
    @Transactional(readOnly = false)
    public int insert(T t, String createBy) {
        t.setCreateBy(createBy);
        t.setCreateDate(new Date());
        return dao.insert(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T t, String updateBy) {
        t.setUpdateBy(updateBy);
        t.setUpdateDate(new Date());
        return dao.updateByPrimaryKey(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public T selectOne(T t) {
        return dao.selectOne(t);
    }

    @Override
    public PageInfo<T> page(int pageNums, int pageSize, T t) {
        PageHelper.startPage(pageNums, pageSize);
        return new PageInfo<>(dao.select(t));
    }
}
