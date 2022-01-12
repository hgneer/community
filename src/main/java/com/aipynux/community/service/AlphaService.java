package com.aipynux.community.service;

import com.aipynux.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author aipynux
 * @create 2021-12-07 15:39
 */
@Service
@Scope("prototype")
public class AlphaService {

  @Autowired
  @Qualifier("alphaDaoMybatis")
  private AlphaDao alphaDao;

  public AlphaService(){
    System.out.println("实例化AlphaService");
  }

  @PostConstruct
  public void init(){
    System.out.println("初始化AlphaService");
  }

  @PreDestroy
  public void destory(){
    System.out.println("销毁AlphaService");
  }

  public String find() {
    return alphaDao.select();
  }
}
