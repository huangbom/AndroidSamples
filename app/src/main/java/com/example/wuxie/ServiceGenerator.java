package com.example.wuxie;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huangyaoshi on 2017/5/11.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "https://api.github.com/";

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static Interceptor heads = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("User-Agent", "android")
//                            .header("Accept-Encoding", "gzip")
//                            .header("Accept", "application/vnd.yourapi.v1.full+json")
                    .header("Accept", "application/json")
                    .header("Connection", "close")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }
    };

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(logging)
            .addInterceptor(heads)
            ;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            ;

    private static Retrofit retrofit = builder.build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}