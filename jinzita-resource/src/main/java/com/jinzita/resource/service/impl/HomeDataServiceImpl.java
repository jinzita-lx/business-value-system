package com.jinzita.resource.service.impl;

import com.jinzita.resource.domain.HomeCount;
import com.jinzita.resource.mapper.HomeDataMapper;
import com.jinzita.resource.service.IHomeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
