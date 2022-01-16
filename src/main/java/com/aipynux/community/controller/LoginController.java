package com.aipynux.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author aipynux
 * @create 2022-01-16 14:25
 */
@Controller
public class LoginController {
  @RequestMapping(path = "/login",method = RequestMethod.GET)
  public String getLoginPage(){
    return "/site/login";
  }
}
