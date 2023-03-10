package com.example.appquanlidiem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.EditText;
import com.example.appquanlidiem.phat_bieudo_database.database;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.text.SimpleDateFormat;


public class ThongKeMainActivity extends AppCompatActivity {

    EditText kttbc;
    private BarChart barChart;
    private Button button;
    private EditText editText;
    private database db;
    long date = System.currentTimeMillis();
    Spinner dsbieudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phat_activity_thongke);


        kttbc = (EditText) findViewById(R.id.mp_kqtbc);

        // Barchart

        barChart = (BarChart) findViewById(R.id.barchart);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);

        addDataToGraph();
        barChart.invalidate();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mdiem1;
                mdiem1 = editText.getText().toString();
                Double d1,  tb2;
                String m1 = "XH024, XH025, CT205";
                String m2 = "CT173";
                String m3 = "CT190";
                String m4 = "CT188, CT177, CT291";
                String m5 = "CT263, CT175 ";
                String m6 = "TN001";
                String m7 = "TN002, CT281";
                String m8 = "CT274, CT252";
                d1 = Double.parseDouble(mdiem1);
                tb2= d1;
                if(tb2 < 4) {
                    kttbc.setText(tb2 + " ( F ) : " + m1);
                }else if ( tb2 >= 4.0 && tb2 <= 4.9) {
                    kttbc.setText(tb2 + " ( D ) : " + m2);
                }else if ( tb2 >= 5.0 && tb2 <= 5.4) {
                    kttbc.setText(tb2 + " ( D+ ) : " + m3);
                }else if (tb2 >= 5.5 && tb2 <= 6.4){
                    kttbc.setText(tb2 + " ( C ) : " + m4);
                }else if (tb2 >= 6.5 && tb2 <= 6.9){
                    kttbc.setText(tb2 + " ( C+ ) : " + m5);
                }else if (tb2 >= 7.0 && tb2 <= 7.9){
                    kttbc.setText(tb2 + " ( B ) : " + m6);
                }else if (tb2 >= 8.0 && tb2 <= 8.9){
                    kttbc.setText(tb2 + " ( B+ ) : " + m7);
                }else if (tb2 >= 9.0 && tb2 <= 10.0){
                    kttbc.setText(tb2 + " ( A ) : " + m8);
                }

                // Barchart
                SaveToDatabase();
            }
        });

        dsbieudo = (Spinner) findViewById(R.id.mp_spinner);
        final ArrayList<String> arrayHocKi = new ArrayList<String>();
        arrayHocKi.add("H???c k?? 1 (N??m 1)");
        arrayHocKi.add("H???c k?? 2 (N??m 1)");
        arrayHocKi.add("H???c k?? 3 (N??m 1)");
        arrayHocKi.add("H???c k?? 1 (N??m 2)");
        arrayHocKi.add("H???c k?? 2 (N??m 2)");
        arrayHocKi.add("H???c k?? 3 (N??m 2)");
        arrayHocKi.add("H???c k?? 1 (N??m 3)");
        arrayHocKi.add("H???c k?? 2 (N??m 3)");
        arrayHocKi.add("H???c k?? 3 (N??m 3)");
        arrayHocKi.add("H???c k?? 1 (N??m 4)");
        arrayHocKi.add("H???c k?? 2 (N??m 4)");
        arrayHocKi.add("H???c k?? 3 (N??m 4)");
        arrayHocKi.add("H???c k?? 1 (N??m 5)");
        arrayHocKi.add("H???c k?? 2 (N??m 5)");
        arrayHocKi.add("H???c k?? 3 (N??m 5)");


        ArrayAdapter arrAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayHocKi);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dsbieudo.setAdapter(arrAdapter);
        dsbieudo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ThongKeMainActivity.this, arrayHocKi.get(i), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void SaveToDatabase(){
        db = new database(this);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
        String xvalue = sdf.format(date);
        String yvalue = editText.getText().toString();

        db.saveData(xvalue,yvalue);
        addDataToGraph();
        barChart.invalidate();

        db.close();
    }

    public void addDataToGraph(){
        db = new database(this);
        final ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        final ArrayList<String> ydata = db.queryYData();


        for(int i = 0; i < db.queryYData().size(); i++){
            BarEntry newBarEntry = new BarEntry(i, Float.parseFloat(db.queryYData().get(i)));
            yVals.add(newBarEntry);
        }

        final ArrayList<String> xVals = new ArrayList<String>();
        final ArrayList<String> xdata = db.queryXData();

        for (int i = 0; i < db.queryXData().size(); i++){
            xVals.add(xdata.get(i));

            yVals.add(new BarEntry(5, 8));
            yVals.add(new BarEntry(6, 10));
        }

        BarDataSet dataSet = new BarDataSet(yVals, "?????nh danh");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(16f);
        //  BarData barData = new BarData(dataSet);
        barChart.setFitBars(true);
        //barChart.setData(barData);
        barChart.getDescription().setText("NMP");
        ArrayList<IBarDataSet> dataSets1 = new ArrayList<>();
        dataSets1.add(dataSet);
        BarData data = new BarData(dataSets1);
        //   barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
        barChart.setData(data);
        barChart.animateY(3000);
        //    XAxis xAxis = barChart.getXAxis();
        //   xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // xAxis.setAvoidFirstLastClipping(true);
        // xAxis.setDrawLabels(true);
        //  xAxis.isCenterAxisLabelsEnabled();
        // xAxis.setGranularityEnabled(true);
        //  YAxis righAxis = barChart.getAxisRight();
        // righAxis.setEnabled(false);
        // barChart.setMaxVisibleValueCount(5);
    }
}