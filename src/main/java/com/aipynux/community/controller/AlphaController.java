package com.aipynux.community.controller;

import com.aipynux.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author aipynux
 * @create 2021-12-07 12:21
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {
  @Autowired
  private AlphaService service;

  @RequestMapping("/hello")
  @ResponseBody
  public String sayHello(){
    return "Hello Spring Boot.";
  }

  @RequestMapping("/data")
  @ResponseBody
  public String getData(){
    return service.find();
  }

  @RequestMapping("/http")
  public void http(HttpServletRequest request, HttpServletResponse response){
    //获取请求数据
    System.out.println(request.getMethod());
    System.out.println(request.getServletPath());
    System.out.println(request.getContextPath());
    Enumeration enumeration = request.getHeaderNames();
    while(enumeration.hasMoreElements()){
      String name = (String) enumeration.nextElement();
      String value = request.getHeader(name);
      System.out.println(name + ":" + value);

    }
    //其实还有请求体，含有传递的参数，我们可以这样获取(那浏览器如何传参的呢？就是在路径后拼接一个“？参数名=参数值”，这里可以？code=java)
    System.out.println(request.getParameter("code"));

    //返回响应数据
    response.setContentType("text/html;charset=utf-8");
    try (PrintWriter printWriter = response.getWriter()) {
      printWriter.write("<h1>牛客网</h1>");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  /**处理GET请求*/
  @RequestMapping(value = "/students",method = RequestMethod.GET)
  @ResponseBody
  public String getStudents(@RequestParam(name = "current",required = false,defaultValue = "1") int current,
                            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit){
    System.out.println(current);
    System.out.println(limit);
    return "students";
  }

  @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
  @ResponseBody
  public String getStudent(@PathVariable("id") int id){
    System.out.println(id);
    return "a student";
  }

  /**处理POST请求*/
  @RequestMapping(path = "/student",method = RequestMethod.POST)
  @ResponseBody
  public String saveStudent(String name,int age){
    System.out.println(name);
    System.out.println(age);
    return "success";
  }

  /**
   * 响应 HTML 数据
   * 默认不加 @ResponseBody 注解，返回的就是 HTML 。
   */
  @RequestMapping(path = "/teacher",method = RequestMethod.GET)
  public ModelAndView getTeacher(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("name","李四");
    modelAndView.addObject("age","24");
    //因为这里使用的 Thymeleaf 模板引擎默认的模板文件格式是 HTML 文件，所以写到模板文件名字即可，不用写文件格式。
    modelAndView.setViewName("/demo/view");
    return modelAndView;
  }

  @RequestMapping(path = "/school",method = RequestMethod.GET)
  public String getSchool(Model model){
    model.addAttribute("name","中国矿业大学");
    model.addAttribute("age","123");
    return "/demo/view";
  }


  /**
   * 响应返回JSON数据（多用于异步请求或者局部验证时）
   * Java对象 -> JSON字符串 -> JS对象
   */
  @RequestMapping(path = "/emp",method = RequestMethod.GET)
  @ResponseBody
  public Map<String,Object> getEmp(){
    Map<String,Object> map = new HashMap<>(10);
    map.put("name","张三");
    map.put("age",22);
    map.put("salary",8000.0);
    return map;
  }

  @RequestMapping(path = "/emps",method = RequestMethod.GET)
  @ResponseBody
  public List<Map<String,Object>> getEmps(){
    //LinkedList 和 ArrayList 有什么区别？ ArrayList的实现是基于数组，LinkedList的实现是基于双向链表。
    //List<Map<String,Object>> list = new LinkedList<>();
    List<Map<String,Object>> list = new ArrayList<>();

    Map<String,Object> map0 = new HashMap<>(20);
    map0.put("name","张三");
    map0.put("age",22);
    map0.put("salary",8000.0);

    Map<String,Object> map1 = new HashMap<>(20);
    map1.put("name","李四");
    map1.put("age",23);
    map1.put("salary",9000.0);

    Map<String,Object> map2 = new HashMap<>(20);
    map2.put("name","王麻子");
    map2.put("age",24);
    map2.put("salary",10000.0);

    list.add(map0);
    list.add(map1);
    list.add(map2);

    return list;
  }
}
