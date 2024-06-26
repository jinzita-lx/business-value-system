package com.jinzita.web.controller.resource;

import com.jinzita.common.core.controller.BaseController;
import com.jinzita.common.core.domain.AjaxResult;
import com.jinzita.resource.domain.HomeCount;
import com.jinzita.resource.service.IHomeDataService;
import com.jinzita.resource.service.IListTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 主页Controller
 *
 * @author jinzita
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Autowired
    private IHomeDataService homeDataService;

    @GetMapping("/count")
    public AjaxResult getHomeCount() {
        AjaxResult ajaxResult = AjaxResult.success();
        HomeCount homeCount = homeDataService.HomeCount();
        ajaxResult.put("data", homeCount);
        return ajaxResult;
    }

    @GetMapping("/line_chart")
    public AjaxResult getLineChart() {
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", homeDataService.GetlineChartData());
        return ajaxResult;
    }

    @GetMapping("/pie_chart")
    public AjaxResult getPieChart() {
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", homeDataService.GetPieChartData());
        return ajaxResult;
    }

    @GetMapping("/raddar_chart")
    public AjaxResult getRaddarChart() {
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", homeDataService.GetRaddarChartData());
        return ajaxResult;
    }

    @GetMapping("/bar_chart")
    public AjaxResult getBarChart() {
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", homeDataService.GetBarChartData());
        return ajaxResult;
    }

}
