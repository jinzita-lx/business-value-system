package com.jinzita.resource.domain;

import com.jinzita.common.core.domain.BaseEntity;

public class HomeCount extends BaseEntity {

    // 账号数量
    private Long accountNumber;
    // 榜单类型
    private Long listType;
    // 指标种类
    private Long indicatorType;
    // 用户数量
    private Long userNumber;

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }

    public void setIndicatorType(Long indicatorType) {
        this.indicatorType = indicatorType;
    }

    public void setListType(Long listType) {
        this.listType = listType;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Long getListType() {
        return listType;
    }

    public Long getIndicatorType() {
        return indicatorType;
    }

    public Long getUserNumber() {
        return userNumber;
    }



    @Override
    public String toString() {
        return "HomeCount{" +
                "accountNumber=" + accountNumber +
                ", listType=" + listType +
                ", indicatorType=" + indicatorType +
                ", userNumber=" + userNumber +
                '}';
    }
}
