package com.lvtong.retrofitdemo.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.tabs.TabLayout;
import com.lvtong.retrofitdemo.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author tong.lv
 * @date 2020/1/27
 */
public class HomeFragment extends Fragment {
    private RecyclerView rvNewsList;
    private TabLayout tlNewsType;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mNewsAdapter;

    private String currentCategory = "home";

    private List<CSDNNews.Article> mNewsList = new ArrayList<>();
    private String[] mTabList = {"推荐", "程序人生", "Python", "Java", "前端", "架构", "区块链",
            "数据库", "游戏开发", "移动开发", "运维", "人工智能", "安全", "云计算/大数据", "研发管理",
            "物联网", "计算机基础", "音视频开发", "其他"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initData();
        initView(root);
        initMethod();
        return root;
    }

    private void initData() {
        //no use
    }

    private void initView(View root) {
        tlNewsType = root.findViewById(R.id.tl_news_type);
        for (String string : mTabList) {
            tlNewsType.addTab(tlNewsType.newTab().setText(string));
        }
        tlNewsType.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentCategory = getTabType(tab.getPosition());
                request(currentCategory);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        rvNewsList = root.findViewById(R.id.rv_news_list);
        rvNewsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout = root.findViewById(R.id.srl_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        //设置下拉刷新时的监听器，在重写的onRefresh方法中实现操作
        mSwipeRefreshLayout.setOnRefreshListener(() -> request(currentCategory));
    }

    private void initMethod() {
        request(currentCategory);
    }

    private String getTabType(int code) {
        switch (code) {
            case 0:
                return "home";
            case 1:
                return "career";
            case 2:
                return "python";
            case 3:
                return "java";
            case 4:
                return "web";
            case 5:
                return "arch";
            case 6:
                return "blockchain";
            case 7:
                return "db";
            case 8:
                return "game";
            case 9:
                return "mobile";
            case 10:
                return "ops";
            case 11:
                return "ai";
            case 12:
                return "sec";
            case 13:
                return "cloud";
            case 14:
                return "engineering";
            case 15:
                return "iot";
            case 16:
                return "fund";
            case 17:
                return "avi";
            case 18:
                return "other";
            default:
                return "home";
        }
    }

    public void request(String category) {
        System.out.println("当前:" + category);
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
        Call<CSDNNews> call = request.getCsdnNews(category, System.currentTimeMillis());

        //发送网络请求(异步)
        call.enqueue(new Callback<CSDNNews>() {
            @Override
            public void onResponse(Call<CSDNNews> call, Response<CSDNNews> response) {
                System.out.println(response.body().toString());
                System.out.println("连接成功" + response.body().getArticles().size());
                mNewsList = response.body().getArticles();
                if (mNewsAdapter == null) {
                    mNewsAdapter = new NewsAdapter(getActivity(), mNewsList);
                    rvNewsList.setAdapter(mNewsAdapter);
                } else {
                    mNewsAdapter.setNewsList(mNewsList);
                    mNewsAdapter.notifyDataSetChanged();
                }
                mSwipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<CSDNNews> call, Throwable t) {
                Toast.makeText(getActivity(), "连接失败", Toast.LENGTH_SHORT).show();
                System.out.println("连接失败");
            }
        });
    }
}
