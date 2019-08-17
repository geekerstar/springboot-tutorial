package com.geekerstar.mail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.geekerstar.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMailApplicationTests {

    @Autowired
    private MailService mailService;

    /**
     * 测试简单文本邮件发送
     */
    @Test
    public void send() {
        String to = "17602684842@163.com";
        String title = "标题: 简单文本邮件发送测试";
        String content = "简单文本 ...";
        Assert.assertTrue(mailService.send(to, title, content));
    }

    /**
     * 测试html邮件发送
     */
    @Test
    public void sendHtml() {
        String to = "247507792@com";
        String title = "标题: html邮件发送测试";

        String htmlContent = "<html><body><h1>欢迎关注极客文库</h1></body></html>";

        Assert.assertTrue(mailService.sendWithHtml(to, title, htmlContent));
    }

    /**
     * 测试html邮件发送
     */
    @Test
    public void sendImageHtml() {
        String to = "17602684842@163.com";
        String title = "标题: 带图片的html邮件发送测试";

        String htmlContent = "<html><body>" +
                "<h1>欢迎关注猿码素材</h1>" +
                "<p><h2 style='color: red;'>图片1</h2><img style='width: 280px; height: 280px;' src='cid:test'></p>" +
                "<p><h2 style='color: red;'>图片2</h2><img style='width: 280px; height: 280px;' src='cid:test2'></p>" +
                "</body></html>";

        // cid 要与上面 html 中的 cid 对应，否则设置图片不成功
        String[] cids = new String[]{
                "test",
                "test2",
        };

        // 文件路径
        String[] filePaths = new String[]{
                "/Users/geekerstar/Downloads/avatar.jpg"
        };

        Assert.assertTrue(mailService.sendWithImageHtml(to, title, htmlContent, cids, filePaths));
    }

    /**
     * 测试添加附件的邮件发送
     */
    @Test
    public void sendWithWithEnclosure() {
        String to = "17602684842@163.com";
        String title = "标题: 带有附件的邮件发送测试";
        String content = "带有附件的邮件发送测试";

        // 添加两个附件
        String[] filePaths = new String[]{
                "/Users/geekerstar/Downloads/avatar.jpg",
                "/Users/geekerstar/Downloads/avatar.jpg"
        };

        Assert.assertTrue(mailService.sendWithWithEnclosure(to, title, content, filePaths));
    }



}
