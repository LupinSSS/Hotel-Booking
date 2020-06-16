package com.example.hotel_booking.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    /**
     * 登录跳转
     * @return
     */
    @RequestMapping("/admin/adminlogin")
    public String adminlogin() {
        return "admin/adminlogin";
    }

    /**
     * 登录
     * @param adminid
     * @param password
     * @return
     */
    @RequestMapping("/admin/login")
    public String login(String adminid, String password) {
        if (adminid.equals("root") && password.equals("root"))
            return "admin/main";
        else {
            System.out.println("管理员名或密码错误");
            return "admin/adminlogin";
        }
    }


    /**
     * 返回index
     * @return
     */
    @RequestMapping("/admin/back")
    public String back() {
        return "index";
    }

}
