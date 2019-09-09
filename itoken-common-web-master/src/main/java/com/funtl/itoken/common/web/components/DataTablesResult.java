package com.funtl.itoken.common.web.components;

import com.funtl.itoken.common.dto.BaseResult;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 李洋
 * @date 2019-09-03 17:11
 */
@Data
public class DataTablesResult extends BaseResult implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private String error;
}
