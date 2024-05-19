package com.jinzita.resource.service.impl;

import java.util.List;

import com.jinzita.resource.domain.ResourceValue;
import com.jinzita.resource.mapper.ResourceValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jinzita.resource.mapper.HomeInfoMapper;
import com.jinzita.resource.domain.HomeInfo;
import com.jinzita.resource.service.IHomeInfoService;

/**
 * 主页信息Service业务层处理
 * 
 * @author jinzita
 * @date 2024-05-16
 */
@Service
public class HomeInfoServiceImpl implements IHomeInfoService 
{
    @Autowired
    private HomeInfoMapper homeInfoMapper;

    /**
     * 查询主页信息
     * 
     * @param id 主页信息主键
     * @return 主页信息
     */
    @Override
    public HomeInfo selectHomeInfoById(Long id)
    {
        return homeInfoMapper.selectHomeInfoById(id);
    }

    /**
     * 查询主页信息列表
     * 
     * @param homeInfo 主页信息
     * @return 主页信息
     */
    @Override
    public List<HomeInfo> selectHomeInfoList(HomeInfo homeInfo)
    {
        return homeInfoMapper.selectHomeInfoList(homeInfo);
    }

    /**
     * 新增主页信息
     * 
     * @param homeInfo 主页信息
     * @return 结果
     */
    @Override
    public int insertHomeInfo(HomeInfo homeInfo)
    {
        return homeInfoMapper.insertHomeInfo(homeInfo);
    }

    /**
     * 修改主页信息
     * 
     * @param homeInfo 主页信息
     * @return 结果
     */
    @Override
    public int updateHomeInfo(HomeInfo homeInfo)
    {
        return homeInfoMapper.updateHomeInfo(homeInfo);
    }

    /**
     * 批量删除主页信息
     * 
     * @param ids 需要删除的主页信息主键
     * @return 结果
     */
    @Override
    public int deleteHomeInfoByIds(Long[] ids)
    {
        return homeInfoMapper.deleteHomeInfoByIds(ids);
    }

    /**
     * 删除主页信息信息
     * 
     * @param id 主页信息主键
     * @return 结果
     */
    @Override
    public int deleteHomeInfoById(Long id)
    {
        return homeInfoMapper.deleteHomeInfoById(id);
    }

    /**
     * 更新主页信息数据
     * @return
     */
    @Override
    public int reloadHomeInfoList() {
        List<HomeInfo> homeInfoList = homeInfoMapper.selectHomeInfoFromValueList();
        try {
            int res = homeInfoMapper.insertHomeInfoList(homeInfoList);
            System.out.println(res);
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
