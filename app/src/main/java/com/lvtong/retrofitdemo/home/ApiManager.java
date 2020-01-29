package com.lvtong.retrofitdemo.home;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The interface Api manager.
 *
 * @author tong.lv
 * @date 2020 /1/11
 */
public interface ApiManager {

    @GET("api/articles?type=more&first_view=false")
    Call<CSDNNews> getCsdnNews(@Query("category") String category, @Query("shown_offset") long shownOffset);

    @GET("{user_name}/article/details/{article_id}")
    Call<String> getCsdnNewsInfo(@Path("user_name") String userName, @Path("article_id") int articleId);
}
