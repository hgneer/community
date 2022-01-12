package com.aipynux.community;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author aipynux
 * @create 2022-01-11 22:20
 */
@SpringBootTest
public class LoggerTest {
  private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

  @Test
  public void testLogger(){
    System.out.println(logger.getName());

    logger.trace("trace log");
    logger.debug("debug log");
    logger.info("info log");
    logger.warn("warn log");
    logger.error("error log");
  }
}
