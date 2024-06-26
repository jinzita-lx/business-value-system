package com.jinzita.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jinzita.common.core.controller.BaseController;
import com.jinzita.common.core.domain.AjaxResult;
import com.jinzita.common.core.domain.model.RegisterBody;
import com.jinzita.common.utils.StringUtils;
import com.jinzita.framework.web.service.SysRegisterService;
import com.jinzita.system.service.ISysConfigService;

/**
 * 注册验证
 * 
 * @author jinzita
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    @GetMapping("/getRegisterConfig")
    public AjaxResult getRegisterConfig() {
        String registerConfig = configService.selectConfigByKey("sys.account.registerUser");
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", registerConfig);
        return ajax;
    }
}
