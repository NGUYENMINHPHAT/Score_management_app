package com.example.appquanlidiem;
import static com.example.appquanlidiem.ChuyenDoiDiem_DiemDuKien.ChuyenDoiDiemChu;
import static com.example.appquanlidiem.ChuyenDoiDiem_DiemDuKien.Diemhe10SangDiemHe4;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.appquanlidiem.duc_dao_HP.LopDao;
import java.util.ArrayList;
import java.util.List;


public class DiemDuKienMainActivity extends AppCompatActivity {
    List<String> dsLop = new ArrayList<>();
    List<String> tinchi = new ArrayList<>();

    ArrayList<String> danhsachdiem = new ArrayList<>();
    ArrayList<String> tongtinchi = new ArrayList<>();
    ArrayList<String> danhsachtinchi = new ArrayList<>();

    LopDao lopDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuyen_activity_diemdukien);

        Button diemcaithien =  findViewById(R.id.btn_diemcaithien);

        Spinner spchonmonhoc = findViewById(R.id.spchonmon);
        Spinner sptinchi = findViewById(R.id.sptinchi);
        EditText sodiemnhapvao = findViewById(R.id.sodiemdukien);

        Button nhap = findViewById(R.id.btn_nhap);
        TextView diemchu = findViewById(R.id.diemchu);
        TextView tbhocki = findViewById(R.id.trungbinhhocki);

        nhap.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                float diem4_tinchi = 0.0F;
                float trungbinh ;
                int tongtinchi1 = 0;
                float diem10 ;
                float diem4 ;

               String chuoisdnv = sodiemnhapvao.getText().toString();
               float chuyendiemsangsothuc = Float.parseFloat(chuoisdnv);
                String diem = ChuyenDoiDiemChu(chuyendiemsangsothuc);
                diemchu.setText(String.format("%.1f %s",chuyendiemsangsothuc, diem));

                lopDao = new LopDao(DiemDuKienMainActivity.this);

                danhsachdiem = lopDao.getDiem();
                danhsachtinchi = lopDao.getdsTinChi();
                tongtinchi = lopDao.getTBTinChi();

                for (int i = 0; i < tongtinchi.size(); i++) {
                    tongtinchi1 = Integer.parseInt(tongtinchi.get(i));

                }
                for (int i = 0; i < danhsachdiem.size(); i++) {
                    int tc;
                    diem10 =  Float.parseFloat(danhsachdiem.get(i));
                    diem4 = Diemhe10SangDiemHe4(diem10);
                    tc = Integer.parseInt(danhsachtinchi.get(i));
                    diem4_tinchi = diem4_tinchi + diem4*tc;
                }
                trungbinh = diem4_tinchi / tongtinchi1;
                String temp = String.format("%.2f",trungbinh);
                tbhocki.setText(temp);
            }
        });

        lopDao = new LopDao(DiemDuKienMainActivity.this);
        dsLop = lopDao.getDanhSachLop();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,dsLop);
        spchonmonhoc.setAdapter(arrayAdapter);

        lopDao = new LopDao(DiemDuKienMainActivity.this);
        danhsachdiem = lopDao.getDiem();
        for (int i = 0; i < danhsachdiem.size(); i++) {
            sodiemnhapvao.setText(danhsachdiem.get(i));
        }

        lopDao = new LopDao(DiemDuKienMainActivity.this);
        tinchi = lopDao.getTinChi();
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,tinchi);
        sptinchi.setAdapter(arrayAdapter2);

        diemcaithien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiemDuKienMainActivity.this, DiemCaiThienMainActivity.class);
                startActivity(intent);
            }
        });

    }


}