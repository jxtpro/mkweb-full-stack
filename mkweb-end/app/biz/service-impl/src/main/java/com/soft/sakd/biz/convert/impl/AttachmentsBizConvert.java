package com.soft.sakd.biz.convert.impl;

import com.google.common.collect.Lists;
import com.soft.sakd.biz.convert.BizConvert;
import com.soft.sakd.biz.param.AttachmentsParam;
import com.soft.sakd.biz.vo.AttachmentsVo;
import com.soft.sakd.common.dto.AttachmentsDto;
import java.util.List;

/**
 * @author Administrator
 * @since 2020/4/5 22:42
 */
public class AttachmentsBizConvert
    implements BizConvert<AttachmentsVo, AttachmentsParam, AttachmentsDto> {

  @Override
  public AttachmentsDto paramToDto(AttachmentsParam attachmentsParam) {
    AttachmentsDto attachmentsDto = new AttachmentsDto();
    attachmentsDto.setId(attachmentsParam.getId());
    attachmentsDto.setUid(attachmentsParam.getUid());
    attachmentsDto.setName(attachmentsParam.getName());
    attachmentsDto.setStatus(attachmentsParam.getStatus());
    attachmentsDto.setType(attachmentsParam.getType());
    attachmentsDto.setUrl(attachmentsParam.getUrl());
    attachmentsDto.setSize(attachmentsParam.getSize());
    return attachmentsDto;
  }

  @Override
  public AttachmentsVo dtoToVo(AttachmentsDto attachmentsDto) {
    AttachmentsVo attachmentsVo = new AttachmentsVo();
    attachmentsVo.setId(attachmentsDto.getId());
    attachmentsVo.setUid(attachmentsDto.getUid());
    attachmentsVo.setName(attachmentsDto.getName());
    attachmentsVo.setStatus(attachmentsDto.getStatus());
    attachmentsVo.setType(attachmentsDto.getType());
    attachmentsVo.setUrl(attachmentsDto.getUrl());
    attachmentsVo.setSize(attachmentsDto.getSize());
    return attachmentsVo;
  }

  public List<AttachmentsVo> dtoToListVo(List<AttachmentsDto> attachmentsDtoList) {
    List<AttachmentsVo> attachmentsVoList = Lists.newArrayList();
    attachmentsDtoList.forEach(
        item -> {
          attachmentsVoList.add(dtoToVo(item));
        });

    return attachmentsVoList;
  }
}
