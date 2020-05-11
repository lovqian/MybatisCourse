package com.gupaoedu.domain.associate;

import java.math.BigDecimal;
import java.util.Date;

public class FeeDate {
    private Integer id;

    private BigDecimal feeAmt;

    private Date feeDate;

    public FeeDate() {
    }

    public FeeDate(Integer id, Date feeDate) {
        this.id = id;
        this.feeDate = feeDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public Date getFeeDate() {
        return feeDate;
    }

    public void setFeeDate(Date feeDate) {
        this.feeDate = feeDate;
    }
}