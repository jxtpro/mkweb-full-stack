package com.soft.sakd.common.facade.impl;

import com.google.common.collect.Lists;
import com.soft.sakd.common.convert.SrvConvert;
import com.soft.sakd.common.convert.impl.ArticleSrvConvert;
import com.soft.sakd.common.convert.impl.AttachmentsSrvConvert;
import com.soft.sakd.common.convert.impl.FileCatorySrvConvert;
import com.soft.sakd.common.convert.impl.ItCatorySrvConvert;
import com.soft.sakd.common.dto.ArticleDto;
import com.soft.sakd.common.dto.AttachmentsDto;
import com.soft.sakd.common.dto.FileCatoryDto;
import com.soft.sakd.common.dto.ItCatoryDto;
import com.soft.sakd.common.exception.ServiceException;
import com.soft.sakd.common.facade.ArticleService;
import com.soft.sakd.core.model.entity.Article;
import com.soft.sakd.core.model.entity.ArticleAttachments;
import com.soft.sakd.core.model.entity.ArticleFileCatory;
import com.soft.sakd.core.model.entity.ArticleItCatory;
import com.soft.sakd.core.model.entity.Attachments;
import com.soft.sakd.core.model.entity.FileCatory;
import com.soft.sakd.core.model.entity.ItCatory;
import com.soft.sakd.core.model.entity.UserArticle;
import com.soft.sakd.core.model.mapper.ArticleAttachmentsMapper;
import com.soft.sakd.core.model.mapper.ArticleFileCatoryMapper;
import com.soft.sakd.core.model.mapper.ArticleItCatoryMapper;
import com.soft.sakd.core.model.mapper.ArticleMapper;
import com.soft.sakd.core.model.mapper.AttachmentsMapper;
import com.soft.sakd.core.model.mapper.FileCatoryMapper;
import com.soft.sakd.core.model.mapper.ItCatoryMapper;
import com.soft.sakd.core.model.mapper.UserArticleMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author xujie
 * @since 2020/4/2 10:24
 */
