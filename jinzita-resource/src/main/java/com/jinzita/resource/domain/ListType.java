package com.jinzita.resource.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jinzita.common.annotation.Excel;
import com.jinzita.common.core.domain.BaseEntity;

/**
 * 榜单类型对象 list_type
 * 
 * @author jinzita
 * @date 2024-05-16
 */
public class ListType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 榜单英文名 */
    @Excel(name = "榜单英文名")
    private String typeNameEn;

    /** 综合营销价值 */
    @Excel(name = "综合营销价值")
    private Long compositeMarketValue;

    /** 商业适应度指数 */
    @Excel(name = "商业适应度指数")
    private Long businessAdaptationExponent;

    /** 传播指数 */
    @Excel(name = "传播指数")
    private Long spreadExponent;

    /** 活跃度指数 */
    @Excel(name = "活跃度指数")
    private Long activityExponent;

    /** 成长指数 */
    @Excel(name = "成长指数")
    private Long growthExponent;

    /** 健康指数 */
    @Excel(name = "健康指数")
    private Long healthExponent;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setTypeNameEn(String typeNameEn) 
    {
        this.typeNameEn = typeNameEn;
    }

    public String getTypeNameEn() 
    {
        return typeNameEn;
    }
    public void setCompositeMarketValue(Long compositeMarketValue) 
    {
        this.compositeMarketValue = compositeMarketValue;
    }

    public Long getCompositeMarketValue() 
    {
        return compositeMarketValue;
    }
    public void setBusinessAdaptationExponent(Long businessAdaptationExponent) 
    {
        this.businessAdaptationExponent = businessAdaptationExponent;
    }

    public Long getBusinessAdaptationExponent() 
    {
        return businessAdaptationExponent;
    }
    public void setSpreadExponent(Long spreadExponent) 
    {
        this.spreadExponent = spreadExponent;
    }

    public Long getSpreadExponent() 
    {
        return spreadExponent;
    }
    public void setActivityExponent(Long activityExponent) 
    {
        this.activityExponent = activityExponent;
    }

    public Long getActivityExponent() 
    {
        return activityExponent;
    }
    public void setGrowthExponent(Long growthExponent) 
    {
        this.growthExponent = growthExponent;
    }

    public Long getGrowthExponent() 
    {
        return growthExponent;
    }
    public void setHealthExponent(Long healthExponent) 
    {
        this.healthExponent = healthExponent;
    }

    public Long getHealthExponent() 
    {
        return healthExponent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeName", getTypeName())
            .append("typeNameEn", getTypeNameEn())
            .append("compositeMarketValue", getCompositeMarketValue())
            .append("businessAdaptationExponent", getBusinessAdaptationExponent())
            .append("spreadExponent", getSpreadExponent())
            .append("activityExponent", getActivityExponent())
            .append("growthExponent", getGrowthExponent())
            .append("healthExponent", getHealthExponent())
            .toString();
    }
}
