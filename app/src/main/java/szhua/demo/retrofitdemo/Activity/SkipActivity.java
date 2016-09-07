package szhua.demo.retrofitdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import szhua.demo.retrofitdemo.R;
import szhua.demo.retrofitdemo.RxJava.RxJavaActivity;

public class SkipActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt;
    private Button bt1;
    private Button bt2;
    private Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip);
        bt = (Button) findViewById(R.id.bt);
        bt1 = (Button) findViewById(R.id.bt1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SkipActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SkipActivity.this, RxJavaActivity.class);
                startActivity(intent);
            }
        });

        bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
        bt3 = (Button) findViewById(R.id.bt3);
        bt3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == bt2) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        if(v==bt3){
            Intent intent =new Intent(this,LoginActivity2.class) ;
            startActivity(intent);
        }
    }
}
