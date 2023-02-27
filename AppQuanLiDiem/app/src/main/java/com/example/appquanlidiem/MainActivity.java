package com.example.appquanlidiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuyen_activity_main);

        CardView cv2 = findViewById(R.id.d2);
        CardView cv3 = findViewById(R.id.d3);
        CardView cv4 = findViewById(R.id.d4);
        CardView cv6 = findViewById(R.id.d6);
//        ListView hienthiten = findViewById(R.id.hienthiten_hk);
        TextView thongtin = findViewById(R.id.thongtinnguoidung);

        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
        cv4.setOnClickListener(this);
        cv6.setOnClickListener(this);
        thongtin.setOnClickListener(this);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.d2 : i = new Intent(this,DanhSachHocPhanActivity.class); startActivity(i); break;
            case R.id.d3 : i = new Intent(this,DiemDuKienMainActivity.class); startActivity(i); break;
            case R.id.d4 : i = new Intent(this,ThoiKhoaBieuMainActivity.class); startActivity(i); break;
            case R.id.d6 : i = new Intent(this,ThongKeMainActivity.class); startActivity(i); break;
            case R.id.thongtinnguoidung:ThongTinMainActivity intent = new ThongTinMainActivity(this); intent.show(); break;

        }
    }
}