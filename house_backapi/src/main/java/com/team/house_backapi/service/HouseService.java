package com.team.house_backapi.service;

import com.github.pagehelper.PageInfo;
import com.team.house_backapi.entity.House;
import com.team.house_backapi.util.HouseCondition;
import com.team.house_backapi.util.PageParmeter;

public interface HouseService {

    /**
     * 发布出租房
     * @param house  实体
     * @return 影响行数
     */
    public int addHouse(House house);

    /**
     * 查询某用户下的出租房
     * @param userid  用户编号
     * @param pageParameter  分页的参数，页码，页大小
     * @return
     */
    PageInfo<House> getHouseByUser(Integer userid, PageParmeter pageParameter);

    /**
     * 删除出租房|恢复出租房
     * @param id
     * @param delState 1 表示删除   0表示恢复
     * @return
     */
    public int delete(String id,Integer delState);

    /**
     * 条件查询租房信息
     * @param  houseCondition
     * @return
     */
    PageInfo<House> getHouseDataByHouseCondition(HouseCondition houseCondition);
}
