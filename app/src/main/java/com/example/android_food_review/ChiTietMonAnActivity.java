package com.example.android_food_review;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_review,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_review_menu:
                Intent i  = new Intent(getApplicationContext(),AddReviewActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
