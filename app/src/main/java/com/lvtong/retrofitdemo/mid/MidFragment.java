package com.lvtong.retrofitdemo.mid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lvtong.retrofitdemo.R;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author tong.lv
 * @date 2020/1/27
 */
public class MidFragment extends Fragment {
    private EditText mEditText;
    private TextView mTextView;
    private Button mButtonGet;

    private Translation translation = new Translation();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mid, container, false);
        mEditText = root.findViewById(R.id.et_translate);
        mTextView = root.findViewById(R.id.tv_translation);
        mButtonGet = root.findViewById(R.id.bt_get);
        mButtonGet.setOnClickListener(v -> request(mEditText.getText()
                .toString()));
        return root;
    }

    private void request(String translationString) {

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                // 设置 网络请求Url
                .baseUrl("http://fy.iciba.com/")
                //设置使用Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建网络请求接口的实例
        TranslationService request = retrofit.create(TranslationService.class);

        //对发送请求进行封装
        Call<Translation> call = request.getCall(translationString);

        //发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时候的回调
            @Override
            public void onResponse(@NotNull Call<Translation> call, @NotNull Response<Translation> response) {
                //请求处理,输出结果
                if (response.body() != null) {
                    response.body()
                            .show();
                }
                translation = response.body();
                mTextView.setText("状态码(1为成功):" + translation.getStatus() + "\n"
                        + "翻译信息如下\n"
                        + "错误码(0为成功):" + translation.getContent().getErrNo() + "\n"
                        + "源语言:" + translation.getContent().getFrom() + "\n"
                        + "目标语言:" + translation.getContent().getTo() + "\n"
                        + "翻译结果:" + translation.getContent().getOut() + "\n"
                        + "来源平台:" + translation.getContent().getVendor() + "\n"
                        + "英语发音:" + translation.getContent().getPh_en_mp3());
            }

            //请求失败时候的回调
            @Override
            public void onFailure(@NotNull Call<Translation> call, @NotNull Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }
}
