package com.soft.sakd.biz.mange.impl;

import com.alibaba.fastjson.JSON;
import com.soft.sakd.biz.convert.impl.ArticleBizConvert;
import com.soft.sakd.biz.mange.ArticleMange;
import com.soft.sakd.biz.param.ArticleParam;
import com.soft.sakd.common.dto.ArticleDto;
import com.soft.sakd.common.facade.ArticleService;
import com.soft.sakd.common.search.bean.SearchResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author xujie
 * @since 2020/4/2 10:03
 */
@Service
@Log4j2
public class ArticleMangeImpl implements ArticleMange {

  @Autowired private ArticleService articleService;

  @Override
  public SearchResult saveOrUpateArticle(ArticleParam param) {
    Assert.notNull(param, "参数不为空");
    Assert.notNull(param.getContent(), "文本参数不为空");
    Assert.notNull(param.getFileName(), "文档名不为空");
    Assert.notNull(param.getFileDesc(), "文档描述不为空");

    SearchResult result = new SearchResult();
    // 处理
    ArticleBizConvert articleConvert = new ArticleBizConvert();
    try {
      ArticleDto articleDto = articleConvert.paramToDto(param);
      Long r = 0L;
      if (null == param.getId() || param.getId() <= 0) {
        r = articleService.insertArticle(articleDto);

      } else {
        r = articleService.updateArticle(articleDto);
      }
      if (r == 0) {
        result.setStatus(false);
      }
    } catch (Exception e) {
      log.error("入参：" + JSON.toJSONString(param), e);
      result.setStatus(false);
      result.setMsg("数据新增失败");
    }
    return result;
  }

  @Override
  public SearchResult queryAll(int pageSize, int page) {
    Assert.notNull(page, "pageSize不为空");
    Assert.notNull(pageSize, "page不为空");
    SearchResult result = new SearchResult();
    result.setList(articleService.queryAll(pageSize, page));
    result.setTotalCount(articleService.countArticle());
    return result;
  }

  @Override
  public SearchResult queryArticleById(Long id) {
    Assert.notNull(id, "id不为空");
    SearchResult result = new SearchResult();
    ArticleBizConvert articleConvert = new ArticleBizConvert();
    result.setData(articleConvert.dtoToVo(articleService.queryArticleById(id)));
    return result;
  }

  @Override
  public SearchResult updateArticleLike(Long id) {
    SearchResult result = new SearchResult();
    result.setStatus(articleService.updateArticleLike(id) > 0); // > 0 表示成功
    return result;
  }

  @Override
  public SearchResult updateArticleBrowse(Long id) {
    SearchResult result = new SearchResult();
    result.setStatus(articleService.updateArticleBrowse(id) > 0); // > 0 表示成功
    return result;
  }
}
