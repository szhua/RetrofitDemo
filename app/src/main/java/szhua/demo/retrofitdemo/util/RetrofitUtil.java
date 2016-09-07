package szhua.demo.retrofitdemo.util;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import szhua.demo.retrofitdemo.Http.AddCookiesInterceptor;
import szhua.demo.retrofitdemo.Http.CookiesManager;
import szhua.demo.retrofitdemo.Http.LogInterceptor;
import szhua.demo.retrofitdemo.Http.PersistentCookieStore;
import szhua.demo.retrofitdemo.Http.ReceivedCookiesInterceptor;
import szhua.demo.retrofitdemo.Service.DemoService;

/**
 * Created by szhua on 2016/6/27.
 */
public class RetrofitUtil {
    public static final String BASE_URL = "http://139.129.51.233/group1/mm_mall/index.php/Webservice/v100/";

    public static Retrofit getInstance(Context context) {
        LogInterceptor interceptor = new LogInterceptor();
        interceptor.setLevel(LogInterceptor.Level.BODY);
        //OkhttpClient进行一些必要的配置；
        OkHttpClient client = new OkHttpClient.Builder().retryOnConnectionFailure(true).addInterceptor(interceptor).cookieJar(new CookiesManager(context)).connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static void main(String [] args){

    }public class Hello{}
}
