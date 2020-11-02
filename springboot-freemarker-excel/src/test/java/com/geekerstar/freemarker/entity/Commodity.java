package com.geekerstar.freemarker.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class Commodity {
    private String name;
    private Integer num;
    private int num1;
    private BigDecimal price;
    private Double price2;
    private Float price3;
    private Date createTime;
    private Long timestamp;
}
