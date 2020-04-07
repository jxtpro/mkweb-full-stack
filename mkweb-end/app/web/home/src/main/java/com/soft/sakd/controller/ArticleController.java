package com.soft.sakd.controller;

import com.soft.sakd.biz.mange.ArticleMange;
import com.soft.sakd.biz.param.ArticleParam;
import com.soft.sakd.common.search.bean.SearchResult;
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
  @Autowired public ArticleMange articleMange;

  @RequestMapping("/saveOrUpateArticle")
  @ResponseBody
  public SearchResult saveOrUpateArticle(@RequestBody ArticleParam param) {
    return articleMange.saveOrUpateArticle(param);
  }

  @RequestMapping("/queryArticleAll/{pageSize}/{page}")
  @ResponseBody
  public SearchResult queryArticleAll(
      @PathVariable(name = "pageSize") Integer pageSize, @PathVariable("page") Integer page) {
    return articleMange.queryAll(pageSize, page);
  }

  @RequestMapping("/queryArticle/{id}")
  @ResponseBody
  public SearchResult queryArticle(@PathVariable(name = "id") Long id) {
    return articleMange.queryArticleById(id);
  }

  @RequestMapping("/updateArticleLike/{id}")
  @ResponseBody
  public SearchResult updateArticleLike(@PathVariable(name = "id") Long id) {
    return articleMange.updateArticleLike(id);
  }

  @RequestMapping("/updateArticleBrowse/{id}")
  @ResponseBody
  public SearchResult updateArticleBrowse(@PathVariable(name = "id") Long id) {
    return articleMange.updateArticleBrowse(id);
  }
}
