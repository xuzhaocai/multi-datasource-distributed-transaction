package com.xuzhaocai.multi.entity;

import java.util.Date;


// 订单信息表
public class OrderInfo {
    private Integer id;
    private Integer driverId;
    private Date createTime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDriverId() {
        return driverId;
    }
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
