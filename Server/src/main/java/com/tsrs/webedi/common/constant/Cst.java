package com.tsrs.webedi.common.constant;

/**
 * 一些服务的快捷获取
 *
 * @author Tsrs-WebEDI
 * @date 2017-03-30 15:58
 */
public class Cst {

    private Cst() {
    }

    private static Cst cst = new Cst();

    public static Cst me() {
        return cst;
    }

}
