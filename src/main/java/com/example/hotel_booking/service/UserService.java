package com.example.hotel_booking.service;

import com.example.hotel_booking.common.domain.User;
import com.example.hotel_booking.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User login(User user) {
        User user1 = userMapper.selectByPrimaryKey(user.getUid());
        return user1;
    }

    public void insert(User user) {
        userMapper.insert(user);
    }
}
