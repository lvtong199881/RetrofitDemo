package com.lvtong.retrofitdemo.translation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The interface Request interface.
 *
 * @author tong.lv
 * @date 2020 /1/10
 */
public interface TranslationService {

    /**
     * 注解里传入 网络请求 的部分URL地址
     * Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
     * 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
     * getCall()是接受网络请求数据的方法
     *
     * @param translationString the translation string
     * @return the call
     */
    @GET("ajax.php?a=fy&f=auto&t=auto")
    Call<Translation> getCall(@Query("w") String translationString);


}
