package com.imooc.utils;

import java.util.Random;

/**
 * @author xuan
 * @date 18/5/26 上午1:07
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间＋随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random  random = new Random();
        Integer number =  random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(System.currentTimeMillis());

    }
}
