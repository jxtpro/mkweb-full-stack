package com.soft.sakd.core.model;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xujie
 * @since 2020/4/2 16:33
 */
@SpringBootApplication
@MapperScan("com.soft.sakd.core.model")
public class ModelApplication {
  public static void main(String[] args) {
    SpringApplication.run(ModelApplication.class, args);
  }
}
