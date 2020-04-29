package com.soft.sakd.biz.mange.impl;

import com.alibaba.fastjson.JSON;
import com.soft.sakd.biz.convert.impl.ArticleBizConvert;
import com.soft.sakd.biz.mange.ArticleMange;
import com.soft.sakd.biz.param.ArticleParam;
import com.soft.sakd.common.dto.ArticleDto;
import com.soft.sakd.common.exception.ServiceException;
import com.soft.sakd.common.facade.ArticleService;
import com.soft.sakd.common.search.bean.Result;
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
  public Result saveOrUpateArticle(ArticleParam param) {

    Assert.notNull(param, "参数不为空");
    Assert.notNull(param.getContent(), "文本参数不为空");
    Assert.notNull(param.getFileName(), "文档名不为空");
    Assert.notNull(param.getFileDesc(), "文档描述不为空");

    Result result = Result.makeSuccessResult();
    // 处理
    try {
      saveOrUpdateArticle(param);
    } catch (ServiceException e) {
      log.error("入参：" + JSON.toJSONString(param), e);
      result.setStatus(false);
      result.setMsg("数据新增失败");
    }
    return result;
  }

  private void saveOrUpdateArticle(ArticleParam param) throws ServiceException {
    ArticleBizConvert articleConvert = new ArticleBizConvert();
    ArticleDto articleDto = articleConvert.paramToDto(param);
    if (null == param.getId() || param.getId() <= 0) {
      articleService.insertArticle(articleDto);
    } else {
      articleService.updateArticle(articleDto);
    }
  }

  @Override
  public Result queryAll(int pageSize, int page) {
    Assert.notNull(page, "pageSize不为空");
    Assert.notNull(pageSize, "page不为空");
    return Result.makeSuccessResult(articleService.queryAll(pageSize, page), articleService.countArticle());
  }

  @Override
  public Result queryArticleById(Long articleId) {
    Assert.notNull(articleId, "id不为空");
    ArticleBizConvert articleConvert = new ArticleBizConvert();
    return Result.makeSuccessResult(articleConvert.dtoToVo(articleService.queryArticleById(articleId)));
  }

  @Override
  public Result updateArticleLike(Long articleId) {
    return Result.makeSuccessResult(articleService.updateArticleLike(articleId) > 0); // articleService.updateArticleLike(articleId) > 0 表示成功
  }

  @Override
  public Result updateArticleBrowse(Long articleId) {
    return Result.makeStatusResult(articleService.updateArticleBrowse(articleId) > 0); // articleService.updateArticleBrowse(articleId) > 0 表示成功
  }
}
