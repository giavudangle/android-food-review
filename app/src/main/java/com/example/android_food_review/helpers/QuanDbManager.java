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

public class QuanDbManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "review_app_db";
    public static final String TABLE_NAME = "Quan";
    public  static final String TAG="QuanHelper";

    public static Context scopeContext;
    public static final String ID = "_id";
    public static final String TENQUAN = "ten_quan";
    public static final String SOMON = "so_mon";

    public QuanDbManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        scopeContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Create table Quan");
        Toast.makeText(scopeContext,"CreateTable",Toast.LENGTH_SHORT).show();
        String createTableQuery = "CREATE TABLE " + TABLE_NAME +" ("
                +  ID + " integer primary key autoincrement ,"
                + TENQUAN + " TEXT not null, "
                + SOMON + " DECIMAL DEFAULT (0)" +")";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Xoá bảng cũ
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //Tiến hành tạo bảng mới
        onCreate(db);
    }

    public List<Quan> getAllQuan() {

        List<Quan> quans = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_NAME, null);

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

        return quans;
    }

    //Lấy một SP biết ID
    public Quan getQuanById(int ID) {
        Quan quan = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_NAME + " where id = ?",
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

    //Cập nhật
    public void updateQuan(Quan quan) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME +" SET tenQuan=?, soLuongMon = ? where id = ?",
                new String[]{quan.tenQuan, quan.soLuongMon + "", quan._id + ""});
    }

    //Chèn mới
    public void insertQuan(Quan quan) {
        SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO " + TABLE_NAME  +"(" + TENQUAN + ","+ SOMON +")" +" VALUES (?,?)",
                new String[]{quan.tenQuan, quan.soLuongMon + ""});
    }

    //Xoá sản phẩm khỏi DB
    public void deleteQuan(int quanID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME+ " where id = ?", new String[]{String.valueOf(quanID)});
    }
}
