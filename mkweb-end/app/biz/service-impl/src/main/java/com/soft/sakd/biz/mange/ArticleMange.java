package com.soft.sakd.biz.mange;

import com.soft.sakd.biz.param.ArticleParam;
import com.soft.sakd.common.search.bean.SearchResult;

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
  SearchResult saveOrUpateArticle(ArticleParam param);

  /**
   * 分页查询所有文章
   *
   * @param pageSize
   * @param page
   * @return
   */
  SearchResult queryAll(int pageSize, int page);

  /**
   * 查询单个文章
   *
   * @param id
   * @return
   */
  SearchResult queryArticleById(Long id);

  /**
   * 随意点赞
   *
   * @param id
   * @return
   */
  SearchResult updateArticleLike(Long id);

  /**
   * 更新浏览量
   *
   * @param id
   * @return
   */
  SearchResult updateArticleBrowse(Long id);
}
