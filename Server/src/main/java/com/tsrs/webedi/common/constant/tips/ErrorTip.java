package com.tsrs.webedi.common.constant.tips;

import com.tsrs.webedi.common.exception.BizExceptionEnum;

/**
 * 返回给前台的错误提示
 *
 * @author Tsrs-WebEDI
 * @date 2016年11月12日 下午5:05:22
 */
public class ErrorTip extends Tip {

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ErrorTip(BizExceptionEnum bizExceptionEnum) {
        this.code = bizExceptionEnum.getCode();
        this.message = bizExceptionEnum.getMessage();
    }
}
