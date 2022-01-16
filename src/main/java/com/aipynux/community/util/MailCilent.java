package com.aipynux.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author aipynux
 * @create 2022-01-12 16:21
 */
@Component
public class MailCilent {
  private static final Logger logger = LoggerFactory.getLogger(MailCilent.class);
  @Autowired
  private JavaMailSender javaMailSender;
  @Value("${spring.mail.username}")
  private String from;

  public void sendEmail(String to,String subject,String context){
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
      helper.setFrom(from);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(context,true);

      javaMailSender.send(mimeMessage);
    } catch (MessagingException e) {
      logger.error("发送邮件失败：" + e.getStackTrace());
    }

  }
}
