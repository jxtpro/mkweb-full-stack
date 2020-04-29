package com.alipay.sakd;

import java.util.ArrayList;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath*:META-INF/spring/acts-core.xml"})
@SpringBootApplication(scanBasePackages = {"com.soft.sakd.common","com.soft.sakd.biz.mange"})
@MapperScan("com.soft.sakd.core.model")
public class SOFABootTestApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(SOFABootTestApplication.class, args);
  }
}
