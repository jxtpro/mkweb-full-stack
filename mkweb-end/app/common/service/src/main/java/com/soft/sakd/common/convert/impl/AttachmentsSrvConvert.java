package com.soft.sakd.common.convert.impl;

import com.google.common.collect.Lists;
import com.soft.sakd.common.convert.SrvConvert;
import com.soft.sakd.common.dto.AttachmentsDto;
import com.soft.sakd.core.model.entity.Attachments;
import java.util.List;

/**
 * @author Administrator
 * @since 2020/4/5 22:54
 */
public class AttachmentsSrvConvert implements SrvConvert<AttachmentsDto, Attachments> {

  @Override
  public Attachments toDao(AttachmentsDto attachmentsDto) {
    Attachments attachments = new Attachments();
    attachments.setId(attachmentsDto.getId());
    attachments.setUid(attachmentsDto.getUid());
    attachments.setSize(attachmentsDto.getSize());
    attachments.setStatus(attachmentsDto.getStatus());
    attachments.setUrl(attachmentsDto.getUrl());
    attachments.setName(attachmentsDto.getName());
    attachments.setType(attachmentsDto.getType());
    return attachments;
  }

  @Override
  public AttachmentsDto toDto(Attachments attachments) {
    AttachmentsDto attachmentsDto = new AttachmentsDto();
    attachmentsDto.setId(attachments.getId());
    attachmentsDto.setUid(attachments.getUid());
    attachmentsDto.setSize(attachments.getSize());
    attachmentsDto.setStatus(attachments.getStatus());
    attachmentsDto.setUrl(attachments.getUrl());
    attachmentsDto.setName(attachments.getName());
    attachmentsDto.setType(attachments.getType());
    return attachmentsDto;
  }

  public List<AttachmentsDto> toListDto(List<Attachments> attachmentsList) {
    List<AttachmentsDto> attachmentsDtoList = Lists.newArrayList();
    attachmentsList.forEach(attachments -> {
      attachmentsDtoList.add(toDto(attachments));
    });
    return attachmentsDtoList;
  }

  public List<Attachments> toListDao(List<AttachmentsDto> attachmentsDtoList) {
    List<Attachments> attachmentsList = Lists.newArrayList();
    attachmentsDtoList.forEach(attachmentsDto -> {
      attachmentsList.add(toDao(attachmentsDto));
    });
    return attachmentsList;
  }
}
