package com.gupaoedu.mapper;


import com.gupaoedu.domain.Merchant;

public interface MerchantMapper {
    int deleteByPrimaryKey(Integer merchantId);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(Integer merchantId);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);
}