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
  public Long insertArticle(ArticleDto article) {
    ArticleSrvConvert convert = new ArticleSrvConvert();
    Article articleDao = convert.toDao(article);
    int rd = articleMapper.insert(articleDao);
    if (rd == 0) {
      return 0L;
    }
    // 用户文档关系
    UserArticle userArticle = new UserArticle();
    userArticle.setUserId(article.getUserId());
    userArticle.setArticleId(articleDao.getId());
    userArticleMapper.insert(userArticle);

    // 插入技术分类
    for (ItCatoryDto itCatoryDto : article.getItCatory()) {
      ArticleItCatory itCatory = new ArticleItCatory();
      itCatory.setArticleId(articleDao.getId());
      itCatory.setItCatoryId(itCatoryDto.getId());
      articleItCatoryMapper.insert(itCatory);
    }
    // 插入文件分类
    for (FileCatoryDto fileCatoryDto : article.getFileCatory()) {
      ArticleFileCatory articleFileCatory = new ArticleFileCatory();
      articleFileCatory.setArticleId(articleDao.getId());
      articleFileCatory.setFileCatoryId(fileCatoryDto.getId());
      articleFileCatoryMapper.insert(articleFileCatory);
    }

    // 文档附件
    for (AttachmentsDto attachmentsDto : article.getAttachments()) {
      ArticleAttachments articleAttachments = new ArticleAttachments();
      articleAttachments.setAttachmentId(attachmentsDto.getId());
      articleAttachments.setArticleId(articleDao.getId());
      articleAttachmentsMapper.insert(articleAttachments);
    }
    return articleDao.getId();
  }

  @Override
  public List<ArticleDto> queryAll(int pageSize, int page) {
    List<ArticleDto> articleDtoList = Lists.newArrayList();
    // 分页查询所有文章
    List<Article> articleList = articleMapper.queryAll(pageSize, page);
    if (!CollectionUtils.isEmpty(articleList)) {
      SrvConvert itCatoryConvert = new ItCatorySrvConvert();
      SrvConvert attachmentsSrvConvert = new AttachmentsSrvConvert();
      ArticleSrvConvert articleConvert = new ArticleSrvConvert();
      FileCatorySrvConvert fileCatoryConvert = new FileCatorySrvConvert();
      for (Article item : articleList) {
        ArticleDto articleDto = articleConvert.toDto(item);
        // 文章下所有的文档分类
        articleDto.getFileCatory().addAll(fileCatoryConvert.toListDto(getFileCatory(item)));
        // 文章下所有的技术分类信息
        articleDto.getItCatory().addAll(itCatoryConvert.toListDto(getItCatory(item)));
        // 文章下所有附件
        articleDto.getItCatory().addAll(attachmentsSrvConvert.toListDto(getAttachments(item)));
        articleDtoList.add(articleDto);
      }
    }
    return articleDtoList;
  }

  private List<Attachments> getAttachments(Article item) {
    // 查询每篇文章下的所有附件
    List<ArticleAttachments> articleAttachmentsList =
        articleAttachmentsMapper.queryByArticleId(item.getId());
    List<Attachments> attachmentsList = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(articleAttachmentsList)) {
      for (ArticleAttachments attachmentsItem : articleAttachmentsList) {
        attachmentsList.add(
            attachmentsMapper.selectByPrimaryKey(attachmentsItem.getAttachmentId()));
      }
    }
    return attachmentsList;
  }

  private List<ItCatory> getItCatory(Article item) {
    // 查询每篇文章下的所有技术归类
    List<ArticleItCatory> articleItCatoryList =
        articleItCatoryMapper.queryByArticleId(item.getId());
    List<ItCatory> itCatoryList = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(articleItCatoryList)) {
      for (ArticleItCatory articleItCatoryItem : articleItCatoryList) {
        itCatoryList.add(itCatoryMapper.selectByPrimaryKey(articleItCatoryItem.getItCatoryId()));
      }
    }
    return itCatoryList;
  }

  private List<FileCatory> getFileCatory(Article item) {
    // 查询每篇文章下的所有文档归类
    List<ArticleFileCatory> articleFileCatoryList =
        articleFileCatoryMapper.queryByArticleId(item.getId());
    List<FileCatory> fileCatoryList = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(articleFileCatoryList)) {
      for (ArticleFileCatory articleItCatoryItem : articleFileCatoryList) {
        fileCatoryList.add(
            fileCatoryMapper.selectByPrimaryKey(articleItCatoryItem.getFileCatoryId()));
      }
    }
    return fileCatoryList;
  }

  @Override
  public Integer countArticle() {
    return articleMapper.countArticle();
  }

  @Override
  public ArticleDto queryArticleById(Long id) {
    ArticleSrvConvert articleConvert = new ArticleSrvConvert();
    FileCatorySrvConvert fileCatoryConvert = new FileCatorySrvConvert();
    ItCatorySrvConvert itCatoryConvert = new ItCatorySrvConvert();
    AttachmentsSrvConvert attachmentsSrvConvert = new AttachmentsSrvConvert();
    Article article = articleMapper.selectByPrimaryKey(id);
    ArticleDto articleDto = articleConvert.toDto(article);
    // 文档文件分类
    articleDto.setFileCatory(fileCatoryConvert.toListDto(getFileCatory(article)));
    // 文档it分类
    articleDto.setItCatory(itCatoryConvert.toListDto(getItCatory(article)));
    // 文档附件
    articleDto.setAttachments(attachmentsSrvConvert.toListDto(getAttachments(article)));
    return articleDto;
  }

  @Transactional
  @Override
  public Long updateArticle(ArticleDto articleDto) throws ServiceException {

    if (null == articleDto.getId()) {
      throw new ServiceException("更新文章时，ID不为空");
    }

    ArticleSrvConvert articleConvert = new ArticleSrvConvert();

    articleItCatoryMapper.deleteAllByArtileId(articleDto.getId());
    articleFileCatoryMapper.deleteAllByArtileId(articleDto.getId());
    articleAttachmentsMapper.deleteAllByArtileId(articleDto.getId());

    List<ArticleFileCatory> articleFileCatoryList = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(articleDto.getFileCatory())) {
      for (FileCatoryDto item : articleDto.getFileCatory()) {
        ArticleFileCatory articleFileCatory = new ArticleFileCatory();
        articleFileCatory.setFileCatoryId(item.getId());
        articleFileCatory.setArticleId(articleDto.getId());
        articleFileCatoryList.add(articleFileCatory);
      }
      // 维护 文档归类 关系
      articleFileCatoryMapper.insertBatch(articleFileCatoryList);
    }

    List<ArticleItCatory> articleItCatoryList = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(articleDto.getItCatory())) {
      for (ItCatoryDto item : articleDto.getItCatory()) {
        ArticleItCatory articleItCatory = new ArticleItCatory();
        articleItCatory.setItCatoryId(item.getId());
        articleItCatory.setArticleId(articleDto.getId());
        articleItCatoryList.add(articleItCatory);
      }
      // 维护 技术归类 关系
      articleItCatoryMapper.insertBatch(articleItCatoryList);
    }

    List<ArticleAttachments> articleAttachmentsList = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(articleDto.getAttachments())) {
      for (AttachmentsDto item : articleDto.getAttachments()) {
        ArticleAttachments articleAttachments = new ArticleAttachments();
        articleAttachments.setAttachmentId(item.getId());
        articleAttachments.setArticleId(articleDto.getId());
        articleAttachmentsList.add(articleAttachments);
      }
      // 维护 文档附件
      articleAttachmentsMapper.insertBatch(articleAttachmentsList);
    }
    articleMapper.updateByPrimaryKey(articleConvert.toDao(articleDto));
    return 1L;
  }

  @Override
  public int updateArticleLike(Long id) {
    Article article = articleMapper.selectByPrimaryKey(id);
    if (article == null) {
      return 0;
    }
    article.setLikeCount((null == article.getLikeCount() ? 0 : article.getLikeCount()) + 1);
    return articleMapper.updateArticleLike(article);
  }

  @Override
  public int updateArticleBrowse(Long id) {
    Article article = articleMapper.selectByPrimaryKey(id);
    if (article == null) {
      return 0;
    }
    article.setBrowseCount((null == article.getBrowseCount() ? 0 : article.getBrowseCount()) + 1);
    return articleMapper.updateArticleBrowse(article);
  }
}
