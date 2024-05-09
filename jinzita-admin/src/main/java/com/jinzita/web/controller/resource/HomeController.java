package com.jinzita.web.controller.resource;

import com.jinzita.common.core.controller.BaseController;
import com.jinzita.resource.domain.HomeCount;
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


    @GetMapping("/count")
    public HomeCount getHomeCount() {
        HomeCount homeCount = new HomeCount();
        homeCount.setUserNumber(1000L);
        homeCount.setAccountNumber(1000L);
        homeCount.setIndicatorType(1000L);
        homeCount.setListType(1000L);
        return homeCount;
    }

}
