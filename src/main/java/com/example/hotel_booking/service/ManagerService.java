package com.example.hotel_booking.service;


import com.example.hotel_booking.common.domain.Hotel;
import com.example.hotel_booking.common.domain.HotelExample;
import com.example.hotel_booking.common.domain.User;
import com.example.hotel_booking.common.domain.UserExample;
import com.example.hotel_booking.mapper.HOTELMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.hotel_booking.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManagerService{
    @Autowired(required = false)
    UserMapper usermapper;
    //管理员对用户
    public PageInfo<User> findUsers(int pageNo, int pageSize) {
        //显示所有用户
        PageHelper.startPage(pageNo, pageSize);
        List<User> users = usermapper.selectByExample(null);
        return new PageInfo<User>(users);
    }
    public void deletUser(User u)
    {
        //删除某个用户
        UserExample ex = new UserExample();
        ex.or().andUidEqualTo(u.getUid());
        System.out.println(u.getUid());
        System.out.println(usermapper.deleteByExample(ex));
    }
    public void deletUsers(List<String> list)
    {
        //删除所有用户
        UserExample ex = new UserExample();
        ex.or().andUidIn(list);
        usermapper.deleteByExample(ex);
    }

    //管理员对酒店
    @Autowired(required = false)
    HOTELMapper hotelmapper;
    public PageInfo<Hotel> findHotels(int pageNo, int pageSize) {
        //显示所有酒店
        PageHelper.startPage(pageNo, pageSize);
        List<Hotel> hotels = hotelmapper.selectByExample(null);
        return new PageInfo<Hotel>(hotels);
    }
    //删除单个酒店
    public void deleteHotel(Hotel h)
    {
        h.getHid();
        hotelmapper.deleteByPrimaryKey(h.getHid());
    }
    //删除所有酒店
    public void deleteHotels(List<String> list)
    {
        HotelExample ex = new HotelExample();
        ex.or().andHidIn(list);
        hotelmapper.deleteByExample(ex);
    }
    //显示所有酒店
    public Hotel ShowHotelDetail(String id)
    {
        return hotelmapper.selectByPrimaryKey(id);
    }
}
