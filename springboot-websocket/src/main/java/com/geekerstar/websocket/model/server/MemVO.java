package com.geekerstar.websocket.model.server;

import com.geekerstar.websocket.model.KV;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author geekerstar
 * @date 2020/11/4 16:38
 * @description
 */
@Data
public class MemVO {
    List<KV> data = Lists.newArrayList();

    public static MemVO create(Mem mem) {
        MemVO vo = new MemVO();
        vo.data.add(new KV("内存总量", mem.getTotal() + "G"));
        vo.data.add(new KV("已用内存", mem.getUsed() + "G"));
        vo.data.add(new KV("剩余内存", mem.getFree() + "G"));
        vo.data.add(new KV("使用率", mem.getUsage() + "%"));
        return vo;
    }
}
