package com.glsx.oms.bigdata.admin.bma.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求状态码
 *
 * @Author yangbin
 * @Date 11:21 2018/7/31
 * @Version 1.0
 */
public enum RespStatusEnum {
    /**
     * 操作成功
     */
    SUCCESS(1000, "操作成功！"),
    /**
     * 操作失败
     */
    FAIL(1001, "操作失败！"),
    /**
     * 系统错误
     */
    SYSTEM(-1, "系统错误！");

    private final int code;
    private final String message;

    RespStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static RespStatusEnum findByCode(int code) {
        for (RespStatusEnum rs : values()) {
            if (rs.code == code) {
                return rs;
            }
        }
        return null;
    }

    public static Map<Integer, String> all2Map() {
        Map<Integer, String> map = new HashMap<>();
        for (RespStatusEnum t : RespStatusEnum.values()) {
            map.put(t.code, t.message);
        }
        return map;
    }
}
