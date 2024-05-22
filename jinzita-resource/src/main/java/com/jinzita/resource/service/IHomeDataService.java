package com.jinzita.resource.service;

import com.jinzita.resource.domain.HomeCount;
import com.jinzita.resource.domain.ListType;
import com.jinzita.resource.domain.VO.LineChartData;

import java.util.List;

public interface IHomeDataService {

    public HomeCount HomeCount();

    public LineChartData GetlineChartData();

    public List<ListType> GetPieChartData();

    public LineChartData GetRaddarChartData();

    public LineChartData GetBarChartData();
}
