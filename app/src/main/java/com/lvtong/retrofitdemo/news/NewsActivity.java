package com.lvtong.retrofitdemo.news;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lvtong.retrofitdemo.R;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    private NewsAdapter mNewsAdapter;

    private List<CSDNNews.Article> mNewsList = new ArrayList<>();
    private ImageLoader imageLoader = ImageLoader.getInstance();


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
        rvNewsList = findViewById(R.id.rv_news_list);
        rvNewsList.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefreshLayout = findViewById(R.id.srl_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        //设置下拉刷新时的监听器，在重写的onRefresh方法中实现操作
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request();
            }
        });
    }

    private void initMethod() {
        request();
    }

    public void request() {

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                // 设置 网络请求Url
                .baseUrl("https://www.csdn.net/")
                //设置使用Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建网络请求接口的实例
        ApiManager request = retrofit.create(ApiManager.class);

        //对发送请求进行封装
        Call<CSDNNews> call = request.getCsdnNews(System.currentTimeMillis());

        //发送网络请求(异步)
        call.enqueue(new Callback<CSDNNews>() {
            @Override
            public void onResponse(Call<CSDNNews> call, Response<CSDNNews> response) {
                System.out.println(response.body().toString());
                System.out.println("连接成功"+response.body().getArticles().size());
                mNewsList = response.body().getArticles();
                if (mNewsAdapter == null) {
                    mNewsAdapter = new NewsAdapter(NewsActivity.this,mNewsList);
                    rvNewsList.setAdapter(mNewsAdapter);
                } else {
                    mNewsAdapter.setNewsList(mNewsList);
                    mNewsAdapter.notifyDataSetChanged();
                }
                mSwipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<CSDNNews> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                System.out.println("连接失败");
            }
        });
    }

    @Override
    protected void onDestroy() {
        imageLoader.clearMemoryCache();
        super.onDestroy();
    }
}
