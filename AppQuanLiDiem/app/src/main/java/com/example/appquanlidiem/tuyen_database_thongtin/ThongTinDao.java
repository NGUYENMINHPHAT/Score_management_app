package com.example.appquanlidiem.tuyen_database_thongtin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ThongTinDao {
    private final SQLiteDatabase db;

    public ThongTinDao(Context context) {
        DbHelper helper = new DbHelper(context);
        this.db = helper.getWritableDatabase();
    }
    public long insert(ThongTin tt) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenNguoiDung", tt.getTenNguoiDung());
        contentValues.put("tenHocKi", tt.getTenHocKi());
        return db.insert("ThongTin", null, contentValues);

        }
    public void update(ThongTin tt) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenNguoiDung", tt.getTenNguoiDung());
        contentValues.put("tenHocKi", tt.getTenHocKi());
        db.update("ThongTin", contentValues, "tenNguoiDung=?", new String[]{tt.getTenNguoiDung()});

    }
    public ArrayList<ThongTin> getAll(){
        ArrayList<ThongTin> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT * FROM ThongTin", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String tenNguoiDung = cs.getString(0);
            String tenHocKi = cs.getString(1);
            ThongTin thongTin = new ThongTin(tenNguoiDung, tenHocKi);
            list.add(thongTin);
            cs.moveToNext();
        }
        cs.close();
        return list;

    }
    public ArrayList<String> getTen() {
        ArrayList<String> lsList = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT tenNguoiDung FROM ThongTin", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            lsList.add(cs.getString(0));
            cs.moveToNext();
        }
        cs.close();
        return lsList;
    }
}
