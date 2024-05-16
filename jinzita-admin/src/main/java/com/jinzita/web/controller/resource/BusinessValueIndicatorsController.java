package com.jinzita.web.controller.resource;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jinzita.common.annotation.Log;
import com.jinzita.common.core.controller.BaseController;
import com.jinzita.common.core.domain.AjaxResult;
import com.jinzita.common.enums.BusinessType;
import com.jinzita.resource.domain.BusinessValueIndicators;
import com.jinzita.resource.service.IBusinessValueIndicatorsService;
import com.jinzita.common.utils.poi.ExcelUtil;
import com.jinzita.common.core.page.TableDataInfo;

/**
 * 价值指标Controller
 * 
 * @author jinzita
 * @date 2024-05-16
 */
@RestController
@RequestMapping("/resource/indicators")
public class BusinessValueIndicatorsController extends BaseController
{
    @Autowired
    private IBusinessValueIndicatorsService businessValueIndicatorsService;

    /**
     * 查询价值指标列表
     */
    @PreAuthorize("@ss.hasPermi('resource:indicators:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessValueIndicators businessValueIndicators)
    {
        startPage();
        List<BusinessValueIndicators> list = businessValueIndicatorsService.selectBusinessValueIndicatorsList(businessValueIndicators);
        return getDataTable(list);
    }

    /**
     * 导出价值指标列表
     */
    @PreAuthorize("@ss.hasPermi('resource:indicators:export')")
    @Log(title = "价值指标", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessValueIndicators businessValueIndicators)
    {
        List<BusinessValueIndicators> list = businessValueIndicatorsService.selectBusinessValueIndicatorsList(businessValueIndicators);
        ExcelUtil<BusinessValueIndicators> util = new ExcelUtil<BusinessValueIndicators>(BusinessValueIndicators.class);
        util.exportExcel(response, list, "价值指标数据");
    }

    /**
     * 获取价值指标详细信息
     */
    @PreAuthorize("@ss.hasPermi('resource:indicators:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessValueIndicatorsService.selectBusinessValueIndicatorsById(id));
    }

    /**
     * 新增价值指标
     */
    @PreAuthorize("@ss.hasPermi('resource:indicators:add')")
    @Log(title = "价值指标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessValueIndicators businessValueIndicators)
    {
        return toAjax(businessValueIndicatorsService.insertBusinessValueIndicators(businessValueIndicators));
    }

    /**
     * 修改价值指标
     */
    @PreAuthorize("@ss.hasPermi('resource:indicators:edit')")
    @Log(title = "价值指标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessValueIndicators businessValueIndicators)
    {
        return toAjax(businessValueIndicatorsService.updateBusinessValueIndicators(businessValueIndicators));
    }

    /**
     * 删除价值指标
     */
    @PreAuthorize("@ss.hasPermi('resource:indicators:remove')")
    @Log(title = "价值指标", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessValueIndicatorsService.deleteBusinessValueIndicatorsByIds(ids));
    }
}
