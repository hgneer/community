package com.aipynux.community.controller;

import com.aipynux.community.entity.User;
import com.aipynux.community.service.UserService;
import com.aipynux.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author aipynux
 * @create 2022-01-13 23:37
 */
@Controller
public class RegisterController implements CommunityConstant {
  @Autowired
  private UserService userService;
  @RequestMapping( path = "/register",method = RequestMethod.GET)
  public String getRegisterPage(){
    return "/site/register";
  }

  @RequestMapping(path = "/register",method = RequestMethod.POST)
  public String register(Model model, User user){
    Map<String,Object> map = userService.register(user);
    if(map == null || map.isEmpty()){
      model.addAttribute("msg","注册成功，我们已向您的邮箱发送一封激活邮件，请尽快激活。");
      model.addAttribute("target","/index");
      return "/site/operate-result";
    }else{
      model.addAttribute("usernameMsg",map.get("usernameMsg"));
      model.addAttribute("passwordMsg",map.get("passwordMsg"));
      model.addAttribute("emailMsg",map.get("emailMsg"));
      return "/site/register";
    }
  }

  @RequestMapping(path = "/activation/{userId}/{code}",method = RequestMethod.GET)
  public String activation(Model model,@PathVariable("userId") int userId,@PathVariable("code") String code){
    int result = userService.activate(userId,code);
    if(result == ACTIVATION_SUCCESS){
      model.addAttribute("msg","恭喜您，账号激活成功，马上开始使用吧！");
      model.addAttribute("target","/login");
    }else if(result == ACTIVATION_FAIL){
      model.addAttribute("msg","账号激活失败，您的激活码不正确。");
      model.addAttribute("target","/index");
    }else{
      model.addAttribute("msg","无效操作，该账号已激活。");
      model.addAttribute("target","/index");
    }
    return "/site/operate-result";
  }
}
