package com.aipynux.community.dao;

import com.aipynux.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aipynux
 * @create 2021-12-13 16:27
 */
@Mapper
public interface UserMapper {
  User selectById(int id);
  User selectByName(String username);
  User selectByEmail(String email);
  int insertUser(User user);
  int updateStatus(int id,int status);
  int updateHeader(int id,String headerUrl);
  int updatePassword(int id,String password);
  void deleteById(int id);
}
