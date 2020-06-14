package com.team.house_backapi.service.impl;

import com.team.house_backapi.entity.Users;
import com.team.house_backapi.entity.UsersExample;
import com.team.house_backapi.mapper.UsersMapper;
import com.team.house_backapi.service.UsersService;
import com.team.house_backapi.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired(required = false)
    private UsersMapper usersMapper;

    @Override
    public int regUser(Users users) {
        //密码不要以明文方式保存到数据中，这样做不安全
        //使用md5工具类对密码进行加密后存储到数据库
        //使用步骤:1.导入md5工具类  2.调用md5工具类的方法进行加密
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return this.usersMapper.insertSelective(users);
    }

    //登入的密码加密后再进行数据库比对
    @Override
    public Users Login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //添加条件
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> users = usersMapper.selectByExample(usersExample);
        if (users!=null&&users.size()==1){
            return users.get(0);
        }
        return null;
    }
}
