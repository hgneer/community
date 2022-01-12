package com.aipynux.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author aipynux
 * @create 2021-12-07 15:15
 */

@Repository
@Primary
public class AlphaDaoHibernateImpl implements AlphaDao{
  @Override
  public String select() {
    return "Hibernate";
  }
}
