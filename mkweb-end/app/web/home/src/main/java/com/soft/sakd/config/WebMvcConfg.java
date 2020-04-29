package com.soft.sakd.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.soft.sakd.common.search.bean.Result;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author xujie
 * @since 2020/4/2 20:41
 */
@Configuration
@Log4j2
public class WebMvcConfg implements WebMvcConfigurer {
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    FastJsonConfig config = new FastJsonConfig();
    config.setSerializerFeatures(SerializerFeature.WriteMapNullValue, // 保留空的字段
        SerializerFeature.WriteNullStringAsEmpty, // String null -> ""
        SerializerFeature.WriteNullNumberAsZero); // Number null -> 0
    converter.setFastJsonConfig(config);
    converter.setDefaultCharset(Charset.forName("UTF-8"));
    converters.add(converter);
  }

  @Override
  public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
    exceptionResolvers.add((request, response, handler, e) -> handlerException(request, response, handler, e));
  }

  private ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
    String message = handlerExceptionDetailMessage(request, handler, e);
    writeResponse(response, message);
    log.error(message, e);
    return new ModelAndView();
  }

  private String handlerExceptionDetailMessage(HttpServletRequest request, Object handler, Exception e) {
    String message;
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(), handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(), e.getMessage());
    } else {
      message = e.getMessage();
    }
    return message;
  }

  private void writeResponse(HttpServletResponse response, String message) {
    try {
      response.setContentType("text/html; charset=UTF-8");
      response.getWriter().print(JSON.toJSON(Result.makeSuccessResult(message)));
      response.getWriter().flush();
    } catch (IOException ex) {
      // ignore
    }
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 具体实现 用户登录，token验证等
  }
}
