package com.jinzita.resource.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jinzita.common.annotation.Excel;
import com.jinzita.common.core.domain.BaseEntity;

/**
 * 价值指标对象 business_value_indicators
 * 
 * @author jinzita
 * @date 2024-05-16
 */
public class BusinessValueIndicators extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 指标名字 */
    @Excel(name = "指标名字")
    private String indicatorName;

    /** 指标字段 */
    @Excel(name = "指标字段")
    private String indicatorKey;

    /** 指标最大值 */
    @Excel(name = "指标最大值")
    private Long indicatorMax;

    /** 指标最小值 */
    @Excel(name = "指标最小值")
    private Long indicatorMin;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    private Long isDelete;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Long isOpen;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIndicatorName(String indicatorName) 
    {
        this.indicatorName = indicatorName;
    }

    public String getIndicatorName() 
    {
        return indicatorName;
    }
    public void setIndicatorKey(String indicatorKey) 
    {
        this.indicatorKey = indicatorKey;
    }

    public String getIndicatorKey() 
    {
        return indicatorKey;
    }
    public void setIndicatorMax(Long indicatorMax) 
    {
        this.indicatorMax = indicatorMax;
    }

    public Long getIndicatorMax() 
    {
        return indicatorMax;
    }
    public void setIndicatorMin(Long indicatorMin) 
    {
        this.indicatorMin = indicatorMin;
    }

    public Long getIndicatorMin() 
    {
        return indicatorMin;
    }
    public void setIsDelete(Long isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Long getIsDelete() 
    {
        return isDelete;
    }
    public void setIsOpen(Long isOpen) 
    {
        this.isOpen = isOpen;
    }

    public Long getIsOpen() 
    {
        return isOpen;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("indicatorName", getIndicatorName())
            .append("indicatorKey", getIndicatorKey())
            .append("indicatorMax", getIndicatorMax())
            .append("indicatorMin", getIndicatorMin())
            .append("isDelete", getIsDelete())
            .append("isOpen", getIsOpen())
            .toString();
    }
}
