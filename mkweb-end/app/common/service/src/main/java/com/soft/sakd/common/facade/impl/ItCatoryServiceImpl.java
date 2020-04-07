package com.soft.sakd.common.facade.impl;

import com.soft.sakd.common.convert.impl.ItCatorySrvConvert;
import com.soft.sakd.common.dto.ItCatoryDto;
import com.soft.sakd.common.facade.ItCatoryService;
import com.soft.sakd.core.model.mapper.ItCatoryMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujie
 * @since 2020/4/2 10:27
 */
@Service
public class ItCatoryServiceImpl implements ItCatoryService {

  @Autowired private ItCatoryMapper itCatoryMapper;

  @Override
  public List<ItCatoryDto> queryList() {
    ItCatorySrvConvert catoryConvert = new ItCatorySrvConvert();
    return catoryConvert.toListDto(itCatoryMapper.selectAll());
  }
}
