package com.geekerstar.websocket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocket {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在线人数
     */
    private static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String username;

    /**
     * 建立连接
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        onlineNumber++;
        logger.info("连接的客户id：" + session.getId() + "用户名：" + username);
        this.username = username;
        this.session = session;
        logger.info("新连接加入！ 当前在线人数" + onlineNumber);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            //先给所有人发送通知，说我上线了
            Map<String, Object> map1 = new HashMap<>(16);
            map1.put("messageType", 1);
            map1.put("username", username);
            sendMessageAll(JSON.toJSONString(map1), username);

            //把自己的信息加入到map当中去
            clients.put(username, this);
            //给自己发一条消息：告诉自己现在都有谁在线
            Map<String, Object> map2 = new HashMap<>(16);
            map2.put("messageType", 3);
            //移除掉自己
            Set<String> set = clients.keySet();
            map2.put("onlineUsers", set);
            sendMessageTo(JSON.toJSONString(map2), username);
        } catch (IOException e) {
            logger.info(username + "上线的时候通知所有人发生了错误");
        }
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("服务端发生了错误" + error.getMessage());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        clients.remove(username);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap<>(16);
            map1.put("messageType", 2);
            map1.put("onlineUsers", clients.keySet());
            map1.put("username", username);
            sendMessageAll(JSON.toJSONString(map1), username);
        } catch (IOException e) {
            logger.info(username + "下线的时候通知所有人发生了错误");
        }
        logger.info("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String fromUsername = jsonObject.getString("username");
            String toUsername = jsonObject.getString("to");
            //如果不是发给所有，那么就发给某一个人
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap<>(16);
            map1.put("messageType", 4);
            map1.put("textMessage", textMessage);
            map1.put("fromusername", fromUsername);
            if ("All".equals(toUsername)) {
                map1.put("tousername", "所有人");
                sendMessageAll(JSON.toJSONString(map1), fromUsername);
            } else {
                map1.put("tousername", toUsername);
                sendMessageTo(JSON.toJSONString(map1), toUsername);
            }
        } catch (Exception e) {
            logger.info("发生了错误了");
        }
    }

    /**
     * 发送消息-指定人
     */
    private void sendMessageTo(String message, String toUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            if (item.username.equals(toUserName)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    /**
     * 发送消息-所有人
     */
    private void sendMessageAll(String message, String fromUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    /**
     * 获取在线人数
     */
    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }

}
