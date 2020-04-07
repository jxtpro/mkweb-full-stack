package com.soft.sakd.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.soft.sakd.common.exception.ServiceException;
import com.soft.sakd.common.search.bean.SearchResult;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
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
    config.setSerializerFeatures(
        SerializerFeature.WriteMapNullValue, // 保留空的字段
        SerializerFeature.WriteNullStringAsEmpty, // String null -> ""
        SerializerFeature.WriteNullNumberAsZero); // Number null -> 0
    converter.setFastJsonConfig(config);
    converter.setDefaultCharset(Charset.forName("UTF-8"));
    converters.add(converter);
  }

  @Override
  public void configureHandlerExceptionResolvers(
      List<HandlerExceptionResolver> exceptionResolvers) {
    exceptionResolvers.add(
        (request, response, handler, e) -> {
          HandlerMethod handlerMethod = (HandlerMethod) handler;
          if (isServiceException(e)) { // 业务失败的异常，如“账号或密码错误”
            log.error(e);
            return getResult(response, "服务层出现异常", e);
          }
          if (isNohandlerFoundException(e)) { // 业务失败的异常，如“账号或密码错误”
            return getResult(response, "接口 [" + request.getRequestURI() + "] 不存在", e);
          }
          if (isIshandlerMethod(handler)) {
            String message =
                String.format(
                    "接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                    request.getRequestURI(),
                    handlerMethod.getBean().getClass().getName(),
                    handlerMethod.getMethod().getName(),
                    e.getMessage());
            log.error(message);
            return getResult(response, "接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员", e);
          }
          return new ModelAndView();
        });
  }

  private ModelAndView getResult(HttpServletResponse response, String msg, Exception e) {
    SearchResult result = new SearchResult();
    result.setStatus(false);
    result.setMsg(msg);
    try {
      response.setContentType("text/html; charset=UTF-8");
      response.getWriter().print(JSON.toJSON(result));
      response.getWriter().flush();
    } catch (IOException ex) {
      // ignore
    }
    return new ModelAndView();
  }

  private boolean isIshandlerMethod(Object handler) {
    return handler instanceof HandlerMethod;
  }

  private boolean isNohandlerFoundException(Exception e) {
    return e instanceof NoHandlerFoundException;
  }

  private boolean isServiceException(Exception e) {
    return e instanceof ServiceException;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 具体实现, 用户登录, token验证等
  }
}
