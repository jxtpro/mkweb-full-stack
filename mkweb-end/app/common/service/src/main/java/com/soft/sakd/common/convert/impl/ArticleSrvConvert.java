package com.soft.sakd.common.convert.impl;

import com.google.common.collect.Lists;
import com.soft.sakd.common.convert.SrvConvert;
import com.soft.sakd.common.dto.ArticleDto;
import com.soft.sakd.core.model.entity.Article;
import java.util.List;

/**
 * @author xujie
 * @since 2020/4/2 15:15
 */
public class ArticleSrvConvert implements SrvConvert<ArticleDto, Article> {

  @Override
  public Article toDao(ArticleDto articleDto) {
    Article articleDao = new Article();
    articleDao.setUserId(articleDto.getUserId());
    articleDao.setBrowseCount(articleDto.getBrowseCount());
    articleDao.setLikeCount(articleDto.getLikeCount());
    articleDao.setId(articleDto.getId());
    articleDao.setFileName(articleDto.getFileName());
    articleDao.setFileDesc(articleDto.getFileDesc());
    articleDao.setContent(articleDto.getContent());
    return articleDao;
  }

  @Override
  public ArticleDto toDto(Article article) {
    ArticleDto dto = new ArticleDto();
    dto.setId(article.getId());
    dto.setBrowseCount(article.getBrowseCount());
    dto.setLikeCount(article.getLikeCount());
    dto.setUserId(article.getUserId());
    dto.setFileName(article.getFileName());
    dto.setFileDesc(article.getFileDesc());
    dto.setContent(article.getContent());
    return dto;
  }

  public List<ArticleDto> toListDto(List<Article> articleList) {
    List<ArticleDto> articleDtoList = Lists.newArrayList();
    articleList.forEach(
        item -> {
          articleDtoList.add(toDto(item));
        });
    return articleDtoList;
  }

  @Override
  public List<Article> toListDao(List<ArticleDto> attachmentsDtoList) {
    return null;
  }
}
