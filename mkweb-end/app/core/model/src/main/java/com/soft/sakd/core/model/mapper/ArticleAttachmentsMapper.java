package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.ArticleAttachments;
import java.util.List;

public interface ArticleAttachmentsMapper {
  int deleteByPrimaryKey(Long id);

  int insert(ArticleAttachments record);

  ArticleAttachments selectByPrimaryKey(Long id);

  List<ArticleAttachments> selectAll();

  int updateByPrimaryKey(ArticleAttachments record);

  void deleteAllByArtileId(Long id);

  void insertBatch(List<ArticleAttachments> articleAttachmentsList);

  List<ArticleAttachments> queryByArticleId(Long id);
}
