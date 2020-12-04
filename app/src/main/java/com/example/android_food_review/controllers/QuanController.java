package com.example.android_food_review.controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android_food_review.helpers.DBHelper;
import com.example.android_food_review.models.Quan;

import java.util.ArrayList;
import java.util.List;

public class QuanController {
    public static final String TAG = "QUAN-CONTROLLER";
    private DBHelper mHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private String[] mAllColumnsInQuan = {
            DBHelper.QUAN_ID,
            DBHelper.QUAN_TENQUAN,
            DBHelper.QUAN_SOMON
    };

    public void openDatabase() throws SQLException {
        mDatabase = mHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        mDatabase.close();
    }

    public QuanController(Context context){
        this.mContext = context;
        mHelper  = new DBHelper(context);
        // Mở kết nối đến Database thông qua đối tượng mH
        try {
            openDatabase();
        } catch (SQLException e){
            Log.e(TAG,"SQL Exception on openning the database " + e.getMessage());
            e.printStackTrace();
        }
    }



    public List<Quan> getAllQuan() {
        List<Quan> quans = new ArrayList<>();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_QUAN,mAllColumnsInQuan,null,null,null,null,DBHelper.QUAN_ID + " ASC");

        if(cursor != null){
            //Đến dòng đầu của tập dữ liệu
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int quanID = cursor.getInt(0);
                String tenQuan = cursor.getString(1);
                int soMon = cursor.getInt(2);
                quans.add(new Quan(quanID, tenQuan, soMon));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return quans;
    }


    public List<String> getTenQuan() {
        List<String> tenQuans = new ArrayList<>();

        Cursor cursor = mDatabase.rawQuery("SELECT " + DBHelper.QUAN_TENQUAN + " from " + DBHelper.TABLE_QUAN, null);

        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tenQuan = cursor.getString(0);
            tenQuans.add(tenQuan);
            cursor.moveToNext();
        }

        cursor.close();

        return tenQuans;
    }

    //Lấy một SP biết ID
    public Quan getQuanById(int ID) {
        Quan quan = null;

        Cursor cursor = mDatabase.rawQuery("SELECT * from " + DBHelper.TABLE_QUAN + " where id = ?",
                new String[]{ID + ""});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int quanID = cursor.getInt(0);
            String tenQuan = cursor.getString(1);
            int soMon = cursor.getInt(2);
            quan = new Quan(quanID, tenQuan, soMon);
        }
        cursor.close();
        return quan;
    }

    public Integer getMaQuanByTenQuan(String tenQuan){
        Integer maQuan = 0;

        Cursor cursor = mDatabase.rawQuery("SELECT " + DBHelper.QUAN_ID + " from " + DBHelper.TABLE_QUAN + " where " +  DBHelper.QUAN_TENQUAN + " = ?",new String[] {tenQuan +""});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            maQuan = cursor.getInt(0);
        }
        cursor.close();
        return maQuan;
    }

    //Cập nhật
    public void updateQuan(Quan quan) {
        mDatabase.execSQL("UPDATE " + DBHelper.TABLE_QUAN +" SET tenQuan=?, soLuongMon = ? where id = ?",
                new String[]{quan.tenQuan, quan.soLuongMon + "", quan._id + ""});
    }

    //Chèn mới
    public void insertQuan(Quan quan) {
        mDatabase.execSQL("INSERT INTO " + DBHelper.TABLE_QUAN  +"(" + DBHelper.QUAN_TENQUAN + ","+ DBHelper.QUAN_SOMON +")" +" VALUES (?,?)",
                new String[]{quan.tenQuan, quan.soLuongMon + ""});
    }

    //Xoá sản phẩm khỏi DB
    public void deleteQuan(int quanID) {
        mDatabase.execSQL("DELETE FROM " + DBHelper.TABLE_QUAN + " where id = ?", new String[]{String.valueOf(quanID)});
    }

}
