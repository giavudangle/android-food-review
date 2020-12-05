package com.example.android_food_review.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.android_food_review.models.Quan;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "review_app.db";
    public static final Integer DATABASE_VERSION= 1;
    public static Context scopeContext;

    /* ==================== QUẬN DEFINE CONST ============== */
    public static final String TABLE_QUAN = "QUAN";
    public static final String QUAN_ID = "_id";
    public static final String QUAN_TENQUAN = "ten_quan";
    public static final String QUAN_SOMON = "so_mon";
    public static final String createTableQuan = "CREATE TABLE " + TABLE_QUAN +" ("
            +  QUAN_ID + " integer primary key autoincrement ,"
            + QUAN_TENQUAN + " TEXT not null, "
            + QUAN_SOMON + " DECIMAL DEFAULT (0)" +")";
    public static final String dropTableQuan = "DROP TABLE IF EXISTS " + TABLE_QUAN;
    /*=================================================*/

    /* =================== MÓN ĂN DEFINE CONST ============= */
    public static final String TABLE_MONAN = "MONAN";
    public static final String MONAN_ID = "_id";
    public static final String MONAN_TENMONAN = "ten_mon";
    public static final String MONAN_DIACHI = "dia_chi";
    public static final String MONAN_GIA = "gia";
    public static final String MONAN_IMAGE = "hinh";
    public static final String MONAN_QUAN = "quan"; // khoá ngoại tham chiếu đếnn bảng

    public static final String createTableMonAn ="CREATE TABLE " + TABLE_MONAN + " ("
            + MONAN_ID + " integer primary key autoincrement ,"
            + MONAN_TENMONAN + " TEXT not null, "
            + MONAN_DIACHI + " TEXT not null, "
            + MONAN_GIA + " INTEGER not null, "
            + MONAN_IMAGE +" BLOB, "
            + MONAN_QUAN + " INTEGER not null, "
            + "FOREIGN KEY " + "(" + MONAN_QUAN + ")"
            + " REFERENCES " +  TABLE_QUAN + "(" + QUAN_ID +")"
            + ")";

    public static final String dropTableMonAn = "DROP TABLE IF EXISTS " + TABLE_MONAN;
    /*=================================================*/

    /* =================== REVIEW DEFINE CONST ============= */
    public static final String TABLE_REVIEW = "REVIEW";
    public static final String REVIEW_ID = "_id";
    public static final String REVIEW_TENNGUOIREVIEW= "ten_nguoi_review";
    public static final String REVIEW_NOIDUNG = "noi_dung_review";
    public static final String REVIEW_MONAN ="mon_an" ; //// khoá ngoại tham chiếu đếnn bảng MONAN
    public static final String REVIEW_IMAGE= "image";


    public static final String createTableReview ="CREATE TABLE " + TABLE_REVIEW + " ("
            + REVIEW_ID + " integer primary key autoincrement ,"
            + REVIEW_TENNGUOIREVIEW + " TEXT not null, "
            + REVIEW_NOIDUNG + " TEXT not null, "
            + REVIEW_IMAGE +" BLOB, "
            + REVIEW_MONAN + " INTEGER not null, "
            + "FOREIGN KEY " + "(" + REVIEW_MONAN + ")"
            + " REFERENCES " +  TABLE_MONAN + "(" + MONAN_ID +")"
            + ")";

    public static final String dropTableReview = "DROP TABLE IF EXISTS " + TABLE_REVIEW;
    /*=================================================*/



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        scopeContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(scopeContext,"Created Table QUAN",Toast.LENGTH_SHORT).show();
        Toast.makeText(scopeContext,"Created Table MONAN",Toast.LENGTH_SHORT).show();
        Toast.makeText(scopeContext,"Created Table REVIEW",Toast.LENGTH_SHORT).show();

        db.execSQL(createTableMonAn);
        db.execSQL(createTableQuan);
        db.execSQL(createTableReview);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Xoá bảng cũ
        db.execSQL(dropTableMonAn);
        db.execSQL(dropTableQuan);
        db.execSQL(dropTableReview);
        //Tiến hành tạo bảng mới
        onCreate(db);
    }


}
