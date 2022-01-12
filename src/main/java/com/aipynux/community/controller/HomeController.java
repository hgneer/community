package com.aipynux.community.controller;

import com.aipynux.community.entity.DiscussPost;
import com.aipynux.community.entity.Page;
import com.aipynux.community.entity.User;
import com.aipynux.community.service.DiscussPostService;
import com.aipynux.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aipynux
 * @create 2022-01-05 0:02
 */
@Controller
public class HomeController {
  @Autowired
  private DiscussPostService discussPostService;
  @Autowired
  private UserService userService;

  @RequestMapping(path = "/index",method = RequestMethod.GET)
  public String getIndexPage(Model model,Page page){
    page.setRows(discussPostService.findDiscussRows(0));
    page.setPath("/index");

    List<DiscussPost> list = discussPostService.findDiscussPosts(0,page.getOffSet(),page.getLimit());
    List<Map<String,Object>> discussPosts = new ArrayList<>();
    if(list != null){
      for(DiscussPost post : list){
        Map<String,Object> map = new HashMap<>();
        map.put("post",post);
        User user = userService.findUserById(post.getUserId());
        //遇到了can't find headerUrl on null的问题，因为是写错用post的id查的用户，导致查到的用户始终为空。
//        if(user == null){
//          System.out.println("user is null");
//        }
        map.put("user",user);

        discussPosts.add(map);
      }
    }

    model.addAttribute("discussPosts",discussPosts);
    return "/index";
  }
}
