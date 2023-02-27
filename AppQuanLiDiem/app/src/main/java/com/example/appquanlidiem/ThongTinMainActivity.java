package com.example.appquanlidiem;

import androidx.annotation.NonNull;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.appquanlidiem.tuyen_database_thongtin.ThongTin;
import com.example.appquanlidiem.tuyen_database_thongtin.ThongTinDao;

public class ThongTinMainActivity extends Dialog implements View.OnClickListener
{
    Context context;
    EditText tenguoidung;
    EditText hocki;

    public ThongTinMainActivity(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuyen_activity_thongtin);

        tenguoidung =  findViewById(R.id.tennguoidung);
        hocki =  findViewById(R.id.hocki);

        findViewById(R.id.btn_luu).setOnClickListener(this);
        findViewById(R.id.btn_quaylai).setOnClickListener(this);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_luu:
                ThongTin thongTin = new ThongTin();
                ThongTinDao dao = new ThongTinDao(context);
                thongTin.setTenNguoiDung(tenguoidung.getText().toString());
                thongTin.setTenHocKi(hocki.getText().toString());
                dao.update(thongTin);
                Toast.makeText(context, "Thông tin đã được lưu", Toast.LENGTH_LONG).show();
                dismiss();
                break;
            case R.id.btn_quaylai:
                dismiss();
                break;

        }

    }


}