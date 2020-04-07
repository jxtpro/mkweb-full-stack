package com.soft.sakd.biz.convert.impl;

import com.soft.sakd.biz.convert.BizConvert;
import com.soft.sakd.biz.param.ArticleParam;
import com.soft.sakd.biz.vo.ArticleVo;
import com.soft.sakd.biz.vo.AttachmentsVo;
import com.soft.sakd.biz.vo.FileCatoryVo;
import com.soft.sakd.biz.vo.ItCatoryVo;
import com.soft.sakd.common.dto.ArticleDto;
import com.soft.sakd.common.dto.AttachmentsDto;
import com.soft.sakd.common.dto.FileCatoryDto;
import com.soft.sakd.common.dto.ItCatoryDto;

/**
 * @author xujie
 * @since 2020/4/2 15:15
 */
public class ArticleBizConvert implements BizConvert<ArticleVo, ArticleParam, ArticleDto> {

  @Override
  public ArticleDto paramToDto(ArticleParam param) {
    ArticleDto articleDto = new ArticleDto();
    articleDto.setId(param.getId());
    articleDto.setContent(param.getContent());
    articleDto.setFileName(param.getFileName());
    articleDto.setFileDesc(param.getFileDesc());
    articleDto.setUserId(param.getUserId());
    param
        .getFileCatory()
        .forEach(
            item -> {
              FileCatoryDto dto = new FileCatoryDto();
              dto.setId(item);
              articleDto.getFileCatory().add(dto);
            });
    param
        .getItCatory()
        .forEach(
            item -> {
              ItCatoryDto dto = new ItCatoryDto();
              dto.setId(item);
              articleDto.getItCatory().add(dto);
            });

    param
        .getAttachments()
        .forEach(
            item -> {
              AttachmentsDto dto = new AttachmentsDto();
              dto.setId(item.getId());
              articleDto.getAttachments().add(dto);
            });
    return articleDto;
  }

  @Override
  public ArticleVo dtoToVo(ArticleDto articleDto) {
    ArticleVo articleVo = new ArticleVo();
    articleVo.setUserId(articleDto.getUserId());
    articleVo.setId(articleDto.getId());
    articleVo.setFileName(articleDto.getFileName());
    articleVo.setFileDesc(articleDto.getFileDesc());
    articleVo.setContent(articleDto.getContent());

    for (FileCatoryDto item : articleDto.getFileCatory()) {
      FileCatoryVo fileCatoryVo = new FileCatoryVo();
      fileCatoryVo.setId(item.getId());
      fileCatoryVo.setName(item.getName());
      articleVo.getFileCatory().add(fileCatoryVo);
    }
    for (ItCatoryDto item : articleDto.getItCatory()) {
      ItCatoryVo itCatoryVo = new ItCatoryVo();
      itCatoryVo.setId(item.getId());
      itCatoryVo.setName(item.getName());
      articleVo.getItCatory().add(itCatoryVo);
    }

    for (AttachmentsDto item : articleDto.getAttachments()) {
      AttachmentsVo attachmentsVo = new AttachmentsVo();
      attachmentsVo.setId(item.getId());
      attachmentsVo.setName(item.getName());
      attachmentsVo.setSize(item.getSize());
      attachmentsVo.setUrl(item.getUrl());
      attachmentsVo.setUid(item.getUid());
      attachmentsVo.setType(item.getType());
      attachmentsVo.setStatus(item.getStatus());
      articleVo.getAttachments().add(attachmentsVo);
    }
    return articleVo;
  }
}
