package com.imooc.exception;

import com.imooc.enums.ResultEnum;
import lombok.Data;

/**
 * @author xuan
 * @date 18/6/4 下午5:02
 */

@Data
public class ResponseBankException extends RuntimeException {

    private Integer code;

    public ResponseBankException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }


    public ResponseBankException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code=code;
    }
}
