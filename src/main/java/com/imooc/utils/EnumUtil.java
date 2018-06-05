package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
 * @author xuan
 * @date 18/5/29 下午4:38
 */
public class EnumUtil {


    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
