package com.xuzhaocai.multi.service.impl;


import com.xuzhaocai.multi.dao.driver.DriverInfoDao;
import com.xuzhaocai.multi.dao.order.OrderInfoDao;
import com.xuzhaocai.multi.entity.DriverInfo;
import com.xuzhaocai.multi.entity.OrderInfo;
import com.xuzhaocai.multi.service.DirverInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DriverInfoServiceImpl implements DirverInfoService {
    @Autowired
    private DriverInfoDao driverInfoDao;
    @Autowired
    private OrderInfoDao orderInfoDao;

    //接单操作。
    @Transactional()
    @Override
    public String takeOrder(Integer driverId, Integer orderId) {
        OrderInfo orderInfo = orderInfoDao.selectById(orderId);
        DriverInfo driverInfo = driverInfoDao.selectById(driverId);
        // 验证 当前司机没有接单，当前订单没有被司机接走
        if (driverInfo==null || driverInfo.getCurrOrderId()!=null || orderInfo==null || orderInfo.getDriverId()!=null  ){
            return "失败";
        }
        orderInfo.setDriverId(driverId);
        driverInfo.setCurrOrderId(orderId);
        int updateDriverFlag = driverInfoDao.updateReceivedOrder(driverInfo);
        int updateOrderFlag = orderInfoDao.updateOrderAddDriverInfo(orderInfo);
        // 模拟出问题
        int i= 1/0;
        if (updateDriverFlag+updateOrderFlag==2){
            return "成功";
        }
        return "失败";
    }
}
