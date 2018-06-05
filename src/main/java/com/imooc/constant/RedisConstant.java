package com.imooc.constant;

/**
 * @author xuan
 * @date 18/6/4 上午12:45
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";//以"token_"开头的字符串

    Integer EXPIRE = 7200; //2小时
}
