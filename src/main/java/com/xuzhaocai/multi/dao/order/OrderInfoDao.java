package com.xuzhaocai.multi.dao.order;

import com.xuzhaocai.multi.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoDao {
    /**
     * 修改订单信息中司机接单信息
     * @param orderInfo 订单详情
     * @return
     */
    public int updateOrderAddDriverInfo(@Param("orderInfo") OrderInfo orderInfo);

    //通过订单ID查询订单信息
    public  OrderInfo selectById(@Param("orderId") Integer oid);
}
