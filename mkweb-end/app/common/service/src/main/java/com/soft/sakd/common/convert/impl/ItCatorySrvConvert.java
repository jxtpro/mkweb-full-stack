package com.soft.sakd.common.convert.impl;

import com.google.common.collect.Lists;
import com.soft.sakd.common.convert.SrvConvert;
import com.soft.sakd.common.dto.ItCatoryDto;
import com.soft.sakd.core.model.entity.ItCatory;
import java.util.List;

/**
 * @author test
 * @since 2020/4/2 15:15
 */
public class ItCatorySrvConvert implements SrvConvert<ItCatoryDto, ItCatory> {

  @Override
  public ItCatory toDao(ItCatoryDto itCatoryDto) {
    ItCatory itCatory = new ItCatory();
    itCatory.setId(itCatoryDto.getId());
    itCatory.setName(itCatoryDto.getName());
    return itCatory;
  }

  @Override
  public ItCatoryDto toDto(ItCatory itCatory) {
    ItCatoryDto itCatoryDto = new ItCatoryDto();
    itCatoryDto.setId(itCatory.getId());
    itCatoryDto.setName(itCatory.getName());
    return itCatoryDto;
  }

  public List<ItCatoryDto> toListDto(List<ItCatory> itCatoryList) {
    List<ItCatoryDto> itCatoryDtoList = Lists.newArrayList();
    itCatoryList.forEach(itCatory -> {
      itCatoryDtoList.add(toDto(itCatory));
    });
    return itCatoryDtoList;
  }

  public List<ItCatory> toListDao(List<ItCatoryDto> itCatoryDtoList) {
    List<ItCatory> itCatoryList = Lists.newArrayList();
    itCatoryDtoList.forEach(itCatoryDto -> {
      itCatoryList.add(toDao(itCatoryDto));
    });
    return itCatoryList;
  }
}
