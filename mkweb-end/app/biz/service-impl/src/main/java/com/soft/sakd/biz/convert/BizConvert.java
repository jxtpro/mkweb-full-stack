package com.soft.sakd.biz.convert;

/**
 * @author xujie
 * @since 2020/4/2 18:13
 *     <p>vo,param dto,没有dao层
 */
public interface BizConvert<Vo, Param, Dto> {

  Dto paramToDto(Param param);

  Vo dtoToVo(Dto dto);
}
