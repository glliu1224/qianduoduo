package com.media.his.api;


import com.media.his.HisStartApplication;
import com.media.his.entity.PeopleDO;
import com.media.his.pojoVO.RepeatVO;
import com.media.his.util.FieldFilterUtil;
import com.media.his.util.StudentUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sun.reflect.misc.FieldUtil;

import java.util.LinkedList;
import java.util.List;


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

    @Test
    public void test1() {
        int count = 72254;
        double ceil = Math.ceil(count / 100);
        System.out.println(ceil);
        int x = (int) Math.ceil(count / 100) * 100;
        System.out.println(x);
    }

    @Test
    public void field() {
        RepeatVO r1 = new RepeatVO();
        r1.setName("a");
        r1.setRepeat(true);
        RepeatVO r2 = new RepeatVO();
        r2.setName("b");
        r2.setRepeat(false);
        RepeatVO r3 = new RepeatVO();
        r3.setName("a");
        r3.setRepeat(false);
        RepeatVO r4 = new RepeatVO();
        r4.setName("b");
        r4.setRepeat(true);
        List<RepeatVO> result = new LinkedList<>();
        RepeatVO r5 = new RepeatVO();
        r5.setName("c");
        r5.setRepeat(false);
        result.add(r1);
        result.add(r2);
        result.add(r3);
        result.add(r4);
        result.add(r5);
        List<RepeatVO> repeatVOS = FieldFilterUtil.filterElement(result);
        for (RepeatVO repeatVO : repeatVOS) {
            System.out.println(repeatVO);
        }
    }
}