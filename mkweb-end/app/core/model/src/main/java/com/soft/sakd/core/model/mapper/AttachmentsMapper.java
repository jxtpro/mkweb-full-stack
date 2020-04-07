package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.Attachments;
import java.util.List;

public interface AttachmentsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attachments record);

    Attachments selectByPrimaryKey(Long id);

    List<Attachments> selectAll();

    int updateByPrimaryKey(Attachments record);
}