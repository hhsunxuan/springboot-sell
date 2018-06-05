package com.imooc.exception;

import com.imooc.enums.ResultEnum;
import lombok.Getter;

/**
 * @author xuan
 * @date 18/5/25 下午4:41
 */
@Getter
public class  SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();

    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
