package com.geekerstar.web.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author geekerstar
 * date: 2019-08-20 10:59
 * description:
 */
@Component
public class Properties {
    @Value("${com.geekerstar.title}")
    private String title;
    @Value("${com.geekerstar.desc}")
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
