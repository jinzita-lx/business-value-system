package com.jinzita.resource.controller;

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
import com.jinzita.resource.domain.ResourceValue;
import com.jinzita.resource.service.IResourceValueService;
import com.jinzita.common.utils.poi.ExcelUtil;
import com.jinzita.common.core.page.TableDataInfo;

/**
 * 价值榜单Controller
 * 
 * @author jinzita
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/resource/value")
public class ResourceValueController extends BaseController
{
    @Autowired
    private IResourceValueService resourceValueService;

    /**
     * 查询价值榜单列表
     */
    @PreAuthorize("@ss.hasPermi('resource:value:list')")
    @GetMapping("/list")
    public TableDataInfo list(ResourceValue resourceValue)
    {
        startPage();
        List<ResourceValue> list = resourceValueService.selectResourceValueList(resourceValue);
        return getDataTable(list);
    }

    /**
     * 导出价值榜单列表
     */
    @PreAuthorize("@ss.hasPermi('resource:value:export')")
    @Log(title = "价值榜单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ResourceValue resourceValue)
    {
        List<ResourceValue> list = resourceValueService.selectResourceValueList(resourceValue);
        ExcelUtil<ResourceValue> util = new ExcelUtil<ResourceValue>(ResourceValue.class);
        util.exportExcel(response, list, "价值榜单数据");
    }

    /**
     * 获取价值榜单详细信息
     */
    @PreAuthorize("@ss.hasPermi('resource:value:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(resourceValueService.selectResourceValueById(id));
    }

    /**
     * 新增价值榜单
     */
    @PreAuthorize("@ss.hasPermi('resource:value:add')")
    @Log(title = "价值榜单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ResourceValue resourceValue)
    {
        return toAjax(resourceValueService.insertResourceValue(resourceValue));
    }

    /**
     * 修改价值榜单
     */
    @PreAuthorize("@ss.hasPermi('resource:value:edit')")
    @Log(title = "价值榜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ResourceValue resourceValue)
    {
        return toAjax(resourceValueService.updateResourceValue(resourceValue));
    }

    /**
     * 删除价值榜单
     */
    @PreAuthorize("@ss.hasPermi('resource:value:remove')")
    @Log(title = "价值榜单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(resourceValueService.deleteResourceValueByIds(ids));
    }
}
