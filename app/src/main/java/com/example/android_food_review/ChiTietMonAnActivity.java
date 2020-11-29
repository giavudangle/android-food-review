package com.example.android_food_review;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_food_review.adapter.ReviewAdapter;
import com.example.android_food_review.models.Review;

import java.util.ArrayList;

public class ChiTietMonAnActivity extends AppCompatActivity {
    ArrayList<Review> reviewArrayList;
    ListView listViewReview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_monan_activity);
        reviewArrayList = new ArrayList<>();
        reviewArrayList.add(new Review(1,14,"Minh Trường","Cũng ngon",3));
        reviewArrayList.add(new Review(1,1,"Minh Trường","Cũng ngon",3));
        reviewArrayList.add(new Review(1,1,"Minh Trường","Cũng ngon",3));
        reviewArrayList.add(new Review(1,1,"Minh Trường","Cũng ngon",3));
        reviewArrayList.add(new Review(1,1,"Minh Trường","Cũng ngon",3));
        reviewArrayList.add(new Review(1,1,"Minh Trường","Cũng ngon",3));

        final ReviewAdapter reviewAdapter = new ReviewAdapter(reviewArrayList);
        listViewReview = findViewById(R.id.lv_review_chitiet_monan);
        listViewReview.setAdapter(reviewAdapter);

    }
}
