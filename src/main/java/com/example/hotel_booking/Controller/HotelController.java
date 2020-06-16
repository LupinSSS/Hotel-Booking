package com.example.hotel_booking.Controller;

import com.example.hotel_booking.common.domain.Hotel;
import com.example.hotel_booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    HttpSession httpSession;

    /**
     * 登录跳转
     * @return
     */
    @RequestMapping("/hotel/hotellogin")
    public String hotellogin() {
        return "hotel/hotellogin";
    }

    /**
     * 登录 将酒店信息保存到"hotel"中
     * @param hotel
     * @param modelMap
     * @return
     */
    @RequestMapping("/hotel/login")
    public String login(Hotel hotel, ModelMap modelMap) {
        Hotel hotelInDB = hotelService.login(hotel.getHid());
        boolean success = true;
        if (hotelInDB == null) {
            success = false;
            System.out.println("酒店id不存在");
        } else {
            if (hotelInDB.getPassword().equals(hotel.getPassword())) {
                httpSession.setAttribute("hotel", hotel);//将登录的酒店信息保存进httpsession
                modelMap.addAttribute(hotel);
                success = true;
            }
            else {
                System.out.println("密码错误");
                success = false;
            }
        }
        return success?show_scaninfo(modelMap):"hotel/hotellogin";
    }

    /**
     * 注册跳转
     * @return
     */
    @RequestMapping("/hotel/hotelregister")
    public String hotelregister() {
        return "hotel/hotelregister";
    }

    /**
     * 注册
     * @param hid
     * @param name
     * @param password
     * @param translated_name
     * @param address
     * @param city
     * @param state
     * @param country
     * @param star_rating
     * @param url
     * @param overview
     * @param rating_average
     * @param photo1
     * @param photo2
     * @param photo3
     * @param photo4
     * @param photo5
     * @param checkinTime
     * @param checkoutTime
     * @return
     * @throws ParseException
     */
    @RequestMapping("/hotel/register")
    public String register(@RequestParam("hid") String hid, @RequestParam("NAME") String name, @RequestParam("PASSWORD") String password,
                           @RequestParam("translated_name") String translated_name, @RequestParam("address") String address, @RequestParam("city") String city,
                           @RequestParam("state") String state, @RequestParam("country") String country, @RequestParam("star_rating") String star_rating,
                           @RequestParam("url") String url, @RequestParam("overview") String overview, @RequestParam("rating_average") String rating_average,
                           @RequestParam("photo1") String photo1, @RequestParam("photo2") String photo2, @RequestParam("photo3") String photo3,
                           @RequestParam("photo4") String photo4, @RequestParam("photo5") String photo5,
                           @RequestParam("checkinTime") String checkinTime, @RequestParam("checkoutTime") String checkoutTime) throws ParseException, IOException {
        Hotel hotelInDB = hotelService.login(hid);
        boolean success = true;
        if (hotelInDB != null) {
            success = false;
            System.out.println("酒店编号已存在，注册失败");
        }
        else {
            Hotel hotel = new Hotel();
            hotel.setHid(hid);
            checkinTime = checkinTime + ":00";
            checkoutTime = checkoutTime + ":00";
            DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date checkinT = sdf.parse(checkinTime);
            Date checkoutT = sdf.parse(checkoutTime);
            hotel.setName(name);
            hotel.setPassword(password);
            hotel.setTranslatedName(translated_name);
            hotel.setAddress(address);
            hotel.setCity(city);
            hotel.setState(state);
            hotel.setCountry(country);
            hotel.setStarRating(Long.valueOf(star_rating));
            hotel.setUrl(url);
            hotel.setOverview(overview);
            hotel.setRatingAverage(Long.valueOf(rating_average));
            hotel.setCheckin(checkinT);
            hotel.setCheckout(checkoutT);
            //转存图片
            String photo = "";
            String save = System.getProperty("user.dir") + "\\photos";
            File file =new File(save);
            //如果文件夹不存在则创建
            if  (!file .exists()  && !file .isDirectory())
            {
                file .mkdir();
            }
            for (int i=1;i<6;i++) {
                switch (i) {
                    case 1:
                        photo = photo1;
                        break;
                    case 2:
                        photo = photo2;
                        break;
                    case 3:
                        photo = photo3;
                        break;
                    case 4:
                        photo = photo4;
                        break;
                    case 5:
                        photo = photo5;
                        break;
                }

                /*-------------------------------------此处的path应为图片的绝对路径，但现在还获取不到呜呜呜呜呜-----------------------------------------*/
                String path = "C:/Users/ZBB/Desktop/testphoto/" + photo;
                /*-------------------------------------此处的path应为图片的绝对路径，但现在还获取不到呜呜呜呜呜-----------------------------------------*/

                FileInputStream in = new FileInputStream(path);
                String savepath = save.replace('\\' ,'/') + "/" + photo;
                FileOutputStream out= new FileOutputStream(savepath);
                BufferedInputStream bufferedIn=new BufferedInputStream(in);
                BufferedOutputStream bufferedOut=new BufferedOutputStream(out);
                byte[] by=new byte[1];
                while (bufferedIn.read(by)!=-1) {
                    bufferedOut.write(by);
                }
                bufferedOut.flush();
                bufferedIn.close();
                bufferedOut.close();
            }

            hotel.setPhoto1(photo1);
            hotel.setPhoto2(photo2);
            hotel.setPhoto3(photo3);
            hotel.setPhoto4(photo4);
            hotel.setPhoto5(photo5);
            hotelService.insert(hotel);
            System.out.println("注册成功");
        }
        return success?"hotel/hotellogin":"hotel/hotelregister";
    }

    /**
     * 返回到index
     */
    @RequestMapping("/hotel/back")
    public String back(Model model) {
        return "index";
    }
    /**
     * 返回到login
     */
    @RequestMapping("/hotel/back1")
    public String back1(Model model) {
        return "hotel/hotellogin";
    }


    //查看酒店信息
    @RequestMapping("/hotel/scaninfo")
    public String show_scaninfo(ModelMap modelMap){
        //得到请求--去查询此宾馆
        //去调用service
        /*
        httpSession.getAttribute("hotel");
        */
        Hotel hotel = (Hotel)httpSession.getAttribute("hotel");
        Hotel hotelInDB = hotelService.scaninfo(hotel);
        if(hotelInDB == null){
            System.out.println("不存在");
        }else{
            //展示在前端
            System.out.println(hotelInDB.getAddress()+hotelInDB.getTranslatedName());
            modelMap.addAttribute("hotel", hotelInDB);
        }
        //modelMap.addAttribute("name", "thymeleaf");
        return "/hotel/scaninfo";
    }

    //跳转编辑信息界面
    @RequestMapping("/toeditinfo")
    public String edit_showinfo(ModelMap modelMap){
        return show_editinfo(modelMap);
    }

    //显示编辑信息页面
    @RequestMapping("/hotel/editinfo")
    public String show_editinfo(ModelMap modelMap){
        //在表单中显示该宾馆的信息
        //得到请求--去查询此宾馆
        //去调用service
        Hotel hotel = (Hotel)httpSession.getAttribute("hotel");
            Hotel hotelInDB = hotelService.scaninfo(hotel);
            if(hotelInDB == null){
                System.out.println("不存在");
            }else{
            //展示在前端
            System.out.println(hotelInDB.getAddress()+hotelInDB.getTranslatedName());
            modelMap.addAttribute("hotel", hotelInDB);
        }
        System.out.println("1111");
        return "/hotel/editinfo";
    }

    //提交修改申请
    @RequestMapping("/edit")
    public String edit_info(Hotel hotel, ModelMap modelMap){
        Hotel hotel_session = (Hotel)httpSession.getAttribute("hotel");
        hotel.setHid(hotel_session.getHid());//此处获取id
        int result = hotelService.update(hotel);
        if(result == 0){
            return show_editinfo(modelMap);
        }else{
            return show_scaninfo(modelMap);
        }
    }

    @RequestMapping("/editpassword")
    @ResponseBody
    public int edit_password(@RequestBody String password){
        int result = 0;
        Hotel hotel = new Hotel();
        Hotel hotel_session = (Hotel)httpSession.getAttribute("hotel");
        hotel.setHid(hotel_session.getHid());//此处获取id
        hotel.setPassword(password);
        result = hotelService.updatepassword(hotel);
        System.out.println(password);
        return result;
    }

    @RequestMapping("/hotel/general")
    public String show(){
        return "/hotel/general";
    }
}
