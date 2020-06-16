package com.example.hotel_booking.service;

import com.example.hotel_booking.common.domain.Hotel;
import com.example.hotel_booking.common.domain.HotelExample;
import com.example.hotel_booking.mapper.HOTELMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired(required = false)
    private HOTELMapper hotelMapper;

    public Hotel login(String hid) {
        Hotel hotel1 = hotelMapper.selectByPrimaryKey(hid);
        return hotel1;
    }

    public void insert(Hotel hotel) {
        hotelMapper.insert(hotel);
    }

    public Hotel scaninfo(Hotel hotel) {
        //真正处理请求的地方
        //请求目的：获取该酒店 调用获取该酒店的数据库操作
        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andHidEqualTo(hotel.getHid());
        List<Hotel> hotels = hotelMapper.selectByExample(hotelExample);

        return (hotels.size()>0?hotels.get(0):null);
    }

    //更新修改酒店信息
    public int update(Hotel hotel) {
        //目的调用update语句修改该酒店的数据库操作
        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andHidEqualTo(hotel.getHid());
        int  result = hotelMapper.updateByPrimaryKey(hotel);
        return result;
    }

    public int updatepassword(Hotel hotel) {
        return hotelMapper.updatepsdByPrimaryKey(hotel);
    }
}
