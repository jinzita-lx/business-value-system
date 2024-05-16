package com.jinzita.resource.mapper;

import java.util.List;
import com.jinzita.resource.domain.ListType;

/**
 * 榜单类型Mapper接口
 * 
 * @author jinzita
 * @date 2024-05-16
 */
public interface ListTypeMapper 
{
    /**
     * 查询榜单类型
     * 
     * @param id 榜单类型主键
     * @return 榜单类型
     */
    public ListType selectListTypeById(Long id);

    /**
     * 查询榜单类型列表
     * 
     * @param listType 榜单类型
     * @return 榜单类型集合
     */
    public List<ListType> selectListTypeList(ListType listType);

    /**
     * 新增榜单类型
     * 
     * @param listType 榜单类型
     * @return 结果
     */
    public int insertListType(ListType listType);

    /**
     * 修改榜单类型
     * 
     * @param listType 榜单类型
     * @return 结果
     */
    public int updateListType(ListType listType);

    /**
     * 删除榜单类型
     * 
     * @param id 榜单类型主键
     * @return 结果
     */
    public int deleteListTypeById(Long id);

    /**
     * 批量删除榜单类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteListTypeByIds(Long[] ids);
}
