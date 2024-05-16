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
import com.jinzita.resource.domain.ListType;
import com.jinzita.resource.service.IListTypeService;
import com.jinzita.common.utils.poi.ExcelUtil;
import com.jinzita.common.core.page.TableDataInfo;

/**
 * 榜单类型Controller
 * 
 * @author jinzita
 * @date 2024-05-16
 */
@RestController
@RequestMapping("/resource/list_type")
public class ListTypeController extends BaseController
{
    @Autowired
    private IListTypeService listTypeService;

    /**
     * 查询榜单类型列表
     */
    @PreAuthorize("@ss.hasPermi('resource:list_type:list')")
    @GetMapping("/list")
    public TableDataInfo list(ListType listType)
    {
        startPage();
        List<ListType> list = listTypeService.selectListTypeList(listType);
        return getDataTable(list);
    }

    /**
     * 导出榜单类型列表
     */
    @PreAuthorize("@ss.hasPermi('resource:list_type:export')")
    @Log(title = "榜单类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ListType listType)
    {
        List<ListType> list = listTypeService.selectListTypeList(listType);
        ExcelUtil<ListType> util = new ExcelUtil<ListType>(ListType.class);
        util.exportExcel(response, list, "榜单类型数据");
    }

    /**
     * 获取榜单类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('resource:list_type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(listTypeService.selectListTypeById(id));
    }

    /**
     * 新增榜单类型
     */
    @PreAuthorize("@ss.hasPermi('resource:list_type:add')")
    @Log(title = "榜单类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ListType listType)
    {
        return toAjax(listTypeService.insertListType(listType));
    }

    /**
     * 修改榜单类型
     */
    @PreAuthorize("@ss.hasPermi('resource:list_type:edit')")
    @Log(title = "榜单类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ListType listType)
    {
        return toAjax(listTypeService.updateListType(listType));
    }

    /**
     * 删除榜单类型
     */
    @PreAuthorize("@ss.hasPermi('resource:list_type:remove')")
    @Log(title = "榜单类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(listTypeService.deleteListTypeByIds(ids));
    }
}
