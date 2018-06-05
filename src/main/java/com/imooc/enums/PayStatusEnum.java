package com.imooc.enums;

import lombok.Getter;

/**
 * @author xuan
 * @date 18/5/24 上午1:16
 */
@Getter
public enum PayStatusEnum  implements CodeEnum{
    WAIT(0,"等待状态"),
    SUCCESS(1,"支付成功")

    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
