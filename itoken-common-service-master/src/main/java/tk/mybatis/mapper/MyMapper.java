package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 李洋
 * @date 2019-08-22 11:15
 */
public interface MyMapper<T> extends Mapper<T> , MySqlMapper<T> {
}
