package com.team.house_backapi.controller;

import com.team.house_backapi.entity.Type;
import com.team.house_backapi.service.TypeService;
import com.team.house_backapi.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type/")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/getAllTypeData")
    public BaseResult getAllTypeData(){
        List<Type> allType = typeService.getAllType();
        return new BaseResult(200,allType);
    }

}
