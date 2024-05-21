package com.jinzita.resource.service;

import com.jinzita.resource.domain.HomeCount;
import com.jinzita.resource.domain.VO.LineChartData;

public interface IHomeDataService {

    public HomeCount HomeCount();

    public LineChartData GetlineChartData();
}
