package com.soft.sakd.biz.mange;

import com.soft.sakd.biz.param.ArticleParam;
import com.soft.sakd.common.search.bean.Result;

/**
 * @author xujie
 * @since 2020/4/2 10:01
 */
public interface ArticleMange {

  /**
   * 新增文章
   *
   * @param param
   * @return
   */
  Result saveOrUpateArticle(ArticleParam param);

  /**
   * 分页查询所有文章
   *
   * @param pageSize
   * @param page
   * @return
   */
  Result queryAll(int pageSize, int page);

  /**
   * 查询单个文章
   *
   * @param articleId
   * @return
   */
  Result queryArticleById(Long articleId);

  /**
   * 随意点赞
   *
   * @param articleId
   * @return
   */
  Result updateArticleLike(Long articleId);

  /**
   * 更新浏览量
   *
   * @param articleId
   * @return
   */
  Result updateArticleBrowse(Long articleId);
}
