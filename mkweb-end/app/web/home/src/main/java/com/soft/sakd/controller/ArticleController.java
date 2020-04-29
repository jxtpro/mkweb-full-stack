package com.soft.sakd.controller;

import com.soft.sakd.biz.mange.ArticleMange;
import com.soft.sakd.biz.param.ArticleParam;
import com.soft.sakd.common.search.bean.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujie
 * @since 2020/4/2 0:59
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class ArticleController {

  @Autowired
  private ArticleMange articleMange;

  @RequestMapping("/saveOrUpateArticle")
  @ResponseBody
  public Result saveOrUpateArticle(@RequestBody ArticleParam param) {
    return articleMange.saveOrUpateArticle(param);
  }

  @RequestMapping("/queryArticleAll/{pageSize}/{page}")
  @ResponseBody
  public Result queryArticleAll(@PathVariable(name = "pageSize") Integer pageSize, @PathVariable("page") Integer page) {
    return articleMange.queryAll(pageSize, page);
  }

  @RequestMapping("/queryArticle/{id}")
  @ResponseBody
  public Result queryArticle(@PathVariable(name = "id") Long articleId) {
    return articleMange.queryArticleById(articleId);
  }

  @RequestMapping("/updateArticleLike/{id}")
  @ResponseBody
  public Result updateArticleLike(@PathVariable(name = "id") Long articleId) {
    return articleMange.updateArticleLike(articleId);
  }

  @RequestMapping("/updateArticleBrowse/{id}")
  @ResponseBody
  public Result updateArticleBrowse(@PathVariable(name = "id") Long articleId) {
    return articleMange.updateArticleBrowse(articleId);
  }
}
