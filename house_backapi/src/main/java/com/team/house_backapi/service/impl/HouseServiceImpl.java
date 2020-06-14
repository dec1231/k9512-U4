package com.team.house_backapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house_backapi.entity.House;
import com.team.house_backapi.mapper.HouseMapper;
import com.team.house_backapi.service.HouseService;
import com.team.house_backapi.util.HouseCondition;
import com.team.house_backapi.util.PageParmeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired(required = false)
    private HouseMapper houseMapper;

    @Override
    public int addHouse(House house) {
        house.setId(System.currentTimeMillis()+"");
        return this.houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUser(Integer userid, PageParmeter pageParameter) {
        //开启分页
        PageHelper.startPage(pageParameter.getPage(),pageParameter.getPageSize());
        //调用dao层查询所有
        List<House> houseList= this.houseMapper.getHouseByUserId(userid);
        return new PageInfo<House>(houseList);
    }

    @Override
    public int delete(String id, Integer delState) {
        House house=new House();
        house.setId(id);  //设置编号
        house.setIsdel(delState);  //设置删除状态
        return this.houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseDataByHouseCondition(HouseCondition houseCondition) {
        PageHelper.startPage(houseCondition.getPage(),houseCondition.getPageSize());
        List<House> houseList= this.houseMapper.getHouseDataByCondition(houseCondition);
        return new PageInfo<House>(houseList);
    }
}
