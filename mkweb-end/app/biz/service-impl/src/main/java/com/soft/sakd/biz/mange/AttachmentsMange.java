package com.soft.sakd.biz.mange;

import com.soft.sakd.biz.param.AttachmentsParam;
import com.soft.sakd.biz.vo.AttachmentsVo;

/**
 * @author Administrator
 * @since 2020/4/6 9:35
 */
public interface AttachmentsMange {

  AttachmentsVo insertAttachments(AttachmentsParam attachmentsParam);
}
