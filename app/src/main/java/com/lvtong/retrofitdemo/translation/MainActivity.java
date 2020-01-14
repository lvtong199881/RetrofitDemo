package com.lvtong.retrofitdemo.translation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lvtong.retrofitdemo.R;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 22939
 */
public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;
    private Button mButtonGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.et_translate);
        mTextView = findViewById(R.id.tv_translation);
        mButtonGet = findViewById(R.id.bt_get);
        mButtonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request(mEditText.getText()
                        .toString());
            }
        });
    }

    public void request(String translationString) {

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
                Translation translation = response.body();
                mTextView.setText("状态码(1为成功):" + translation.getStatus() + "\n"
                        + "翻译信息如下\n"
                        + "错误码(0为成功):" + translation.getContent().getErrNo() + "\n"
                        + "源语言:" + translation.getContent().getFrom() + "\n"
                        + "目标语言:" + translation.getContent().getTo() + "\n"
                        + "翻译结果:" + translation.getContent().getOut() + "\n"
                        + "来源平台:" + translation.getContent().getVendor());
            }

            //请求失败时候的回调
            @Override
            public void onFailure(@NotNull Call<Translation> call, @NotNull Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }
}
