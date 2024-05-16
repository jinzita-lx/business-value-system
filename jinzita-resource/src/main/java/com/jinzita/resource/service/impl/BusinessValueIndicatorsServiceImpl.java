package com.jinzita.resource.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jinzita.resource.mapper.BusinessValueIndicatorsMapper;
import com.jinzita.resource.domain.BusinessValueIndicators;
import com.jinzita.resource.service.IBusinessValueIndicatorsService;

/**
 * 价值指标Service业务层处理
 * 
 * @author jinzita
 * @date 2024-05-16
 */
@Service
public class BusinessValueIndicatorsServiceImpl implements IBusinessValueIndicatorsService 
{
    @Autowired
    private BusinessValueIndicatorsMapper businessValueIndicatorsMapper;

    /**
     * 查询价值指标
     * 
     * @param id 价值指标主键
     * @return 价值指标
     */
    @Override
    public BusinessValueIndicators selectBusinessValueIndicatorsById(Long id)
    {
        return businessValueIndicatorsMapper.selectBusinessValueIndicatorsById(id);
    }

    /**
     * 查询价值指标列表
     * 
     * @param businessValueIndicators 价值指标
     * @return 价值指标
     */
    @Override
    public List<BusinessValueIndicators> selectBusinessValueIndicatorsList(BusinessValueIndicators businessValueIndicators)
    {
        return businessValueIndicatorsMapper.selectBusinessValueIndicatorsList(businessValueIndicators);
    }

    /**
     * 新增价值指标
     * 
     * @param businessValueIndicators 价值指标
     * @return 结果
     */
    @Override
    public int insertBusinessValueIndicators(BusinessValueIndicators businessValueIndicators)
    {
        return businessValueIndicatorsMapper.insertBusinessValueIndicators(businessValueIndicators);
    }

    /**
     * 修改价值指标
     * 
     * @param businessValueIndicators 价值指标
     * @return 结果
     */
    @Override
    public int updateBusinessValueIndicators(BusinessValueIndicators businessValueIndicators)
    {
        return businessValueIndicatorsMapper.updateBusinessValueIndicators(businessValueIndicators);
    }

    /**
     * 批量删除价值指标
     * 
     * @param ids 需要删除的价值指标主键
     * @return 结果
     */
    @Override
    public int deleteBusinessValueIndicatorsByIds(Long[] ids)
    {
        return businessValueIndicatorsMapper.deleteBusinessValueIndicatorsByIds(ids);
    }

    /**
     * 删除价值指标信息
     * 
     * @param id 价值指标主键
     * @return 结果
     */
    @Override
    public int deleteBusinessValueIndicatorsById(Long id)
    {
        return businessValueIndicatorsMapper.deleteBusinessValueIndicatorsById(id);
    }
}
