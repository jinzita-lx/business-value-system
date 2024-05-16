package com.jinzita.resource.mapper;

import java.util.List;
import com.jinzita.resource.domain.BusinessValueIndicators;

/**
 * 价值指标Mapper接口
 * 
 * @author jinzita
 * @date 2024-05-16
 */
public interface BusinessValueIndicatorsMapper 
{
    /**
     * 查询价值指标
     * 
     * @param id 价值指标主键
     * @return 价值指标
     */
    public BusinessValueIndicators selectBusinessValueIndicatorsById(Long id);

    /**
     * 查询价值指标列表
     * 
     * @param businessValueIndicators 价值指标
     * @return 价值指标集合
     */
    public List<BusinessValueIndicators> selectBusinessValueIndicatorsList(BusinessValueIndicators businessValueIndicators);

    /**
     * 新增价值指标
     * 
     * @param businessValueIndicators 价值指标
     * @return 结果
     */
    public int insertBusinessValueIndicators(BusinessValueIndicators businessValueIndicators);

    /**
     * 修改价值指标
     * 
     * @param businessValueIndicators 价值指标
     * @return 结果
     */
    public int updateBusinessValueIndicators(BusinessValueIndicators businessValueIndicators);

    /**
     * 删除价值指标
     * 
     * @param id 价值指标主键
     * @return 结果
     */
    public int deleteBusinessValueIndicatorsById(Long id);

    /**
     * 批量删除价值指标
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessValueIndicatorsByIds(Long[] ids);
}
