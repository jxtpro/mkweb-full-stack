package com.soft.sakd.common.convert;

import java.util.List;

/**
 * @author xujie
 * @since 2020/4/2 18:13
 */
public interface SrvConvert<Dto, Dao> {
  Dao toDao(Dto dto);

  Dto toDto(Dao dao);

  List<Dto> toListDto(List<Dao> attachmentsList);

  List<Dao> toListDao(List<Dto> attachmentsDtoList);
}
