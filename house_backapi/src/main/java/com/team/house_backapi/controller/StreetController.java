package com.team.house_backapi.controller;

import com.team.house_backapi.entity.Street;
import com.team.house_backapi.service.StreetService;
import com.team.house_backapi.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/street/")
public class StreetController {

    @Autowired
    private StreetService streetService;

    //获取街道信息
    @RequestMapping("/streetData")
    public BaseResult streetData(Integer did){
        List<Street> streetList= this.streetService.getStreetByDId(did);
        return new BaseResult(200,streetList);
    }
}
