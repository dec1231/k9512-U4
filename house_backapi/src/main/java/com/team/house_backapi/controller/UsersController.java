package com.team.house_backapi.controller;

import com.team.house_backapi.entity.Users;
import com.team.house_backapi.service.UsersService;
import com.team.house_backapi.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/regUser")
    public BaseResult regUser(Users users){
        //调用业务
        int insert=this.usersService.regUser(users);
        if (insert>0)
            return new BaseResult(BaseResult.RESULT_SUCCESS,"");
        else
            return new BaseResult(BaseResult.RESULT_FAIL,"注册出错啦！");
    }

    @RequestMapping("/userLogin")
    public BaseResult loginUser(HttpSession session,String password,String username){
        //调用业务
        Users users=usersService.Login(username, password);
        if (users==null){
            return new BaseResult(BaseResult.RESULT_FAIL,"用户名密码不正确");
        }
        else {
            //只要登入请使用session保存登入人的信息
                    session.setAttribute("loginInfo",users);
                    session.setMaxInactiveInterval(10000);
                    return new BaseResult(BaseResult.RESULT_SUCCESS,"");
        }
    }

    //用户信息
    @RequestMapping("/showUser")
    public void showUser(HttpSession session){
        Users users=(Users)session.getAttribute("loginInfo");
        System.out.println("用户id:"+users.getId());
    }
}
