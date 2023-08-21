package com.tsrs.webedi.common.constant.dictmap;

import com.tsrs.webedi.common.constant.dictmap.base.AbstractDictMap;

/**
 * 日志的字典
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-06 15:01
 */
public class LogDict extends AbstractDictMap {

    @Override
    public void init() {
        put("tips","备注");
    }

    @Override
    protected void initBeWrapped() {

    }
}
