package com.linhuanjie.javase;

public enum TestEnum {
    SUCCESS(0,"成功");
//    SERROR1(1,"错误1");
//    SSSS(2,"错误2");
//    ERROR3(3,"错误3");
//    ERROR4(4,"错误4");

    private int code;
    private String msg;

    TestEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
