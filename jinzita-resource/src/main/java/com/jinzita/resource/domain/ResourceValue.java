package com.jinzita.resource.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jinzita.common.annotation.Excel;
import com.jinzita.common.core.domain.BaseEntity;

/**
 * 价值榜单对象 short_video_business_value_list
 * 
 * @author jinzita
 * @date 2024-04-14
 */
public class ResourceValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 简介 */
    @Excel(name = "简介")
    private String introduction;

    /** 主页链接 */
    @Excel(name = "主页链接")
    private String homeLink;

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

    /** 周期类型 */
    @Excel(name = "周期类型")
    private String periodType;

    /** 榜单类型 */
    @Excel(name = "榜单类型")
    private String listType;

    /** 起始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "起始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setHomeLink(String homeLink) 
    {
        this.homeLink = homeLink;
    }

    public String getHomeLink() 
    {
        return homeLink;
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
    public void setPeriodType(String periodType) 
    {
        this.periodType = periodType;
    }

    public String getPeriodType() 
    {
        return periodType;
    }
    public void setListType(String listType) 
    {
        this.listType = listType;
    }

    public String getListType() 
    {
        return listType;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("author", getAuthor())
            .append("gender", getGender())
            .append("introduction", getIntroduction())
            .append("homeLink", getHomeLink())
            .append("compositeMarketValue", getCompositeMarketValue())
            .append("businessAdaptationExponent", getBusinessAdaptationExponent())
            .append("spreadExponent", getSpreadExponent())
            .append("activityExponent", getActivityExponent())
            .append("growthExponent", getGrowthExponent())
            .append("healthExponent", getHealthExponent())
            .append("periodType", getPeriodType())
            .append("listType", getListType())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .toString();
    }
}
