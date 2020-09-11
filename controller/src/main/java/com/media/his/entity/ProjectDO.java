package com.media.his.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDO {

    private int id;
    private String projectName;
    private int projectType;
    private String projectDesc;
    private Date createTime;
    private Date updateTime;

    public ProjectDO(int id, String projectName, int projectType, String projectDesc, Date createTime, Date updateTime) {
        this.id = id;
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectDesc = projectDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProjectDO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectType() {
        return projectType;
    }

    public void setProjectType(int projectType) {
        this.projectType = projectType;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
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
