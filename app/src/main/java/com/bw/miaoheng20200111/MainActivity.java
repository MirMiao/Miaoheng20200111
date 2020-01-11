package com.bw.miaoheng20200111;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bw.miaoheng20200111.api.Api;
import com.bw.miaoheng20200111.api.ClsApiService;
import com.bw.miaoheng20200111.entity.ClsEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("CheckResult")
    private void initData() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        final Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    retrofit.create(ClsApiService.class)
            .getData("13387","157870372510213387","0","1","5")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ClsEntity>() {
                /**
                 * 成功
                 * @param clsEntity
                 * @throws Exception
                 */
                @Override
                public void accept(ClsEntity clsEntity) throws Exception {
                    Toast.makeText(MainActivity.this, ""+clsEntity.getMessage(), Toast.LENGTH_SHORT).show();
                    ClsAdapter clsAdapter = new ClsAdapter(MainActivity.this, clsEntity.getOrderList());
                    recyclerView.setAdapter(clsAdapter);

                }
            }, new Consumer<Throwable>() {
                /**
                 * 失败
                 * @param throwable
                 * @throws Exception
                 */
                @Override
                public void accept(Throwable throwable) throws Exception {

                }
            });
    }
}
