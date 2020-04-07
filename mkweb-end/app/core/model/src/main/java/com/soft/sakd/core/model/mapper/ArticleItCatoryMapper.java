package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.ArticleItCatory;
import java.util.List;

public interface ArticleItCatoryMapper {
  int insert(ArticleItCatory record);

  List<ArticleItCatory> selectAll();
  // 文章下所有的技术分类
  List<ArticleItCatory> queryByArticleId(Long id);

  // 删除该文章技术归类关系
  void deleteAllByArtileId(Long id);
  // 批量
  void insertBatch(List<ArticleItCatory> articleItCatoryList);
}
