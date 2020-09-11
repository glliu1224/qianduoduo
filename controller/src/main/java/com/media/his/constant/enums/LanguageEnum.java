package com.media.his.constant.enums;

/**
 * 语言枚举
 */
public enum LanguageEnum {
    LANG_EN_US(1, "English"),
    LANG_ZH_CN(2, "简体中文");
    private Integer language;
    private String languageDesc;

    LanguageEnum(Integer language, String languageDesc) {
        this.language = language;
        this.languageDesc = languageDesc;
    }

    public Integer getLanguage() {
        return language;
    }

    public String getLanguageDesc() {
        return languageDesc;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public void setLanguageDesc(String languageDesc) {
        this.languageDesc = languageDesc;
    }
}
