package com.example.appquanlidiem;

import static com.example.appquanlidiem.ChuyenDoiDiem_DiemDuKien.ChuyenDoiDiemHe4;
import static com.example.appquanlidiem.ChuyenDoiDiem_DiemDuKien.Diemhe10SangDiemHe4;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.appquanlidiem.duc_dao_HP.LopDao;
import java.util.ArrayList;
import java.util.List;

public class DiemCaiThienMainActivity extends AppCompatActivity implements ChoiceResult.SingleChoiceListenner{

    List<String> dsLop = new ArrayList<>();

    ArrayList<String> danhsachdiem = new ArrayList<>();
    ArrayList<String> tongtinchi = new ArrayList<>();
    ArrayList<String> danhsachtinchi1 = new ArrayList<>();

    LopDao lopDao;
    TextView chonketqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuyen_activity_diemcaithien);

        Button btn_diemdukien = findViewById(R.id.btn_diemdukien);
        Button btn_nhap = findViewById(R.id.btn_nhap);
        Spinner spchonmonhoc = findViewById(R.id.spchonmon);
        chonketqua = findViewById(R.id.chonketqua);
        TextView tbhocki = findViewById(R.id.mt_tbmon);

        btn_nhap.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                long tenlop = spchonmonhoc.getSelectedItemId();
                String diemspchon = spchonmonhoc.getSelectedItem().toString();
                float diem = 0.0F;
                diem = ChuyenDoiDiemHe4(diemspchon);
                tbhocki.setText(Long.toString(tenlop));

                float sum = 0.0F;
                float trungbinh;
                int tongtinchi1 = 0;

                float diem10;
                float diem4;

                lopDao = new LopDao(DiemCaiThienMainActivity.this);

                danhsachdiem = lopDao.getDiem();
                danhsachtinchi1 = lopDao.getdsTinChi();
                tongtinchi = lopDao.getTBTinChi();

                for (int i1 = 0; i1 < tongtinchi.size(); i1++) {
                    tongtinchi1 = Integer.parseInt(tongtinchi.get(i1));
                }

                for (int i = 0; i < danhsachdiem.size(); i++) {
                    int tc;
                    if(i==tenlop){
                        diem4 = diem;
                    }else {
                        diem10 = Float.parseFloat(danhsachdiem.get(i));
                        diem4 = Diemhe10SangDiemHe4(diem10);
                    }
                    tc = Integer.parseInt(danhsachtinchi1.get(i));
                    sum = sum + diem4*tc;
                }

                trungbinh = sum / tongtinchi1;
                @SuppressLint("DefaultLocale") String temp = String.format("%.2f",trungbinh);
                tbhocki.setText(temp);
            }
        });
        lopDao = new LopDao(DiemCaiThienMainActivity.this);
        dsLop = lopDao.getDanhSachLop();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, dsLop);
        spchonmonhoc.setAdapter(arrayAdapter);

        btn_diemdukien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiemCaiThienMainActivity.this, DiemDuKienMainActivity.class);
                startActivity(intent);
            }
        });
        chonketqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment choiDialogFragment = new ChoiceResult();
                choiDialogFragment.setCancelable(false);
                choiDialogFragment.show(getSupportFragmentManager(), "");
            }
        });

    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onPostiveButtonClicked(String[] list, int position) {
        chonketqua.setText(" " +list[position]);
    }

    @Override
    public void oNegativeButtonClicked() {
        chonketqua.setText("");

    }

}