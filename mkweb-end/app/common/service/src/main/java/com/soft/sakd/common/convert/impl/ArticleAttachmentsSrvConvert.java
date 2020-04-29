package com.soft.sakd.common.convert.impl;

import com.google.common.collect.Lists;
import com.soft.sakd.common.convert.SrvConvert;
import com.soft.sakd.common.dto.ArticleAttachmentsDto;
import com.soft.sakd.core.model.entity.ArticleAttachments;
import java.util.List;

/**
 * @author Administrator
 * @since 2020/4/5 23:12
 */
public class ArticleAttachmentsSrvConvert
    implements SrvConvert<ArticleAttachmentsDto, ArticleAttachments> {

  @Override
  public ArticleAttachments toDao(ArticleAttachmentsDto articleAttachmentsDto) {
    ArticleAttachments articleAttachments = new ArticleAttachments();
    articleAttachments.setArticleId(articleAttachmentsDto.getArticleId());
    articleAttachments.setId(articleAttachmentsDto.getId());
    articleAttachments.setAttachmentId(articleAttachmentsDto.getAttachmentId());
    return articleAttachments;
  }

  @Override
  public ArticleAttachmentsDto toDto(ArticleAttachments articleAttachments) {
    ArticleAttachmentsDto articleAttachmentsDto = new ArticleAttachmentsDto();
    articleAttachmentsDto.setArticleId(articleAttachments.getArticleId());
    articleAttachmentsDto.setId(articleAttachments.getId());
    articleAttachmentsDto.setAttachmentId(articleAttachments.getAttachmentId());
    return articleAttachmentsDto;
  }

  @Override
  public List<ArticleAttachmentsDto> toListDto(List<ArticleAttachments> attachmentsList) {
    List<ArticleAttachmentsDto> articleAttachmentsDtoList = Lists.newArrayList();
    attachmentsList.forEach(attachments -> {
      articleAttachmentsDtoList.add(toDto(attachments));
    });
    return articleAttachmentsDtoList;
  }

  @Override
  public List<ArticleAttachments> toListDao(List<ArticleAttachmentsDto> attachmentsDtoList) {
    List<ArticleAttachments> articleAttachmentsList = Lists.newArrayList();
    attachmentsDtoList.forEach(attachmentsDto -> {
      articleAttachmentsList.add(toDao(attachmentsDto));
    });
    return articleAttachmentsList;
  }
}
