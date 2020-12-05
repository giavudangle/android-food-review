package com.example.android_food_review.controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.android_food_review.helpers.DBHelper;
import com.example.android_food_review.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewController {
    public static final String TAG = "REVIEW-CONTROLLER";
    private DBHelper mHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private String[] mAllColumnsInReview = {
            DBHelper.REVIEW_ID,
            DBHelper.REVIEW_TENNGUOIREVIEW,
            DBHelper.REVIEW_NOIDUNG,
            DBHelper.REVIEW_IMAGE,
            DBHelper.REVIEW_MONAN,
    };

    public void openDatabase() throws SQLException {
        mDatabase = mHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        mDatabase.close();
    }

    public ReviewController(Context context){
        this.mContext = context;
        mHelper = new DBHelper(context);
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

    public List<Review> getAllReview(){
        List<Review> reviewList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBHelper.TABLE_REVIEW,null);
        while(cursor.moveToFirst()){
            int reviewId = cursor.getInt(0);
            String tenNguoiReview = cursor.getString(1);
            String noiDungReview  = cursor.getString(2);
            byte[] imageReview = cursor.getBlob(3);
            Integer monAnId = cursor.getInt(4);
            Review review = new Review(reviewId,monAnId,tenNguoiReview,noiDungReview,imageReview);
            reviewList.add(review);
        }
        cursor.close();
        return reviewList;
    }

    public void insertReview(Review review){
        String sqlQuery ="INSERT INTO "
                + DBHelper.TABLE_REVIEW  +"("
                + DBHelper.REVIEW_TENNGUOIREVIEW + ","
                + DBHelper.REVIEW_NOIDUNG +","
                + DBHelper.REVIEW_IMAGE + ","
                + DBHelper.REVIEW_MONAN +")" +" VALUES (?,?,?,?)";
        SQLiteStatement sqLiteStatement =  mDatabase.compileStatement(sqlQuery);

        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,review.getTenNguoiReview());
        sqLiteStatement.bindString(2,review.getNoiDungReview());
        sqLiteStatement.bindBlob(3,review.getImageReview());
        sqLiteStatement.bindLong(4,review.getIdMonAn());

        sqLiteStatement.executeInsert();
    }

}
