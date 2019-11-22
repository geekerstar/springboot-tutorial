package com.geekerstar.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class Demo {
    private int id;
    private String name;
    /**
     * com.alibaba.fastjson.annotation.JSONField
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     * 我们不想返回remarks?
     * serialize:是否需要序列化属性.
     */
    @JSONField(serialize = false)
    private String remarks;
}
