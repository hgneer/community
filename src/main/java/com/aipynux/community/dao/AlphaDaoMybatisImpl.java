package com.aipynux.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author aipynux
 * @create 2021-12-07 15:20
 */
@Repository("alphaDaoMybatis")
public class AlphaDaoMybatisImpl implements AlphaDao{
  @Override
  public String select() {
    return "Mybatis";
  }
}
