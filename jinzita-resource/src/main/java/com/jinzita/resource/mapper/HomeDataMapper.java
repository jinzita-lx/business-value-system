package com.jinzita.resource.mapper;

import com.jinzita.resource.domain.BusinessValueIndicators;
import com.jinzita.resource.domain.ListType;

import java.util.List;

public interface HomeDataMapper {

    public Long selectAccountNumber();

    public Long selectListType();

    public Long selectUserNumber();

    public Long selectIndicatorType();

    public List<ListType> selectAllListTypeList();

    public List<BusinessValueIndicators> selectAllBusinessValueIndicators();
}
