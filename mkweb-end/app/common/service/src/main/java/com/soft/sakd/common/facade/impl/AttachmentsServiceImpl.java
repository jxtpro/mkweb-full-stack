package com.soft.sakd.common.facade.impl;

import com.soft.sakd.common.convert.impl.AttachmentsSrvConvert;
import com.soft.sakd.common.dto.AttachmentsDto;
import com.soft.sakd.common.facade.AttachmentsService;
import com.soft.sakd.core.model.entity.Attachments;
import com.soft.sakd.core.model.mapper.AttachmentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @since 2020/4/6 9:38
 */
@Service
public class AttachmentsServiceImpl implements AttachmentsService {
  @Autowired private AttachmentsMapper attachmentsMapper;

  @Override
  public AttachmentsDto insertAttachments(AttachmentsDto attachmentsDto) {
    AttachmentsSrvConvert attachmentsSrvConvert = new AttachmentsSrvConvert();
    Attachments attachments = attachmentsSrvConvert.toDao(attachmentsDto);
    attachmentsMapper.insert(attachments);
    return attachmentsSrvConvert.toDto(attachments);
  }
}
