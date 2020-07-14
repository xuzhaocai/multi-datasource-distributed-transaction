package com.xuzhaocai.multi.entity;

import java.util.Date;



// 司机信息
public class DriverInfo {
    private Integer  id;
    private  String driverName;
    private Integer currOrderId;
    private Date createTime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public Integer getCurrOrderId() {
        return currOrderId;
    }
    public void setCurrOrderId(Integer currOrderId) {
        this.currOrderId = currOrderId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
