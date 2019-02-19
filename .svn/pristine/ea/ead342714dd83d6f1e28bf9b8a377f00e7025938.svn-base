package com.glsx.oms.bigdata.admin.bma.User.controller;

import com.glsx.oms.bigdata.admin.bma.User.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/currentUser")
    public User getCurrentUser(){
        Tages[] tag = new Tages[6];
        Tages tages0 = new Tages();
        tages0.setKey("0");
        tages0.setLabel("很有想法的");
        tag[0] = tages0;
        Tages tages1 = new Tages();
        tages1.setKey("1");
        tages1.setLabel("专注设计");
        tag[1] = tages1;
        Tages tages2 = new Tages();
        tages2.setKey("2");
        tages2.setLabel("辣~");
        tag[2] = tages2;
        Tages tages3 = new Tages();
        tages3.setKey("3");
        tages3.setLabel("大长腿");
        tag[3] = tages3;
        Tages tages4 = new Tages();
        tages4.setKey("4");
        tages4.setLabel("川妹子");
        tag[4] = tages4;
        Tages tages5 = new Tages();
        tages5.setKey("5");
        tages5.setLabel("海纳百川");
        tag[5] = tages5;
        User user = new User();
        Geographic geographic = new Geographic();
        Province province=new Province();
        City city = new City();
        city.setLabel("杭州市");
        city.setKey("330100");
        province.setLabel("浙江省");
        province.setKey("330000");
        geographic.setCity(city);
        geographic.setProvince(province);
        user.setName("Serati Ma");
        user.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png");
        user.setUserid("00000001");
        user.setEmail("antdesign@alipay.com");
        user.setSignature("海纳百川，有容乃大");
        user.setTitle("交互专家");
        user.setGroup("蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED");
        user.setTags(tag);
        user.setNotifyCount(12);
        user.setCountry("china");
        user.setGeographic(geographic);
        user.setAddress("西湖区工专路 77 号");
        user.setPhone("0752-268888888");
        return user;
    }

}
