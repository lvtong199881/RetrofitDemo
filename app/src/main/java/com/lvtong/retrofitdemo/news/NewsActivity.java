package com.lvtong.retrofitdemo.news;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lvtong.retrofitdemo.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 22939
 */
public class NewsActivity extends AppCompatActivity {

    private RecyclerView rvNewsList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private List<News> mNewsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initData();
        initView();
        initMethod();
    }

    private void initData() {
        //no use
    }

    private void initView() {
        mSwipeRefreshLayout = findViewById(R.id.srl_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        //设置下拉刷新时的监听器，在重写的onRefresh方法中实现操作
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            //do something
            request();
            rvNewsList.setAdapter(new NewsAdapter(NewsActivity.this, mNewsList));
        });
        rvNewsList = findViewById(R.id.rv_news_list);
        rvNewsList.setLayoutManager(new LinearLayoutManager(this));

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
        Call<List<News>> call = request.getNewsList();

        //发送网络请求(异步)
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NotNull Call<List<News>> call, @NotNull Response<List<News>> response) {
                mNewsList = response.body();
                rvNewsList.setAdapter(new NewsAdapter(NewsActivity.this, mNewsList));
                System.out.println("连接成功" + mNewsList.size());
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NotNull Call<List<News>> call, @NotNull Throwable t) {
                Toast.makeText(NewsActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                System.out.println("连接失败");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
