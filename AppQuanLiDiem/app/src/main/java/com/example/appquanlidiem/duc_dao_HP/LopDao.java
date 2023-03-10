package com.example.appquanlidiem.duc_dao_HP;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appquanlidiem.duc_database_HP.DBHeplper;
import com.example.appquanlidiem.duc_model_HP.Lop;
import java.util.ArrayList;
import java.util.List;

public class LopDao {
    DBHeplper db;
    Lop lop;

    public LopDao(Context context) {
        db = new DBHeplper(context);

    }

    public ArrayList<Lop> getAll() {
        ArrayList<Lop> lsList = new ArrayList<>();
        SQLiteDatabase dtb = db.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM LOP", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String maLop = cs.getString(0);
            String tenLop = cs.getString(1);
            String tinChi = cs.getString(2);
            String mucTieu = cs.getString(3);
            Lop s = new Lop(maLop, tenLop, tinChi, mucTieu);
            lsList.add(s);
            cs.moveToNext();

        }
        cs.close();
        return lsList;
    }

    public boolean insert(Lop lop) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maLop", lop.getMaLop());
        contentValues.put("tenLop", lop.getTenLop());
        contentValues.put("tinChi", lop.getTinChi());
        contentValues.put("mucTieu", lop.getMucTieu());
        long r = sqLiteDatabase.insert("LOP", null, contentValues);

        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean update(Lop lop) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("maLop", lop.getMaLop());
        contentValues.put("tenLop", lop.getTenLop());
        contentValues.put("tinChi", lop.getTinChi());
        contentValues.put("mucTieu", lop.getMucTieu());
        long r = sqLiteDatabase.update("LOP", contentValues, "maLop=?", new String[]{lop.getMaLop()});

        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean delete(String malop) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.delete("SINHVIEN", "maLop=?", new String[]{malop});
        int r = sqLiteDatabase.delete("LOP", "maLop=?", new String[]{malop});
        if (r <= 0) {
            return false;
        }
        return true;
    }

//    Spinner Danh S??ch M??n H???c
    public List<String> getDanhSachLop() {
        List<String> lsList = new ArrayList<>();
        SQLiteDatabase dtb = db.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT tenLop FROM LOP", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            lsList.add(cs.getString(0));
            cs.moveToNext();
        }
        cs.close();
        return lsList;
    }

//    L???y T??n Ch??? m??n c???p nh???t g???n nh???t
    public List<String> getTinChi() {
        List<String> lsList = new ArrayList<>();
        SQLiteDatabase dtb = db.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT tinChi FROM LOP", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            lsList.add(cs.getString(0));
            cs.moveToNext();
        }
        cs.close();
        return lsList;
    }
    // L???y s??? ??i???m ???????c c???p nh???t g???n ????y
        public ArrayList<String> getDiem() {
            ArrayList<String> lsList = new ArrayList<>();
            SQLiteDatabase dtb = db.getReadableDatabase();
            Cursor cs = dtb.rawQuery("SELECT mucTieu FROM LOP", null);
            cs.moveToFirst();
            while (!cs.isAfterLast()) {
                lsList.add(cs.getString(0));
                cs.moveToNext();
            }
            cs.close();
            return lsList;
        }
    // T???ng t??n ch???
        public ArrayList<String> getTBTinChi() {
        ArrayList<String> lsList = new ArrayList<>();
        SQLiteDatabase dtb = db.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT SUM(tinChi) FROM LOP", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            lsList.add(cs.getString(0));
            cs.moveToNext();
        }
        cs.close();
        return lsList;
    }

    //  L???y t???ng t??n ch???
    public ArrayList<String> getdsTinChi() {
        ArrayList<String> lsList = new ArrayList<>();
        SQLiteDatabase dtb = db.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT tinChi FROM LOP", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            lsList.add(cs.getString(0));
            cs.moveToNext();
        }
        cs.close();
        return lsList;
    }

}


