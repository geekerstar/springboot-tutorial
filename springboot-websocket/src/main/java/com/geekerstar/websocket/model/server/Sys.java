package com.geekerstar.websocket.model.server;

import lombok.Getter;
import lombok.Setter;

/**
 * @author geekerstar
 * @date 2020/11/4 16:38
 * @description
 */
@Getter
@Setter
public class Sys {
    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;

}
