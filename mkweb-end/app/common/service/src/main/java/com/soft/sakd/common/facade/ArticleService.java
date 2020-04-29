package com.soft.sakd.common.facade;

import com.soft.sakd.common.dto.ArticleDto;
import com.soft.sakd.common.exception.ServiceException;
import java.util.List;

/**
 * @author xujie
 * @since 2020/4/2 10:23
 */
public interface ArticleService {

  // 新增文章
  ArticleDto insertArticle(ArticleDto article) throws ServiceException;

  // 分页查询
  List<ArticleDto> queryAll(int pageSize, int page);

  // 统计文章总数
  Integer countArticle();

  // 按照ID查询文档详情
  ArticleDto queryArticleById(Long id);

  // 更新文章
  void updateArticle(ArticleDto articleDto) throws ServiceException;

  // 返回影响行数
  int updateArticleLike(Long id);

  int updateArticleBrowse(Long id);
}
