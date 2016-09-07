package szhua.demo.retrofitdemo.RxJava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import szhua.demo.retrofitdemo.R;

/**
 * Created by szhua on 2016/6/25.
 */
public class RxJavaActivity extends AppCompatActivity {
    private String tag = "MMM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_activity);
        //简单的模式； 创建观察者 ；
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(tag, "onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(tag, "OnError");
            }
            @Override
            public void onNext(String s) {
                Log.d(tag, "onNext");
                Log.d(tag, "String" + s);
            }
        };
         //创建Observer的抽象类 订阅者 即当事件触发的时候就会得到信息；
        Subscriber<String> subscriber =new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(tag, "onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(tag, "OnError");
            }
            @Override
            public void onNext(String s) {
                Log.d(tag, "onNext");
                Log.d(tag, "String" + s);
            }
            @Override
            public void onStart() {
                Log.d(tag, "Onstart");
            }

        } ;

        //取消订阅，释放内存 ； onPause onStop 调用
        //if(subscriber.isUnsubscribed())
        //subscriber.unsubscribe();

        Observable<String> observable =Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
        //进行观察；
        observable.subscribe(subscriber);

        /**
         * 以下是打印出来的内容
         * todo log
         * 06-25 14:32:48.108 30633-30633/? D/MMM: Onstart
         06-25 14:32:48.109 30633-30633/? D/MMM: onNext
         06-25 14:32:48.109 30633-30633/? D/MMM: StringHello
         06-25 14:32:48.109 30633-30633/? D/MMM: onNext
         06-25 14:32:48.109 30633-30633/? D/MMM: StringHi
         06-25 14:32:48.110 30633-30633/? D/MMM: onNext
         06-25 14:32:48.110 30633-30633/? D/MMM: StringAloha
         06-25 14:32:48.110 30633-30633/? D/MMM: onCompleted
         */


    }
}
