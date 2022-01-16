package com.aipynux.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author aipynux
 * @create 2022-01-16 9:40
 */
public class CommunityUtil {

  /**
   * 生成随机字符串
   */
  public static String getRandomStr(){
    return UUID.randomUUID().toString().replaceAll("-","");
  }

  /**
   * MD5加密
   */
  public static String md5(String key){
    if(StringUtils.isBlank(key)){
      return null;
    }else{
      return DigestUtils.md5DigestAsHex(key.getBytes());
    }
  }
}
