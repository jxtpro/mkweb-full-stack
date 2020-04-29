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
    param.getFileCatory().forEach(fileCatoryId -> {
      FileCatoryDto dto = new FileCatoryDto();
      dto.setId(fileCatoryId);
      articleDto.getFileCatory().add(dto);
    });
    param.getItCatory().forEach(itCatoryId -> {
      ItCatoryDto dto = new ItCatoryDto();
      dto.setId(itCatoryId);
      articleDto.getItCatory().add(dto);
    });
    param.getAttachments().forEach(attachmentsParam -> {
      AttachmentsDto dto = new AttachmentsDto();
      dto.setId(attachmentsParam.getId());
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

    for (FileCatoryDto fileCatoryDto : articleDto.getFileCatory()) {
      FileCatoryVo fileCatoryVo = new FileCatoryVo();
      fileCatoryVo.setId(fileCatoryDto.getId());
      fileCatoryVo.setName(fileCatoryDto.getName());
      articleVo.getFileCatory().add(fileCatoryVo);
    }
    for (ItCatoryDto itCatoryDto : articleDto.getItCatory()) {
      ItCatoryVo itCatoryVo = new ItCatoryVo();
      itCatoryVo.setId(itCatoryDto.getId());
      itCatoryVo.setName(itCatoryDto.getName());
      articleVo.getItCatory().add(itCatoryVo);
    }

    for (AttachmentsDto attachmentsDto : articleDto.getAttachments()) {
      AttachmentsVo attachmentsVo = new AttachmentsVo();
      attachmentsVo.setId(attachmentsDto.getId());
      attachmentsVo.setName(attachmentsDto.getName());
      attachmentsVo.setSize(attachmentsDto.getSize());
      attachmentsVo.setUrl(attachmentsDto.getUrl());
      attachmentsVo.setUid(attachmentsDto.getUid());
      attachmentsVo.setType(attachmentsDto.getType());
      attachmentsVo.setStatus(attachmentsDto.getStatus());
      articleVo.getAttachments().add(attachmentsVo);
    }
    return articleVo;
  }
}
