package com.example.android_food_review.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.android_food_review.models.MonAn;
import com.example.android_food_review.models.Quan;

public class MonAnDbManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "review_app_db";
    public static final String TABLE_NAME = "MonAn";
    public static final String TABLE_REF_NAME = "Quan";
    public static final String COL_REF_NAME = "_id";

    public  static final String TAG="MonAnHelper";

    public static Context scopeContext;
    public static final String ID = "_id";
    public static final String TENMONAN = "ten_mon";
    public static final String DIACHI = "dia_chi";
    public static final String GIA = "gia";
    public static final String IMAGE = "hinh";
    public static final String QUAN = "quan";



    public MonAnDbManager( Context context) {
        super(context, DATABASE_NAME, null, 1);
        scopeContext = context;

    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Create table MonAn");
        Toast.makeText(scopeContext,"CreateTable MonAn",Toast.LENGTH_SHORT).show();

        String createTableQuery =   "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TENMONAN + " TEXT NOT NULL, "
                + DIACHI + " TEXT NOT NULL, "
                + GIA + " INTEGER NOT NULL, "
                + IMAGE +" BLOB, "
                + QUAN + " INTEGER NOT NULL, "
                + "FOREIGN KEY " + "(" + QUAN + ")"
                + " REFERENCES " +  TABLE_REF_NAME + "(" + COL_REF_NAME +")"
                + ")";
                db.execSQL(createTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Xoá bảng cũ
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //Tiến hành tạo bảng mới
        onCreate(db);
    }

    //Chèn mới
    public void insertMonAn(MonAn monAn) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME  +"(" + TENMONAN + ","+ DIACHI +","+ GIA + ","+ IMAGE + ","+ QUAN +")" +" VALUES (?,?,?,?,?,?)",
                new String[]{monAn.getTenMonAn(), monAn.getDiaChi(),monAn.getGia().toString(),monAn.getMaQuan() + ""});
    }

}
