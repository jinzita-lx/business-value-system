package com.jinzita.resource.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jinzita.common.annotation.Excel;
import com.jinzita.common.core.domain.BaseEntity;

/**
 * 主页信息对象 home_info
 * 
 * @author jinzita
 * @date 2024-05-16
 */
public class HomeInfo extends BaseEntity
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

    /** 个人简介 */
    @Excel(name = "个人简介")
    private String introduction;

    /** 主页链接 */
    @Excel(name = "主页链接")
    private String homeLink;

    /** 周期类型 */
    @Excel(name = "周期类型")
    private String periodType;

    /** 榜单类型 */
    @Excel(name = "榜单类型")
    private String listType;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("author", getAuthor())
            .append("gender", getGender())
            .append("introduction", getIntroduction())
            .append("homeLink", getHomeLink())
            .append("periodType", getPeriodType())
            .append("listType", getListType())
            .toString();
    }
}
