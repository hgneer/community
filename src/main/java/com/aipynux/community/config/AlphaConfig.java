package com.aipynux.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author aipynux
 * @create 2021-12-07 16:18
 */
@Configuration
public class AlphaConfig {
  @Bean
  public SimpleDateFormat simpleDateFormat(){
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  }
}
