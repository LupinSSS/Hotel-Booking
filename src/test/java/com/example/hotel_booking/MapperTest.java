package com.example.hotel_booking;

import com.example.hotel_booking.common.domain.Admin;
import com.example.hotel_booking.common.domain.AdminExample;
import com.example.hotel_booking.common.domain.Book;
import com.example.hotel_booking.mapper.AdminMapper;
import com.example.hotel_booking.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired(required = false)
    AdminMapper adminMapper = null;

    @Autowired
    BookMapper bookMapper;

    @Test
    public void test1(){
        String name = "admin";
        AdminExample adminExample = new AdminExample();
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        for(Admin admin: admins){
            System.out.println(admin.getLoginname()+"/"+admin.getPassword());
        }
    }

    @Test
    public void test2() {
        for(int i = 59; i <= 80; i++) {
            Book book = new Book();
            book.setName("书名-" +i);
            book.setAuthor("作者-" + i);
            book.setPrice((int)(Math.random()*1000) / 100.0);
            book.setRemark("备注-"+i);
            bookMapper.insert(book);
        }
    }

}
