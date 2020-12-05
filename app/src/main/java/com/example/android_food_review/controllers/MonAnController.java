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
        // Mở kết nối đến Database thông qua đối tượng mHelper
        try {
            openDatabase();
        } catch (SQLException e){
            Log.e(TAG,"SQL Exception on openning the database " + e.getMessage());
            e.printStackTrace();
        }
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



    public MonAn getMonById(Integer idParam){

        MonAn monAn = null;

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBHelper.TABLE_MONAN + " WHERE " + DBHelper.MONAN_ID + " =?",new String[]{idParam.toString() +""});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Integer id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String diachi = cursor.getString(2);
            Integer gia = cursor.getInt(3);
            byte[] image = cursor.getBlob(4);
            Integer maQuan = cursor.getInt(5);

             monAn = new MonAn(id,ten,diachi,maQuan,gia,image);
        }
        cursor.close();
        return monAn;

    }

}
