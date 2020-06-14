package com.team.house_backapi.service;

import com.team.house_backapi.entity.Street;

import java.util.List;

public interface StreetService {

    //根据对应区域id查询街道
    List<Street> getStreetByDId(Integer did);
}
