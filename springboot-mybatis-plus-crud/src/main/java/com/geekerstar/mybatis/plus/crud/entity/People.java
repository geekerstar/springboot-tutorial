package com.geekerstar.mybatis.plus.crud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author geekerstar
 * date: 2019/12/19 20:47
 * description:
 */
@TableName("people")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class People extends Model<People> {

    /**
     * @TableId和@TableField 不能同时使用，如果数据库中主键名称是id，实体属性是userId，你可以这样映射@TableId("id")
     * <p>
     * 主键策略6种：
     * AUTO(0),
     * NONE(1),
     * INPUT(2),
     * ID_WORKER(3),
     * UUID(4),
     * ID_WORKER_STR(5);
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    /**
     * 排除非表字段的三种方式
     * 1、private transient String remark; 无法序列化
     * 2、private static String remark; 实例无法独有一份
     * 3、@TableField(exist=false)
     */
    @TableField(exist = false)
    private String remark;
}
