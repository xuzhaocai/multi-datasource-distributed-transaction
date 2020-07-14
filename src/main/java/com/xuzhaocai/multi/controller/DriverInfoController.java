package com.xuzhaocai.multi.controller;


import com.xuzhaocai.multi.service.DirverInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/driver/")
public class DriverInfoController {

    @Autowired
    private DirverInfoService dirverInfoService;
    /**
     * 接单方法
     * @return
     */
    @RequestMapping(value = "/takeOrder",method = RequestMethod.GET)
    public String takeOrder(){
        Integer driverId =1;
        Integer orderId=1;
        return dirverInfoService.takeOrder(driverId,orderId);
    }
}
