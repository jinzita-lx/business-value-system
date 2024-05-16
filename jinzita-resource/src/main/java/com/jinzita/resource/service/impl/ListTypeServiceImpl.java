package com.jinzita.resource.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jinzita.resource.mapper.ListTypeMapper;
import com.jinzita.resource.domain.ListType;
import com.jinzita.resource.service.IListTypeService;

/**
 * 榜单类型Service业务层处理
 * 
 * @author jinzita
 * @date 2024-05-16
 */
@Service
public class ListTypeServiceImpl implements IListTypeService 
{
    @Autowired
    private ListTypeMapper listTypeMapper;

    /**
     * 查询榜单类型
     * 
     * @param id 榜单类型主键
     * @return 榜单类型
     */
    @Override
    public ListType selectListTypeById(Long id)
    {
        return listTypeMapper.selectListTypeById(id);
    }

    /**
     * 查询榜单类型列表
     * 
     * @param listType 榜单类型
     * @return 榜单类型
     */
    @Override
    public List<ListType> selectListTypeList(ListType listType)
    {
        return listTypeMapper.selectListTypeList(listType);
    }

    /**
     * 新增榜单类型
     * 
     * @param listType 榜单类型
     * @return 结果
     */
    @Override
    public int insertListType(ListType listType)
    {
        return listTypeMapper.insertListType(listType);
    }

    /**
     * 修改榜单类型
     * 
     * @param listType 榜单类型
     * @return 结果
     */
    @Override
    public int updateListType(ListType listType)
    {
        return listTypeMapper.updateListType(listType);
    }

    /**
     * 批量删除榜单类型
     * 
     * @param ids 需要删除的榜单类型主键
     * @return 结果
     */
    @Override
    public int deleteListTypeByIds(Long[] ids)
    {
        return listTypeMapper.deleteListTypeByIds(ids);
    }

    /**
     * 删除榜单类型信息
     * 
     * @param id 榜单类型主键
     * @return 结果
     */
    @Override
    public int deleteListTypeById(Long id)
    {
        return listTypeMapper.deleteListTypeById(id);
    }
}
