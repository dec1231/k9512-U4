package com.team.house_backapi;

import com.team.house_backapi.entity.District;
import com.team.house_backapi.entity.DistrictExample;
import com.team.house_backapi.mapper.DistrictMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseBackapiApplicationTests {

    //注入mapper对象
    @Autowired(required = false)
    private DistrictMapper districtMapper;

    @Test
    public void contextLoads() {
        List<District> districts = districtMapper.selectByExample(new DistrictExample());
        System.out.println("测试:"+districts.size());
    }

}
