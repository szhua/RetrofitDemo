package szhua.demo.retrofitdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import szhua.demo.retrofitdemo.Model.PhoneResult;
import szhua.demo.retrofitdemo.R;
import szhua.demo.retrofitdemo.Service.DemoService;


/**
 * 使用分为四步:
 * 创建Retrofit对象
 * 创建访问API的请求
 * 发送请求
 * 处理结果
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt;
    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";
    private EditText view;
    private String  num;
    private TextView textView ;

    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);
        view = (EditText) findViewById(R.id.view);
        textView = (TextView) findViewById(R.id.textview);
        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == bt) {
             num = view.getText().toString();
            if(TextUtils.isEmpty(num)||num.length()<11){
                Toast.makeText(this,"输入正确的手机号",Toast.LENGTH_LONG).show();
                return;
            }
            query();
        }

        if(v==bt1){
            Intent intent =new Intent(this,SecondActivity.class);
            startActivity(intent);
        }


    }

    public void query() {
        //stepOne 创建 Retrofit对象 ；
        Retrofit retrofit = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create())//解析方法；
                .baseUrl(BASE_URL).build();

        //stepTwo 创建访问的接口;

        DemoService demoService = retrofit.create(DemoService.class);
        Call<PhoneResult> phoneResultCall  = demoService.getResult(API_KEY, num);

        //stepThree  请求;

        phoneResultCall.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
               //stepFour 处理 ；
                if(response.isSuccessful()){
                PhoneResult re =response.body();
                if(re!=null){
                    textView.setText("城市"+re.getRetData().getCity()+"号码"+re.getRetData().getPhone()+"省"+re.getRetData().getProvince());
                }
                }
            }
            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });



    }
}
