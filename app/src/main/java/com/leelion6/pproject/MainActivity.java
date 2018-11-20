package com.leelion6.pproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leelion6.pproject.Game2048.GameAcitvity;
import com.leelion6.pproject.HandlerTest.HandlerActivity;
import com.leelion6.pproject.MvpTest.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_mvp_test)
    Button btnMvpTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_mvp_test, R.id.btn_game_2048, R.id.btn_handler_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_mvp_test:
                startActivity(new Intent(this,MvpActivity.class));
                break;
            case R.id.btn_game_2048:
                startActivity(new Intent(this,GameAcitvity.class));
                break;
            case R.id.btn_handler_test:
                startActivity(new Intent(this, HandlerActivity.class));
        }
    }
}
