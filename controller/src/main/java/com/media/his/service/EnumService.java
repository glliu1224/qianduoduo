package com.media.his.service;

import com.google.common.base.Preconditions;
import com.media.his.constant.enumDTO.LanguageEnumDTO;
import com.media.his.constant.enums.LanguageEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnumService {
    /**
     * 获取所有枚举值
     */
    public Map<String, Object> findAllEnum() {
        Map<String, Object> enumMap = new HashMap<>();
        enumMap.put(getEnumName(LanguageEnum.class), getAllEnum(LanguageEnum.class));
        return enumMap;
    }


    /**
     * 获取枚举类名称
     */
    private String getEnumName(Class zClass) {
        Preconditions.checkNotNull(zClass, "Class can't null!");
        return zClass.getSimpleName();
    }

    private Object getAllEnum(Class zClass) {
        Preconditions.checkNotNull(zClass, "Class can't null!");
        //语言枚举
        if (zClass.equals(LanguageEnum.class)) {
            List<LanguageEnumDTO> results = new ArrayList<>();
            for (LanguageEnum languageEnum : LanguageEnum.values()) {
                LanguageEnumDTO dto = new LanguageEnumDTO();
                dto.setId(languageEnum.getLanguage());
                dto.setLanguage(languageEnum.getLanguage());
                dto.setLanguageDesc(languageEnum.getLanguageDesc());
                results.add(dto);
            }
            return results;
        }


        return null;
    }


}











