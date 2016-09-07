package szhua.demo.retrofitdemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import szhua.demo.retrofitdemo.AppHolder;
import szhua.demo.retrofitdemo.Http.JsonUtil;
import szhua.demo.retrofitdemo.Http.ResultUtil;
import szhua.demo.retrofitdemo.R;
import szhua.demo.retrofitdemo.Service.DemoService;
import szhua.demo.retrofitdemo.util.UiUtil;

/**
 * 本示例对请求结果进行适当的处理；
 * todo 封装全局就靠他了；
 */
public class LoginActivity extends AppCompatActivity {

    private static final String BASE_URL ="http://139.129.51.233/group1/mm_mall/index.php/Webservice/v100/" ;
    private EditText etName;
    private EditText  etPass;
    private String  msgInfo;
    private int  error_code;
    private String tag ="MMMM" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      etName = (EditText) findViewById(R.id.name);
         etPass = (EditText) findViewById(R.id.pass);
        Button bt = (Button) findViewById(R.id.login);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String name =etName.getText().toString() ;
               String pass =etPass.getText().toString()  ;

                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pass)){

                    Retrofit retrofit =new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    DemoService demoService =retrofit.create(DemoService.class) ;
                    Map<String ,String> params =new HashMap<>() ;
                    params.put("username",name);
                    params.put("password",pass);
                    params.put("devicetype","2");
                    params.put("lastloginversion","1.0.0");
                    Call<Object> objectCall =demoService.login(params) ;
                    objectCall.enqueue(new Callback <Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                           // UiUtil.showLongToast(LoginActivity.this,"response"+response.isSuccessful());
                            if(response.isSuccessful()){
                                Gson gson =new Gson() ;
                               // gson.toJson(response.body());
                                String infor=gson.toJson(response.body());
                              //  UiUtil.showLongToast(LoginActivity.this, "response" +infor );
                            try {
                                JsonNode node = ResultUtil.handleResult(infor);
                                boolean responseCode = node.findValue("success").asBoolean();
                                //  String responseCode = node.findValue("succeed").asText();
                                if (node.findValue("msg") != null) {
                                    msgInfo = node.findValue("msg").asText();
                                }
                                if (node.findValue("error_code") != null) {
                                    error_code = node.findValue("error_code").asInt();
                                }
                                if (responseCode) {
                                  // UiUtil.showLongToast(LoginActivity.this, "登录成功啦");
                                    String token = node.findValue("token").asText();
                                    AppHolder.getInstance().setToken(token);
                                    UiUtil.showLongToast(LoginActivity.this, "token"+token);
                                } else {
                                    UiUtil.showLongToast(LoginActivity.this,"登录失败啦");
                                }
                            } catch (JsonProcessingException e1) {
                                e1.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }}
                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                        }
                    });


                }

            }
        });


    }
}
