package com.example.ktratrenlop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ktratrenlop.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CongViec.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table congviec(" +
                "id integer primary key autoincrement, " +
                "ten text, " +
                "noidung text, " +
                "ngay text, " +
                "tinhtrang text, " +
                "congtac text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    //get all order by ngay
    public List<CongViec> getAll(){
        List<CongViec> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "ngay DESC";
        Cursor rs = st.query("congviec", null, null, null, null, null, order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String nd = rs.getString(2);
            String ngay = rs.getString(3);
            String tt = rs.getString(4);
            String ct = rs.getString(5);
            list.add(new CongViec(id, ten, nd, ngay, tt.equalsIgnoreCase("1")?true:false, ct.equalsIgnoreCase("1")?true:false));
        }

        return list;
    }
    public long addCv(CongViec cv){
        ContentValues values = new ContentValues();
        values.put("ten", cv.getTen());
        values.put("noidung", cv.getNoidung());
        values.put("ngay", cv.getNgay());
        values.put("tinhtrang", cv.isTinhtrang()?"1":"0");
        values.put("congtac", cv.isCongtac()?"1":"0");
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("congviec", null, values);
    }

    //update
    public int update(CongViec cv){
        ContentValues values = new ContentValues();
        values.put("ten", cv.getTen());
        values.put("noidung", cv.getNoidung());
        values.put("ngay", cv.getNgay());
        values.put("tinhtrang", cv.isTinhtrang()?"1":"0");
        values.put("congtac", cv.isCongtac()?"1":"0");
        SQLiteDatabase st = getWritableDatabase();
        String where = "id=?";
        String[] args = {Integer.toString(cv.getId())};
        return st.update("congviec", values, where, args);
    }

    //delete
    public int delete(int id){
        String where = "id=?";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        return st.delete("congviec", where, args);
    }

    public List<CongViec> searchCvByTen(String s){
        List<CongViec> list = new ArrayList<>();
        String where = "ten like ?";
        String[] args = {"%"+s+"%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("congviec", null, where, args, null, null, null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String nd = rs.getString(2);
            String ngay = rs.getString(3);
            String tt = rs.getString(4);
            String ct = rs.getString(5);
            list.add(new CongViec(id, ten, nd, ngay, tt.equalsIgnoreCase("1")?true:false, ct.equalsIgnoreCase("1")?true:false));
        }

        return list;
    }
    public List<CongViec> searchCvByNgay(String from, String to){
        List<CongViec> list = new ArrayList<>();
        String where = "ngay between ? and ?";
        String[] args = {from, to};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("congviec", null, where, args, null, null, null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String nd = rs.getString(2);
            String ngay = rs.getString(3);
            String tt = rs.getString(4);
            String ct = rs.getString(5);
            list.add(new CongViec(id, ten, nd, ngay, tt.equalsIgnoreCase("1")?true:false, ct.equalsIgnoreCase("1")?true:false));
        }

        return list;
    }
}
