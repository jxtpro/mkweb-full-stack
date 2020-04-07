package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.ArticleFileCatory;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleFileCatoryMapper {
  int insert(ArticleFileCatory record);

  List<ArticleFileCatory> selectAll();
  // 查询文章下所有文档分类
  List<ArticleFileCatory> queryByArticleId(Long id);

  // 根据文章ID删除关系
  int deleteAllByArtileId(@Param("articleId") Long articleId);

  void insertBatch(List<ArticleFileCatory> articleFileCatoryList);
}
