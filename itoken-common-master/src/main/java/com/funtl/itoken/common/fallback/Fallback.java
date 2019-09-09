package com.funtl.itoken.common.fallback;

import com.funtl.itoken.common.constants.StatusCodesConstant;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.utils.MapperUtils;
import com.google.common.collect.Lists;

/**
 * @author 李洋
 * @date 2019-08-29 11:14
 */
public class Fallback {
    public static String BadGateway() {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error(String.valueOf(StatusCodesConstant.BAD_GATEWAY.getCode()), StatusCodesConstant.BAD_GATEWAY.getMessage())));
        try {
            String json = MapperUtils.obj2json(baseResult);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
