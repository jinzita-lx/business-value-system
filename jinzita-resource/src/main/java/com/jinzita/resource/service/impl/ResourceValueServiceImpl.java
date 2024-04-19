package com.jinzita.resource.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jinzita.resource.mapper.ResourceValueMapper;
import com.jinzita.resource.domain.ResourceValue;
import com.jinzita.resource.service.IResourceValueService;

/**
 * 价值榜单Service业务层处理
 * 
 * @author jinzita
 * @date 2024-04-14
 */
@Service
public class ResourceValueServiceImpl implements IResourceValueService 
{
    @Autowired
    private ResourceValueMapper resourceValueMapper;

    /**
     * 查询价值榜单
     * 
     * @param id 价值榜单主键
     * @return 价值榜单
     */
    @Override
    public ResourceValue selectResourceValueById(Long id)
    {
        return resourceValueMapper.selectResourceValueById(id);
    }

    /**
     * 查询价值榜单列表
     * 
     * @param resourceValue 价值榜单
     * @return 价值榜单
     */
    @Override
    public List<ResourceValue> selectResourceValueList(ResourceValue resourceValue)
    {
        return resourceValueMapper.selectResourceValueList(resourceValue);
    }

    /**
     * 新增价值榜单
     * 
     * @param resourceValue 价值榜单
     * @return 结果
     */
    @Override
    public int insertResourceValue(ResourceValue resourceValue)
    {
        return resourceValueMapper.insertResourceValue(resourceValue);
    }

    /**
     * 修改价值榜单
     * 
     * @param resourceValue 价值榜单
     * @return 结果
     */
    @Override
    public int updateResourceValue(ResourceValue resourceValue)
    {
        return resourceValueMapper.updateResourceValue(resourceValue);
    }

    /**
     * 批量删除价值榜单
     * 
     * @param ids 需要删除的价值榜单主键
     * @return 结果
     */
    @Override
    public int deleteResourceValueByIds(Long[] ids)
    {
        return resourceValueMapper.deleteResourceValueByIds(ids);
    }

    /**
     * 删除价值榜单信息
     * 
     * @param id 价值榜单主键
     * @return 结果
     */
    @Override
    public int deleteResourceValueById(Long id)
    {
        return resourceValueMapper.deleteResourceValueById(id);
    }
}
