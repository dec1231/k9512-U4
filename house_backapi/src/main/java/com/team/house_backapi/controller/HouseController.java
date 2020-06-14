package com.team.house_backapi.controller;

import com.github.pagehelper.PageInfo;
import com.team.house_backapi.entity.House;
import com.team.house_backapi.entity.Users;
import com.team.house_backapi.service.HouseService;
import com.team.house_backapi.util.BaseResult;
import com.team.house_backapi.util.FileUploadUtil;
import com.team.house_backapi.util.HouseCondition;
import com.team.house_backapi.util.PageParmeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/house/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    //发布出租房
    @RequestMapping("faBuHouse")
    public BaseResult faBuHouse(
            HttpSession session,
            House house,
            @RequestParam(value = "pfile", required = false)
                    MultipartFile pfile) {
        //一、实现文件上传
        //注意：a.将上传的文件保存到文件服务器中(d://images充当文件服务器)
        //     b.一个上传的文件域对应一个MultipartFile类的对象
        try {
            //利用上传文件的工具类实现上传文件
            String path = "d:\\images\\house";
            String fileName = FileUploadUtil.upload(pfile, path);
            //二、将输入的出租房信息保存到数据库
            //设置出租房的随机唯一编号(采用时间毫秒)
            //设置上传文件路径
            house.setPath(fileName);
            //调用业务保存信息
            Users user=(Users)session.getAttribute("loginInfo");
            house.setUserId(user.getId());
            int i = houseService.addHouse(house);
            //假如登入没有实现，获取不到session时，固定用户编号
            return new BaseResult(BaseResult.RESULT_SUCCESS, "",user.getId());

        } catch (Exception e) {
            e.printStackTrace();

            return new BaseResult(BaseResult.RESULT_FAIL, "发布失败:" + e.getMessage());
        }
    }
       //获取用户出租房信息  传递页码page
        @RequestMapping("getHousePageData")
        public BaseResult getHousePageData(PageParmeter pageParameter, HttpSession session){
            //调用业务获取分页信息
            //假如登入没有实现，获取不到session时，固定用户编号
            Users user=(Users)session.getAttribute("loginInfo");
            PageInfo<House> housePageInfo = this.houseService.getHouseByUser(user.getId(), pageParameter);
            //返回总页数，当前页的数据
            Map<String,Object> map=new HashMap<>();
            map.put("totalPage",housePageInfo.getPages());//总页数
            map.put("row",housePageInfo.getList());//当前页数据
            return new BaseResult(200,map);
        }

    //删除出租房   传id
    @RequestMapping("delHouse")
    public BaseResult delHouse(String id){
        //调用业务获取分页信息  1表示删除
        int temp=this.houseService.delete(id,1);
        if (temp>0){
            return new BaseResult(BaseResult.RESULT_SUCCESS,"");
        }
        else {
            return new BaseResult(BaseResult.RESULT_FAIL,"删除失败");
        }
    }

    //浏览出租房  houseCondition接收前端所有的数据
    @RequestMapping("searchHouse")
    public BaseResult searchHouse(HouseCondition houseCondition){
        //调用业务获取分页信息  1表示删除
        PageInfo<House> pageInfo=this.houseService.getHouseDataByHouseCondition(houseCondition);
        //封装返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("curPage",pageInfo.getPageNum());
        map.put("totalPage",pageInfo.getPages());
        map.put("list",pageInfo.getList());
        return new BaseResult(200,map);
    }

}

