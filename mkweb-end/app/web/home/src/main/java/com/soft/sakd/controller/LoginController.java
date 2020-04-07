package com.soft.sakd.controller;

import com.soft.sakd.biz.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujie
 * @since 2020/4/4 16:59
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class LoginController {

  // TODO 1.antd + sofa后端登录及权限
  @Autowired
  @RequestMapping("/currentUser")
  public UserVo currentUser() {
    UserVo userVo = new UserVo();
    userVo.setName("刀马客");
    userVo.setAvatar("https://www.3jxq.com/public/upload/714d0ddeb1a6085900b8c4744bd79e43.png");
    userVo.setUserId(1L);
    userVo.setEmail("jxtpro@163.com");
    userVo.getAuth().add("user");
    return userVo;
  }
  // 实现就行
  @RequestMapping("/login/account")
  public Object login() {
    return null;
  }
  // 实现就行
  @RequestMapping("/auth_routes")
  public Object authRoutes() {
    return "{\n"
        + "  \"/\": {\n"
        + "    \"authority\": [\n"
        + "      \"admin\",\n"
        + "      \"user\"\n"
        + "    ]\n"
        + "  }\n"
        + "}";
  }
}
