package com.example.hotel_booking.Controller;


import com.example.hotel_booking.common.domain.Hotel;
import com.example.hotel_booking.common.domain.User;
import com.example.hotel_booking.common.utils.Result;
import com.example.hotel_booking.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagerController {
    @Autowired(required = false)
    ManagerService managerservice;

    //修改酒店信息界面
    @RequestMapping("/modify")
    public Object modifyHotel(ModelMap modelmap, String uid)
    {
        modelmap.put("hotel", managerservice.ShowHotelDetail(uid));
        System.out.println("1");
        return "admin/modifyHotel";
    }
    //跳转到酒店信息界面
    @RequestMapping("/showhotel")
    public Object showDetail(ModelMap modelmap, String uid)
    {
        modelmap.put("hotel", managerservice.ShowHotelDetail(uid));
        return "admin/hotelDetail";
    }
    //跳转至首页
    @RequestMapping("/menu")
    public Object showMenu()
    {
        return "admin/menu";
    }

    //跳转至显示所有酒店的页面
    @RequestMapping("/allhotels")
    public Object showHotels()
    {
        return "admin/Hotels";
    }

    //显示所有酒店到界面
    @GetMapping("/gethotels")
    @ResponseBody
    public Object getHotels(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize)
    {
        //return Result.success(managerservice.findHotels(pageNo, pageSize));
        return Result.success(managerservice.findHotels(pageNo, pageSize));
    }

    //注销一个酒店
    @DeleteMapping("/deleteHotel")
    @ResponseBody
    public Object deleteHotel(@RequestBody Hotel h)
    {
        managerservice.deleteHotel(h);
        return Result.success(h.getHid());
    }

    //注销所有酒店
    @DeleteMapping("/deleteHotels")
    @ResponseBody
    public Object deleteHotels(@RequestBody List<String> list)
    {
        managerservice.deleteHotels(list);
        return Result.success();
    }
    //显示用户信息到管理员主界面
    @GetMapping("/ManagerMenu")
    @ResponseBody
    public Object ManagerMenu(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize)
    {
        return Result.success(managerservice.findUsers(pageNo, pageSize));
    }
    //注销单个用户
    @DeleteMapping("/deleteUser")
    @ResponseBody
    public Object deleteUser(@RequestBody User u)
    {
        managerservice.deletUser(u);
        return Result.success(u.getUid());
    }

    //注销多个用户
    @DeleteMapping("/deleteUsers")
    @ResponseBody
    public Object deletUsers(@RequestBody List<String> list)
    {
        managerservice.deletUsers(list);
        return Result.success();
    }


    /************************管理员登录部分***************************/
    /**
     * 登录跳转
     * @return
     */
    @RequestMapping("/adminlogin")
    public String adminlogin() {
        return "admin/adminlogin";
    }

    /**
     * 登录
     * @param adminid
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(String adminid, String password) {
        if (adminid.equals("root") && password.equals("root"))
            return "admin/menu";
        else {
            System.out.println("管理员名或密码错误");
            return "admin/adminlogin";
        }
    }

    /**
     * 返回index
     * @return
     */
    @RequestMapping("/back")
    public String back() {
        return "index";
    }

}
