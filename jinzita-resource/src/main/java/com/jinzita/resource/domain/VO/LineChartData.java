package com.jinzita.resource.domain.VO;

import com.jinzita.common.core.domain.BaseEntity;
import com.jinzita.resource.domain.BusinessValueIndicators;
import com.jinzita.resource.domain.ListType;

import java.util.List;

public class LineChartData extends BaseEntity {



    private List<ListType> listTypeList;

    private List<BusinessValueIndicators> businessValueIndicatorList;

    public List<ListType> getListTypeList() {
        return listTypeList;
    }

    public void setListTypeList(List<ListType> listTypeList) {
        this.listTypeList = listTypeList;
    }

    public List<BusinessValueIndicators> getBusinessValueIndicatorList() {
        return businessValueIndicatorList;
    }

    public void setBusinessValueIndicatorList(List<BusinessValueIndicators> businessValueIndicatorList) {
        this.businessValueIndicatorList = businessValueIndicatorList;
    }

    @Override
    public String toString() {
        return "LineChartData{" +
                "listTypeList=" + listTypeList +
                ", businessValueIndicatorList=" + businessValueIndicatorList +
                '}';
    }
}
