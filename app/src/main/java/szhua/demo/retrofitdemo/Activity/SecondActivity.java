package szhua.demo.retrofitdemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import szhua.demo.retrofitdemo.R;
import szhua.demo.retrofitdemo.Service.DemoService;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private Button getbt ;
    private Button postbt ;
    private TextView contentget ;
    private TextView contentpost ;
    private static final String BASE_URL="http://api.duoshuo.com";
    private static final String BASE_URL2 ="http://139.129.51.233/group1/mm_mall/index.php/Webservice/v100/" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getbt= (Button) findViewById(R.id.getbt);
        postbt= (Button) findViewById(R.id.postbt);
        getbt.setOnClickListener(this);
        postbt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==getbt){
                    get();
        }
        if(v==postbt){
             post();
        }
    }

    /**
     * POST
     */
    public void post(){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DemoService demoService =retrofit.create(DemoService.class) ;
        Map<String ,String> params = new HashMap<>() ;
        /**
        key_type
         */
        params.put("keytype", "1");
        Call<Object> objectCall= demoService.getPostMap(params);

        objectCall.enqueue(new  Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Toast.makeText(SecondActivity.this,"okok"+response.isSuccessful(),Toast.LENGTH_LONG).show();
                if(response.isSuccessful()){
                    Toast.makeText(SecondActivity.this,"okok"+response.body().toString(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });



    }


    /**
     * GET
     */
    public void get(){

        //*1
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //*2
        DemoService demoService =retrofit.create(DemoService.class) ;
        Call<Object> objectCall = demoService.getObject("official","4ff1cbc43ae636b72a00001d") ;
        objectCall.enqueue(new Callback<Object>() {
           @Override
           public void onResponse(Call<Object> call, Response<Object> response) {
               /**
                * 在这里我们处理了response没有转化为实体类的情况
                * 这样的话自定义的程度会高一些，可以进行自行定义；//例如运用其他的解析工具等等;
                * todo  6/25
                * to Resolve the reslut ；
                */
               // the best is ;
               // if(response.isSucceful()){******}

               Log.i("okokok","\nmessage\n"+response.message().toString()+"\nsuccessful\n"+response.isSuccessful()+"\nbody\n"+response.body().toString());
               Toast.makeText(SecondActivity.this,"okok"+response.body().toString(),Toast.LENGTH_LONG).show();
           }
           @Override
           public void onFailure(Call<Object> call, Throwable t) {
               //if no connect ====Nothing;
               Log.i("okokok","throwable"+t.getMessage()+"cause"+t.getCause().getMessage());
           }
       });



    }
}
