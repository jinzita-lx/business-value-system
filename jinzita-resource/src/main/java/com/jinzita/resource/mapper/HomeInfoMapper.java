package com.jinzita.resource.mapper;

import java.util.List;
import com.jinzita.resource.domain.HomeInfo;

/**
 * 主页信息Mapper接口
 * 
 * @author jinzita
 * @date 2024-05-16
 */
public interface HomeInfoMapper 
{
    /**
     * 查询主页信息
     * 
     * @param id 主页信息主键
     * @return 主页信息
     */
    public HomeInfo selectHomeInfoById(Long id);

    /**
     * 查询主页信息列表
     * 
     * @param homeInfo 主页信息
     * @return 主页信息集合
     */
    public List<HomeInfo> selectHomeInfoList(HomeInfo homeInfo);

    /**
     * 新增主页信息
     * 
     * @param homeInfo 主页信息
     * @return 结果
     */
    public int insertHomeInfo(HomeInfo homeInfo);

    /**
     * 修改主页信息
     * 
     * @param homeInfo 主页信息
     * @return 结果
     */
    public int updateHomeInfo(HomeInfo homeInfo);

    /**
     * 删除主页信息
     * 
     * @param id 主页信息主键
     * @return 结果
     */
    public int deleteHomeInfoById(Long id);

    /**
     * 批量删除主页信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomeInfoByIds(Long[] ids);

    /**
     * 获取主页信息
     * @return
     */
    public List<HomeInfo> selectHomeInfoFromValueList();

    /**
     * 更新主页信息
     * @param homeInfoList
     * @return
     */
    public int insertHomeInfoList(List<HomeInfo> homeInfoList);
}
