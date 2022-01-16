package com.aipynux.community.service;

import com.aipynux.community.dao.UserMapper;
import com.aipynux.community.entity.User;
import com.aipynux.community.util.CommunityConstant;
import com.aipynux.community.util.CommunityUtil;
import com.aipynux.community.util.MailCilent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author aipynux
 * @create 2022-01-04 21:19
 */
@Service
public class UserService implements CommunityConstant {
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private MailCilent mailCilent;
  @Autowired
  private TemplateEngine templateEngine;
  @Value("${server.servlet.context-path}")
  private String contextPath;
  @Value("${community.path.domain}")
  private String domain;

  public User findUserById(int id){
    return userMapper.selectById(id);
  }

  public Map<String,Object> register(User user){
    Map<String,Object> map = new HashMap<>();

    // 空值验证
    if(user == null){
      throw new IllegalArgumentException("参数不能为空！");
    }
    if(StringUtils.isBlank(user.getUsername())){
      map.put("usernameMsg","账号不能为空！");
      return map;
    }
    if(StringUtils.isBlank(user.getPassword())){
      map.put("passwordMsg","密码不能为空！");
      return map;
    }
    if(StringUtils.isBlank(user.getEmail())){
      map.put("emailMsg","邮箱不能为空！");
      return map;
    }

    // 验证账号
    if(userMapper.selectByName(user.getUsername()) != null){
      map.put("usernameMsg","该账号已TM存在！");
      return map;
    }
    // 验证邮箱
    if(userMapper.selectByEmail(user.getEmail()) != null){
      map.put("emailMsg","该邮箱已TM存在！");
      return map;
    }

    // 注册用户
    user.setType(0);
    user.setStatus(0);
    String headerUrl = String.format("https://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000));
    user.setHeaderUrl(headerUrl);
    user.setSalt(CommunityUtil.getRandomStr().substring(0,5));
    user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
    user.setActivationCode(CommunityUtil.getRandomStr().substring(0,6));
    user.setCreateTime(new Date());
    userMapper.insertUser(user);

    // 发送激活邮件
    Context context = new Context();
    context.setVariable("email",user.getEmail());
    // http://localhost:8080/community/activation/110/code
    String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
    context.setVariable("url",url);
    String content = templateEngine.process("/mail/activation",context);
    mailCilent.sendEmail(user.getEmail(),"激活账号",content);

    return map;
  }


  public int activate(int userId,String code){
    User user = userMapper.selectById(userId);
    if(user == null){
      return ACTIVATION_FAIL;
    }
    if(user.getStatus() == 1){
      return ACTIVATION_REPEAT;
    }else{
      if(code.equals(user.getActivationCode())){
        userMapper.updateStatus(userId,1);
        return ACTIVATION_SUCCESS;
      }else{
        return ACTIVATION_FAIL;
      }
    }
  }
}
