package com.jinzita.resource.service.impl;

import com.jinzita.resource.domain.BusinessValueIndicators;
import com.jinzita.resource.domain.HomeCount;
import com.jinzita.resource.domain.ListType;
import com.jinzita.resource.domain.VO.LineChartData;
import com.jinzita.resource.mapper.HomeDataMapper;
import com.jinzita.resource.service.IHomeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeDataServiceImpl implements IHomeDataService {

    @Autowired
    private HomeDataMapper homeDataMapper;

    @Override
    public HomeCount HomeCount() {
        HomeCount homeCount = new HomeCount();
        homeCount.setAccountNumber(homeDataMapper.selectAccountNumber());
        homeCount.setIndicatorType(homeDataMapper.selectIndicatorType());
        homeCount.setListType(homeDataMapper.selectListType());
        homeCount.setUserNumber(homeDataMapper.selectUserNumber());
        ;
        return homeCount;
    }

    @Override
    public LineChartData GetlineChartData() {
        List<ListType> listTypeList = homeDataMapper.selectAllListTypeList();
        List<BusinessValueIndicators> businessValueIndicatorList = homeDataMapper.selectAllBusinessValueIndicators();
        LineChartData lineChartData = new LineChartData();
        lineChartData.setListTypeList(listTypeList);
        lineChartData.setBusinessValueIndicatorList(businessValueIndicatorList);
        return lineChartData;
    }
}
