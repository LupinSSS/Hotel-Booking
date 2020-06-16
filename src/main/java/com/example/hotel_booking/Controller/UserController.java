package com.example.hotel_booking.Controller;

import com.example.hotel_booking.common.domain.Hotel;
import com.example.hotel_booking.common.domain.User;
import com.example.hotel_booking.mapper.HOTELMapper;
import com.example.hotel_booking.mapper.UserMapper;
import com.example.hotel_booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private HOTELMapper hotelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    /**
     * 登录跳转
     */
    @RequestMapping("/user/userlogin")
    public String userlogin(Model model) {
        return "user/userlogin";
    }

    /**
     * 登录  登录成功后将用户保存到"user"中
     */
    @RequestMapping("/user/login")
    public String login(User user, Model model) {
        //System.out.println(user.getUid() + " " + user.getPassword());
        User userInDB = userService.login(user);
        boolean success = true;
        if (userInDB == null) {
            success = false;
            System.out.println("用户电话不存在");
        } else {
            if (userInDB.getPassword().equals(user.getPassword())) {
                httpSession.setAttribute("user", user);//将登录的用户信息保存进httpsession
                model.addAttribute(user);
                success = true;
                List<Hotel> hotels = hotelMapper.selectByExample(null);
                model.addAttribute("hotels", hotels);
            }
            else {
                System.out.println("密码错误");
                success = false;
            }
        }
        return success?"user/main":"user/userlogin";
    }

    /**
     * 返回到index
     */
    @RequestMapping("/user/back")
    public String back(Model model) {
        return "index";
    }
    /**
     * 返回到login
     */
    @RequestMapping("/user/back1")
    public String back1(Model model) {
        return "user/userlogin";
    }

    /**
     * 注册跳转
     */
    @RequestMapping("/user/userregister")
    public String userregister(Model model) {
        return "user/userregister";
    }

    /**
     * 注册
     */
    @RequestMapping("/user/register")
    public String register(User user) {
        User userInDB = userService.login(user);
        boolean success = true;
        if (userInDB != null) {
            success = false;
            System.out.println("用户电话已存在，注册失败");
        }
        else {
            System.out.println("注册成功");
            userService.insert(user);
        }
        return success?"user/userlogin":"user/userregister";
    }


    @RequestMapping("/user")
    public String index(ModelMap modelMap){
        //User user1 = userMapper.selectByPrimaryKey("18228920993");
        //httpSession.setAttribute("user", user1);
        List<Hotel> hotels = hotelMapper.selectByExample(null);
        modelMap.addAttribute("hotels", hotels);
        return "user/main";
    }

    @RequestMapping("/user/index")
    public String viewProfile (ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        User user1 = userMapper.selectByPrimaryKey(user.getUid());
        modelMap.addAttribute("user", user1);
        return "user/viewProfile";
    }

    @RequestMapping("/user/editProfile")
    public String editProfile (User user, ModelMap modelMap){
        User user1 = (User) httpSession.getAttribute("user");
        User user2 = userMapper.selectByPrimaryKey(user1.getUid());
        boolean edit =false;
        //System.out.println(user.getName());
        //System.out.println(user.getPassword());
        if(user.getName() != null){
            edit = true;
            user2.setPassword(user.getPassword());
            user2.setName(user.getName());
            userMapper.updateByPrimaryKey(user2);
            //System.out.println(userMapper.selectByPrimaryKey(user2.getUid()));
            httpSession.setAttribute("user", user2);
        }
        return edit ? viewProfile(modelMap):"user/editProfile";
    }

    @RequestMapping("/user/viewHotel")
    public String viewHotel (String id){
        Hotel hotel = hotelMapper.selectByPrimaryKey(id);
        //httpSession.setAttribute("hotel",hotel); 为了之后跳转回来做准备
        System.out.println(hotel.getName());
        return null;
    }
}
