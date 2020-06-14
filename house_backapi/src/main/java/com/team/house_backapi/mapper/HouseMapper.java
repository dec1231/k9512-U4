package com.team.house_backapi.mapper;

import com.team.house_backapi.entity.House;
import com.team.house_backapi.entity.HouseExample;
import com.team.house_backapi.util.HouseCondition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //修改House实体，添加类型名称tname、区域名称dname、街道名称sname
    List<House> getHouseByUserId(Integer id);

    //条件查询租房信息
    List<House> getHouseDataByCondition(HouseCondition houseCondition);

}