@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired private ArticleMapper articleMapper;
  @Autowired private UserArticleMapper userArticleMapper;
  @Autowired private ArticleFileCatoryMapper articleFileCatoryMapper;
  @Autowired private ArticleItCatoryMapper articleItCatoryMapper;
  @Autowired private FileCatoryMapper fileCatoryMapper;
  @Autowired private ItCatoryMapper itCatoryMapper;
  @Autowired private ArticleAttachmentsMapper articleAttachmentsMapper;
  @Autowired private AttachmentsMapper attachmentsMapper;

  @Transactional
  @Override
  public ArticleDto insertArticle(ArticleDto articleDto) throws ServiceException {

    Assert.notNull(articleDto, "文档不为空");
    Assert.notNull(articleDto.getContent(), "文本参数不为空");
    Assert.notNull(articleDto.getFileName(), "文档名不为空");
    Assert.notNull(articleDto.getFileDesc(), "文档描述不为空");

    ArticleSrvConvert convert = new ArticleSrvConvert();
    Article article = convert.toDao(articleDto);

    articleMapper.insert(article);
    UserArticle userArticle = new UserArticle();
    userArticle.setUserId(articleDto.getUserId());
    userArticle.setArticleId(article.getId());

    userArticleMapper.insert(userArticle);
    insertBatchArticleFileCatoryRelated(articleDto);
    insertBatchArticleItCatoryRelated(articleDto);
    insertBatchArtcileAttachmentRelated(articleDto);

    return convert.toDto(article);
  }

  @Override
  public List<ArticleDto> queryAll(int pageSize, int page) {
    return convertArticleRelated(articleMapper.queryAll(pageSize, page));
  }

  /**
   * 处理文档关系
   *
   * @param articleList
   */
  private List<ArticleDto> convertArticleRelated(List<Article> articleList) {
    List<ArticleDto> articleDtoList = Lists.newArrayList();
    if (CollectionUtils.isEmpty(articleList)) {
      return articleDtoList;
    }
    SrvConvert itCatoryConvert = new ItCatorySrvConvert();
    SrvConvert attachmentsSrvConvert = new AttachmentsSrvConvert();
    ArticleSrvConvert articleConvert = new ArticleSrvConvert();
    FileCatorySrvConvert fileCatoryConvert = new FileCatorySrvConvert();
    for (Article article : articleList) {
      ArticleDto articleDto = articleConvert.toDto(article);
      articleDto.getFileCatory().addAll(fileCatoryConvert.toListDto(findFileCatoryByArticleId(article.getId())));
      articleDto.getItCatory().addAll(itCatoryConvert.toListDto(findItCatoryByArticleId(article.getId())));
      articleDto.getAttachments().addAll(attachmentsSrvConvert.toListDto(findAttachmentsByArticleId(article.getId())));
      articleDtoList.add(articleDto);
    }
    return articleDtoList;
  }

  private List<Attachments> findAttachmentsByArticleId(Long articleId) {
    List<ArticleAttachments> articleAttachmentsList = articleAttachmentsMapper.queryByArticleId(articleId);
    List<Attachments> attachmentsList = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(articleAttachmentsList)) {
      for (ArticleAttachments attachmentsItem : articleAttachmentsList) {
        attachmentsList.add(attachmentsMapper.selectByPrimaryKey(attachmentsItem.getAttachmentId()));
      }
    }
    return attachmentsList;
  }

  private List<ItCatory> findItCatoryByArticleId(Long articleId) {
    List<ArticleItCatory> articleItCatoryList = articleItCatoryMapper.queryByArticleId(articleId);
    List<ItCatory> itCatoryList = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(articleItCatoryList)) {
      for (ArticleItCatory articleItCatoryItem : articleItCatoryList) {
        itCatoryList.add(itCatoryMapper.selectByPrimaryKey(articleItCatoryItem.getItCatoryId()));
      }
    }
    return itCatoryList;
  }

  private List<FileCatory> findFileCatoryByArticleId(Long articleId) {
    List<ArticleFileCatory> articleFileCatoryList = articleFileCatoryMapper.queryByArticleId(articleId);
    List<FileCatory> fileCatoryList = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(articleFileCatoryList)) {
      for (ArticleFileCatory articleItCatoryItem : articleFileCatoryList) {
        fileCatoryList.add(fileCatoryMapper.selectByPrimaryKey(articleItCatoryItem.getFileCatoryId()));
      }
    }
    return fileCatoryList;
  }

  @Override
  public Integer countArticle() {
    return articleMapper.countArticle();
  }

  @Override
  public ArticleDto queryArticleById(Long articleId) {

    Assert.notNull(articleId, "文档ID，不为空");

    ArticleSrvConvert articleConvert = new ArticleSrvConvert();
    FileCatorySrvConvert fileCatoryConvert = new FileCatorySrvConvert();
    ItCatorySrvConvert itCatoryConvert = new ItCatorySrvConvert();
    AttachmentsSrvConvert attachmentsSrvConvert = new AttachmentsSrvConvert();

    Article article = articleMapper.selectByPrimaryKey(articleId);

    ArticleDto articleDto = articleConvert.toDto(article);
    articleDto.setFileCatory(fileCatoryConvert.toListDto(findFileCatoryByArticleId(articleId)));
    articleDto.setItCatory(itCatoryConvert.toListDto(findItCatoryByArticleId(articleId)));
    articleDto.setAttachments(attachmentsSrvConvert.toListDto(findAttachmentsByArticleId(articleId)));
    return articleDto;
  }

  /**
   * 更新成功返回 1L, 失败返回 0L
   *
   * @param articleDto
   * @return
   * @throws ServiceException
   */
  @Transactional
  @Override
  public void updateArticle(ArticleDto articleDto) throws ServiceException {
    Assert.notNull(articleDto, "更新的文档信息，不为空");
    Assert.notNull(articleDto.getId(), "更新的文档ID，不为空");

    Article article = articleMapper.selectByPrimaryKey(articleDto.getId());
    // 是否有这个文档
    if (article == null) {
      throw new ServiceException("");
    }

    ArticleSrvConvert articleConvert = new ArticleSrvConvert();
    articleItCatoryMapper.deleteAllByArtileId(articleDto.getId());
    articleFileCatoryMapper.deleteAllByArtileId(articleDto.getId());
    articleAttachmentsMapper.deleteAllByArtileId(articleDto.getId());

    insertBatchArticleFileCatoryRelated(articleDto);
    insertBatchArticleItCatoryRelated(articleDto);
    insertBatchArtcileAttachmentRelated(articleDto);

    articleMapper.updateByPrimaryKey(articleConvert.toDao(articleDto));
  }

  private void insertBatchArtcileAttachmentRelated(ArticleDto articleDto) {
    if (!CollectionUtils.isEmpty(articleDto.getAttachments())) {
      List<ArticleAttachments> articleAttachmentsList = Lists.newArrayList();
      for (AttachmentsDto item : articleDto.getAttachments()) {
        ArticleAttachments articleAttachments = new ArticleAttachments();
        articleAttachments.setAttachmentId(item.getId());
        articleAttachments.setArticleId(articleDto.getId());
        articleAttachmentsList.add(articleAttachments);
      }
      articleAttachmentsMapper.insertBatch(articleAttachmentsList);
    }
  }

  private void insertBatchArticleItCatoryRelated(ArticleDto articleDto) {
    if (!CollectionUtils.isEmpty(articleDto.getItCatory())) {
      List<ArticleItCatory> articleItCatoryList = Lists.newArrayList();
      for (ItCatoryDto item : articleDto.getItCatory()) {
        ArticleItCatory articleItCatory = new ArticleItCatory();
        articleItCatory.setItCatoryId(item.getId());
        articleItCatory.setArticleId(articleDto.getId());
        articleItCatoryList.add(articleItCatory);
      }
      articleItCatoryMapper.insertBatch(articleItCatoryList);
    }
  }

  private void insertBatchArticleFileCatoryRelated(ArticleDto articleDto) {
    if (!CollectionUtils.isEmpty(articleDto.getFileCatory())) {
      List<ArticleFileCatory> articleFileCatoryList = Lists.newArrayList();
      for (FileCatoryDto item : articleDto.getFileCatory()) {
        ArticleFileCatory articleFileCatory = new ArticleFileCatory();
        articleFileCatory.setFileCatoryId(item.getId());
        articleFileCatory.setArticleId(articleDto.getId());
        articleFileCatoryList.add(articleFileCatory);
      }
      articleFileCatoryMapper.insertBatch(articleFileCatoryList);
    }
  }

  @Override
  public int updateArticleLike(Long articleId) {

    Assert.notNull(articleId, "文档ID，不为空");

    Article article = articleMapper.selectByPrimaryKey(articleId);
    // 是否有这个文档
    if (article == null) {
      return 0;
    }

    article.setLikeCount((null == article.getLikeCount() ? 0 : article.getLikeCount()) + 1);
    return articleMapper.updateArticleLike(article);
  }

  @Override
  public int updateArticleBrowse(Long articleId) {
    Assert.notNull(articleId, "文档ID，不为空");
    Article article = articleMapper.selectByPrimaryKey(articleId);
    // 是否有这个文档
    if (article == null) {
      return 0;
    }
    article.setBrowseCount((null == article.getBrowseCount() ? 0 : article.getBrowseCount()) + 1);
    return articleMapper.updateArticleBrowse(article);
  }
}
