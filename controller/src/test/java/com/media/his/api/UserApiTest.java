package com.media.his.api;


import com.media.his.HisStartApplication;
import com.media.his.entity.PeopleDO;
import com.media.his.util.StudentUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HisStartApplication.class)
@WebAppConfiguration
public class UserApiTest {



    @Test
    public void getChineseName() {
        String chineseName = StudentUtil.getChineseName();
        System.out.println(chineseName);
    }

    @Test
    public void getPhoneNumber() {
        for (int i = 0; i < 100; i++) {
            String phoneNumber = StudentUtil.getPhoneNumber();
            System.out.println(phoneNumber);
        }
    }

    @Test
    public void getTel() {
        for (int i = 0; i < 100; i++) {
            String road = StudentUtil.getRoad();
            System.out.println(road);
        }
    }
}