package com.lvtong.retrofitdemo.news;

import java.util.List;

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
    /**
     * Gets news list.
     *
     * @return the news list
     */
    @GET("api/news")
    Call<List<News>> getNewsList();

    /**
     * Gets news info.
     *
     * @param newsId the news id
     * @return the news info
     */
    @GET("api/news/36kr/{newsId}")
    Call<News> getNewsInfo(@Path("newsId") String newsId);

    @GET("api/articles?type=more&category=home&first_view=false")
    Call<CSDNNews> getCsdnNews(@Query("shown_offset") long shownOffset);

    @GET("{user_name}/article/details/{article_id}")
    Call<String> getCsdnNewsInfo(@Path("user_name") String userName, @Path("article_id") int articleId);
}
