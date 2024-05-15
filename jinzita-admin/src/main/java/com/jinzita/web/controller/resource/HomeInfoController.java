package com.jinzita.web.controller.resource;

import com.jinzita.common.annotation.Log;
import com.jinzita.common.core.controller.BaseController;
import com.jinzita.common.core.domain.AjaxResult;
import com.jinzita.common.core.page.TableDataInfo;
import com.jinzita.common.enums.BusinessType;
import com.jinzita.common.utils.poi.ExcelUtil;
import com.jinzita.resource.domain.HomeInfo;
import com.jinzita.resource.service.IHomeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 主页信息Controller
 * 
 * @author jinzita
 * @date 2024-05-16
 */
@RestController
@RequestMapping("/resource/home")
public class HomeInfoController extends BaseController
{
    @Autowired
    private IHomeInfoService homeInfoService;

    /**
     * 查询主页信息列表
     */
    @PreAuthorize("@ss.hasPermi('resource:home:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomeInfo homeInfo)
    {
        startPage();
        List<HomeInfo> list = homeInfoService.selectHomeInfoList(homeInfo);
        return getDataTable(list);
    }

    /**
     * 导出主页信息列表
     */
    @PreAuthorize("@ss.hasPermi('resource:home:export')")
    @Log(title = "主页信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeInfo homeInfo)
    {
        List<HomeInfo> list = homeInfoService.selectHomeInfoList(homeInfo);
        ExcelUtil<HomeInfo> util = new ExcelUtil<HomeInfo>(HomeInfo.class);
        util.exportExcel(response, list, "主页信息数据");
    }

    /**
     * 获取主页信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('resource:home:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(homeInfoService.selectHomeInfoById(id));
    }

    /**
     * 新增主页信息
     */
    @PreAuthorize("@ss.hasPermi('resource:home:add')")
    @Log(title = "主页信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeInfo homeInfo)
    {
        return toAjax(homeInfoService.insertHomeInfo(homeInfo));
    }

    /**
     * 修改主页信息
     */
    @PreAuthorize("@ss.hasPermi('resource:home:edit')")
    @Log(title = "主页信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeInfo homeInfo)
    {
        return toAjax(homeInfoService.updateHomeInfo(homeInfo));
    }

    /**
     * 删除主页信息
     */
    @PreAuthorize("@ss.hasPermi('resource:home:remove')")
    @Log(title = "主页信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homeInfoService.deleteHomeInfoByIds(ids));
    }
}
