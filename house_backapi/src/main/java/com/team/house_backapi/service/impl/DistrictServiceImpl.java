package com.team.house_backapi.service.impl;

import com.team.house_backapi.entity.District;
import com.team.house_backapi.entity.DistrictExample;
import com.team.house_backapi.mapper.DistrictMapper;
import com.team.house_backapi.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired(required = false)
    private DistrictMapper districtMapper;

    //获取区域信息
    @Override
    public List<District> getDistrict() {
        return this.districtMapper.selectByExample(new DistrictExample());
    }
}
