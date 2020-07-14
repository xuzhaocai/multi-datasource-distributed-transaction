package com.xuzhaocai.multi.dao.driver;

import com.xuzhaocai.multi.entity.DriverInfo;
import com.xuzhaocai.multi.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverInfoDao {
    /**
     * 修改司机信息表中，当前接单的订单
     * @param driverInfo 司机信息
     * @return
     */
    public int updateReceivedOrder(@Param("driverInfo") DriverInfo driverInfo);

    /**
     * 通过司机id获取司机信息
     * @param id  司机id
     * @return 司机信息
     */
    public DriverInfo selectById(@Param("dirverId") Integer id);
}
