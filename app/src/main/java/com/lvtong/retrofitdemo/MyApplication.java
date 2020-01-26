package com.lvtong.retrofitdemo;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author tong.lv
 * @date 2020/1/14
 */
public class MyApplication extends Application {

    public static Retrofit retrofit = new Retrofit.Builder()
            //设置网络请求的Url地址
            .baseUrl("https://huazai.luckyzune.com/api/huazai/")
            //设置数据解析器
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    public void onCreate() {
        super.onCreate();
        // 缓存图片的配置，一般通用的配置
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context) {
        // 创建DisplayImageOptions对象
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();
        // 创建ImageLoaderConfiguration对象
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                context).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        // ImageLoader对象的配置
        ImageLoader.getInstance().init(configuration);
    }

}
