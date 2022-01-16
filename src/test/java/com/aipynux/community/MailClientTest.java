package com.aipynux.community;

import com.aipynux.community.util.MailCilent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author aipynux
 * @create 2022-01-12 16:35
 */
@SpringBootTest
public class MailClientTest {
  @Autowired
  private MailCilent mailCilent;

  @Autowired
  private TemplateEngine templateEngine;

  @Test
  public void testSendTextEmail(){
    mailCilent.sendEmail("1079329948@qq.com","Test","Spring大法好啊！");
  }

  @Test
  public void testSendHtmlEmail(){
    Context context = new Context();
    context.setVariable("username","JavaGuide");
    String emailContext = templateEngine.process("/mail/emaildemo",context);

    System.out.println(emailContext);
    mailCilent.sendEmail("1079329948@qq.com","用Spring Email发送HTML格式邮件",emailContext);
  }
}
