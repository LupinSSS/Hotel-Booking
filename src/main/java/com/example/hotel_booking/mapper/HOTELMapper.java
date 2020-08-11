package com.example.hotel_booking.mapper;

import com.example.hotel_booking.common.domain.Hotel;
import com.example.hotel_booking.common.domain.HotelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HOTELMapper {
    long countByExample(HotelExample example);

    int deleteByExample(HotelExample example);

    int deleteByPrimaryKey(String hid);

    int insert(Hotel record);

    int insertSelective(Hotel record);

    List<Hotel> selectByExample(HotelExample example);

    Hotel selectByPrimaryKey(String hid);

    int updateByExampleSelective(@Param("record") Hotel record, @Param("example") HotelExample example);

    int updateByExample(@Param("record") Hotel record, @Param("example") HotelExample example);

    int updateByPrimaryKeySelective(Hotel record);

    int updateByPrimaryKey(Hotel record);

    int updatepsdByPrimaryKey(Hotel hotel);
}