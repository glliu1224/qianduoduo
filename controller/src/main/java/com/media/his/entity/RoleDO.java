package com.media.his.entity;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDO {
    private Integer id;

    private String roleName;

    private Date createTime;

    private Date updateTime;

    static class Builder {
        private RoleDO roleDO;

        public Builder(RoleDO roleDO) {
            this.roleDO = roleDO;
        }

        public Builder setRoleName(String roleName) {
            roleDO.setRoleName(roleName);
            return this;
        }

        public Builder setCreateTime(Date createTime) {
            roleDO.setCreateTime(createTime);
            return this;
        }

        public RoleDO build() {
            return roleDO;
        }
    }
}