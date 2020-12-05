package com.example.android_food_review.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android_food_review.R;
import com.example.android_food_review.models.Quan;
import com.example.android_food_review.models.Review;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    final ArrayList<Review> reviewArrayList;

    public ReviewAdapter(ArrayList<Review> reviewArrayList) {
        this.reviewArrayList = reviewArrayList;
    }

    @Override
    public int getCount() {
        return reviewArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return reviewArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return reviewArrayList.get(i).get_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewReview;
        if(view == null){
            viewReview = View.inflate(viewGroup.getContext(), R.layout.review_view,null);
        }else {
            viewReview = view;
        }

        Review review =  (Review)getItem(i);
        ((TextView) viewReview.findViewById(R.id.txtTenNguoiReview)).setText(String.format("%s",review.getTenNguoiReview()));
        ((TextView) viewReview.findViewById(R.id.txtLoiReview)).setText(String.format("%s",review.getNoiDungReview()));
        byte[] reviewImage = review.getImageReview();
        Bitmap bitmap = BitmapFactory.decodeByteArray(reviewImage,0,reviewImage.length);
        ((ImageView) viewReview.findViewById(R.id.img_review)).setImageBitmap(bitmap);

        return viewReview;
    }
}
