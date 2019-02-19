package com.glsx.oms.bigdata.admin.bma.api;

import HDaoGps.DaoGpsServicePrxHelper;
import HDaoGps.GpsGetRequest;
import HDaoGps.GpsGetResponse;
import HDaoGps.GpsGetResponseHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 获取GPS信息的ICE接口
 * @Author yangbin
 * @Date 10:51 2018/10/26
 * @Version 1.0
 */
public class GPSApi {

    /***
     * 获取某段时间的GPS信息
     * @Author yangbin
     * @Date 10:52 2018/10/26
     * @Param [iceUrl,       ICE连接地址
     *          userID,      设备ID
     *          apType,      设备类型
     *          extraMapReq, 地图类型 0: none 1: google&baidu 2: google 3: baidu
     *          startTime,   开始时间
     *          endTime      结束时间
     *         ]
     * @Return HDaoGps.GpsGetResponse
     */
    public static GpsGetResponse getGPSRecords(String iceUrl, long userID,
                                               int apType, int extraMapReq,
                                               Date startTime, Date endTime)
            throws Exception {
        // Communicator实例
        Ice.Communicator ic = null;
        //调用Ice.Util.Initialize()初始化Ice run time
        ic = Ice.Util.initialize();
        //根据servant的名称以及服务器ip、端口获取远程服务代理
        Ice.ObjectPrx base = ic.stringToProxy(iceUrl);
        //将上面的代理向下转换成一个Printer接口的代理
        DaoGpsServicePrxHelper daoGpsService = (DaoGpsServicePrxHelper) DaoGpsServicePrxHelper.checkedCast(base);
        // 如果转换成功
        if (daoGpsService == null) {
            throw new Error("Invalid proxy");
        }
        //请求参数对象
        GpsGetRequest req = new GpsGetRequest();
        req.userID = userID;
        req.apType = apType;
        req.extraMapReq = extraMapReq;

        req.startTime = new Long(startTime.getTime() / 1000).intValue();
        req.endTime = new Long(endTime.getTime() / 1000).intValue();
        //返回结果对象
        GpsGetResponseHolder res = new GpsGetResponseHolder();
        //调用获取GPS记录的接口
        daoGpsService.getGpsRecords(req, res);
        return res.value;
    }
}
