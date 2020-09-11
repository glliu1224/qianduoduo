package com.media.his.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ContractDO {
    private int id;
    private String contractName;
    private int contractType;
    private String contractDesc;
    private Date createTime;
    private Date updateTime;

    public ContractDO(int id, String contractName, int contractType, String contractDesc, Date createTime, Date updateTime) {
        this.id = id;
        this.contractName = contractName;
        this.contractType = contractType;
        this.contractDesc = contractDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ContractDO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public int getContractType() {
        return contractType;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    public String getContractDesc() {
        return contractDesc;
    }

    public void setContractDesc(String contractDesc) {
        this.contractDesc = contractDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
