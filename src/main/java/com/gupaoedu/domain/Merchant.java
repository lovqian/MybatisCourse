package com.gupaoedu.domain;

import java.util.List;

public class Merchant {
    private Integer merchantId;//商户号

    private String merchantName; //商户名称

    private List<Integer> scope; //经营范围

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public List<Integer> getScope() {
        return scope;
    }

    public void setScope(List<Integer> scope) {
        this.scope = scope == null ? null : scope;
    }
}