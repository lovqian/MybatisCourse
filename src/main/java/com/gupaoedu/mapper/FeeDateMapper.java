package com.gupaoedu.mapper;


import com.gupaoedu.domain.associate.FeeDate;

import java.util.Date;
import java.util.List;

public interface FeeDateMapper {
    //int deleteByPrimaryKey(Integer id);

    //int insert(FeeDate record);

    //int insertSelective(FeeDate record);

    FeeDate selectByPrimaryKey(Integer id);

    List<FeeDate> selectByDate(Date date);

    List<FeeDate> selectByDateOrId(FeeDate feeDate);

   // int updateByPrimaryKeySelective(FeeDate record);

   // int updateByPrimaryKey(FeeDate record);
}