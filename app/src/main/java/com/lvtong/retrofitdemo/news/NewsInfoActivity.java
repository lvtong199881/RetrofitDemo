package com.lvtong.retrofitdemo.news;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lvtong.retrofitdemo.Constants;
import com.lvtong.retrofitdemo.R;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 22939
 */
public class NewsInfoActivity extends AppCompatActivity {

    private String newsId = "";
    private String newsInfo = "";
    private TextView tvNewsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        initData();
        initView();
        initMethod();
    }

    private void initData() {
        Intent intent = getIntent();
        if (TextUtils.isEmpty(intent.getStringExtra(Constants.NEWS_POST_ID))) {
            finish();
        } else {
            newsId = intent.getStringExtra(Constants.NEWS_POST_ID);
            System.out.println("postId:" + intent.getStringExtra(Constants.NEWS_POST_ID));
        }
        RichText.initCacheDir(this);
    }

    private void initView() {
        tvNewsInfo = findViewById(R.id.tv_news_info);
    }

    private void initMethod() {
        request();
    }

    public void request() {

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                // 设置 网络请求Url
                .baseUrl("https://unidemo.dcloud.net.cn/")
                //设置使用Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建网络请求接口的实例
        ApiManager request = retrofit.create(ApiManager.class);

        //对发送请求进行封装
        Call<News> call = request.getNewsInfo(newsId);

        //发送网络请求(异步)
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {
                System.out.println("连接成功");
                if (response.body() != null) {
                    newsInfo = response.body().getContent();
                    setTitle(response.body().getTitle());
                    RichText.from(newsInfo).bind(this)
                            .showBorder(false)
                            .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT)
                            .into(tvNewsInfo);
                }
            }

            @Override
            public void onFailure(@NotNull Call<News> call, @NotNull Throwable t) {
                Toast.makeText(NewsInfoActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                System.out.println("连接失败");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RichText.clear(this);
    }
}
