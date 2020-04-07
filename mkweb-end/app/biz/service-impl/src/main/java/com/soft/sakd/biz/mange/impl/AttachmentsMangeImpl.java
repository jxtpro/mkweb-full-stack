package com.soft.sakd.biz.mange.impl;

import com.soft.sakd.biz.convert.impl.AttachmentsBizConvert;
import com.soft.sakd.biz.mange.AttachmentsMange;
import com.soft.sakd.biz.param.AttachmentsParam;
import com.soft.sakd.biz.vo.AttachmentsVo;
import com.soft.sakd.common.dto.AttachmentsDto;
import com.soft.sakd.common.facade.AttachmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Administrator
 * @since 2020/4/6 9:36
 */
@Service
public class AttachmentsMangeImpl implements AttachmentsMange {

  @Autowired private AttachmentsService attachmentsService;

  @Override
  public AttachmentsVo insertAttachments(AttachmentsParam param) {
    Assert.notNull(param, "参数不为空");
    Assert.notNull(param.getName(), "Name参数不为空");
    AttachmentsBizConvert attachmentsBizConvert = new AttachmentsBizConvert();
    AttachmentsDto dto =
        attachmentsService.insertAttachments(attachmentsBizConvert.paramToDto(param));
    return attachmentsBizConvert.dtoToVo(dto);
  }
}
