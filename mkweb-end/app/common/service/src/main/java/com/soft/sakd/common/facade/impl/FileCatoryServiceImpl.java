package com.soft.sakd.common.facade.impl;

import com.soft.sakd.common.convert.impl.FileCatorySrvConvert;
import com.soft.sakd.common.dto.FileCatoryDto;
import com.soft.sakd.common.facade.FileCatoryService;
import com.soft.sakd.core.model.mapper.FileCatoryMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujie
 * @since 2020/4/2 10:27
 */
@Service
public class FileCatoryServiceImpl implements FileCatoryService {

  @Autowired private FileCatoryMapper fileCatoryMapper;

  @Override
  public List<FileCatoryDto> queryList() {
    FileCatorySrvConvert catoryConvert = new FileCatorySrvConvert();
    return catoryConvert.toListDto(fileCatoryMapper.selectAll());
  }
}
