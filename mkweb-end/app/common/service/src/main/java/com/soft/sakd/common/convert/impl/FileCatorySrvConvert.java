package com.soft.sakd.common.convert.impl;

import com.google.common.collect.Lists;
import com.soft.sakd.common.convert.SrvConvert;
import com.soft.sakd.common.dto.FileCatoryDto;
import com.soft.sakd.core.model.entity.FileCatory;
import java.util.List;

/**
 * @author xujie
 * @since 2020/4/2 15:15
 */
public class FileCatorySrvConvert implements SrvConvert<FileCatoryDto, FileCatory> {

  @Override
  public FileCatory toDao(FileCatoryDto fileCatoryDto) {
    FileCatory fileCatory = new FileCatory();
    fileCatory.setId(fileCatoryDto.getId());
    fileCatory.setName(fileCatoryDto.getName());
    return fileCatory;
  }

  @Override
  public FileCatoryDto toDto(FileCatory fileCatory) {
    FileCatoryDto fileCatoryDto = new FileCatoryDto();
    fileCatoryDto.setId(fileCatory.getId());
    fileCatoryDto.setName(fileCatory.getName());
    return fileCatoryDto;
  }

  public List<FileCatoryDto> toListDto(List<FileCatory> fileCatoryList) {
    List<FileCatoryDto> fileCatoryDtoList = Lists.newArrayList();
    fileCatoryList.forEach(
        item -> {
          fileCatoryDtoList.add(toDto(item));
        });

    return fileCatoryDtoList;
  }

  public List<FileCatory> toListDao(List<FileCatoryDto> fileCatoryDtoList) {
    List<FileCatory> fileCatoryList = Lists.newArrayList();
    fileCatoryDtoList.forEach(
        item -> {
          fileCatoryList.add(toDao(item));
        });
    return fileCatoryList;
  }
}
