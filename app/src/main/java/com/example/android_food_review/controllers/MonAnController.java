package com.example.android_food_review.controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.android_food_review.helpers.DBHelper;
import com.example.android_food_review.models.MonAn;
import com.example.android_food_review.models.Quan;

import java.util.ArrayList;
import java.util.List;

public class MonAnController {
    public static final String TAG = "MONAN-CONTROLLER";
    private DBHelper mHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private String[] mAllColumnsInMonAn = {
            DBHelper.MONAN_ID,
            DBHelper.MONAN_TENMONAN,
            DBHelper.MONAN_DIACHI,
            DBHelper.MONAN_QUAN,
            DBHelper.MONAN_GIA,
            DBHelper.MONAN_IMAGE
    };

    public void openDatabase() throws SQLException {
        mDatabase = mHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        mDatabase.close();
    }

    public MonAnController(Context context){
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

    public List<MonAn> getAllMonAn(){
        List<MonAn> monAnList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBHelper.TABLE_MONAN,null);
        while(cursor.moveToFirst()){
            int monAnId = cursor.getInt(0);
            String tenMonAn = cursor.getString(1);
            String diaChi = cursor.getString(2);
            Integer gia = cursor.getInt(3);
            byte[] image = cursor.getBlob(4);
            Integer maQuan =cursor.getInt(5);
            MonAn temp = new MonAn(monAnId,tenMonAn,diaChi,maQuan,gia,image);
            monAnList.add(temp);
        }

        cursor.close();
        return monAnList;
    }

    public Cursor getData(String sql){
        return mDatabase.rawQuery(sql,null);
    }

    public void insertMonAn(MonAn monAn) {
        String sqlQuery ="INSERT INTO "
                + DBHelper.TABLE_MONAN  +"("
                + DBHelper.MONAN_TENMONAN + ","
                + DBHelper.MONAN_DIACHI +","
                + DBHelper.MONAN_GIA + ","
                + DBHelper.MONAN_IMAGE + ","
                + DBHelper.MONAN_QUAN +")" +" VALUES (?,?,?,?,?)";

        SQLiteStatement sqLiteStatement = mDatabase.compileStatement(sqlQuery);

        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,monAn.getTenMonAn());
        sqLiteStatement.bindString(2,monAn.getDiaChi());
        sqLiteStatement.bindLong(3,monAn.getGia());
        sqLiteStatement.bindBlob(4,monAn.getImage());
        sqLiteStatement.bindLong(5,monAn.getMaQuan());

        sqLiteStatement.executeInsert();
    }

}
