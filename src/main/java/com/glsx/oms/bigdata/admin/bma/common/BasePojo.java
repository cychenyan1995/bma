package com.glsx.oms.bigdata.admin.bma.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName BasePojo
 * @Description TODO
 * @Author yangbin
 * @Date 2018/10/29 11:25
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties({"startTime1", "endTime1", "startTime2", "endTime2", "startTime3", "endTime3",
        "num1", "num2", "num3", "num4", "num5", "num6", "num7", "num8", "num9", "num10"})
public class BasePojo<T> {

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime1;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime1;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime2;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime2;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime3;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime3;

    @JsonIgnore
    private Integer num1;

    @JsonIgnore
    private Integer num2;

    private Integer num3;

    private Integer num4;

    private Integer num5;

    private Integer num6;

    private Integer num7;

    private Integer num8;

    private Integer num9;

    private Integer num10;

}
