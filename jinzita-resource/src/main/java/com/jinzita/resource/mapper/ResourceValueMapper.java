package com.jinzita.resource.mapper;

import java.util.List;
import com.jinzita.resource.domain.ResourceValue;

/**
 * 价值榜单Mapper接口
 * 
 * @author jinzita
 * @date 2024-04-14
 */
public interface ResourceValueMapper 
{
    /**
     * 查询价值榜单
     * 
     * @param id 价值榜单主键
     * @return 价值榜单
     */
    public ResourceValue selectResourceValueById(Long id);

    /**
     * 查询价值榜单列表
     * 
     * @param resourceValue 价值榜单
     * @return 价值榜单集合
     */
    public List<ResourceValue> selectResourceValueList(ResourceValue resourceValue);

    /**
     * 新增价值榜单
     * 
     * @param resourceValue 价值榜单
     * @return 结果
     */
    public int insertResourceValue(ResourceValue resourceValue);

    /**
     * 修改价值榜单
     * 
     * @param resourceValue 价值榜单
     * @return 结果
     */
    public int updateResourceValue(ResourceValue resourceValue);

    /**
     * 删除价值榜单
     * 
     * @param id 价值榜单主键
     * @return 结果
     */
    public int deleteResourceValueById(Long id);

    /**
     * 批量删除价值榜单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResourceValueByIds(Long[] ids);
}
