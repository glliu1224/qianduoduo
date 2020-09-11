package com.media.his.util;

import com.media.his.pojoVO.RepeatVO;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元素处理过滤工具类
 */
public class FieldFilterUtil {

    public static List<RepeatVO> filterElement(List<RepeatVO> list) {
        List<RepeatVO> result = new LinkedList<>();
        List<String> collect = list.stream().map(RepeatVO::getName).collect(Collectors.toList())
                .stream().distinct().collect(Collectors.toList());
        first:
        for (String name : collect) {
            RepeatVO repeat = new RepeatVO();
            repeat.setName(name);
            boolean boo = false;
            for (RepeatVO repeatVO : list) {
                if (repeatVO.getName().equals(name) && repeatVO.isRepeat()) {
                    boo = true;
                    repeat.setRepeat(boo);
                    result.add(repeat);
                    continue first;
                }
            }
            result.add(repeat);
        }
        return result;
    }
}
