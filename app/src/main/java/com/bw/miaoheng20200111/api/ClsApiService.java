package com.bw.miaoheng20200111.api;

import com.bw.miaoheng20200111.entity.ClsEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 时间 :2020/1/11  8:49
 * 作者 :苗恒
 * 功能 :
 */
public interface ClsApiService {
     @GET("small/order/verify/v1/findOrderListByStatus")
        Observable<ClsEntity> getData(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("status") String status, @Query("page")String page, @Query("count")String count);
}
