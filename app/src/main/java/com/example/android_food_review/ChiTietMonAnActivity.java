package com.example.android_food_review;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_food_review.adapter.ReviewAdapter;
import com.example.android_food_review.controllers.MonAnController;
import com.example.android_food_review.controllers.ReviewController;
import com.example.android_food_review.models.MonAn;
import com.example.android_food_review.models.Review;

import java.util.ArrayList;

public class ChiTietMonAnActivity extends AppCompatActivity {
    ArrayList<Review> reviewArrayList;
    ListView listViewReview;

    ReviewController reviewController;
    MonAnController monAnController;

    int maMonAn;
    ReviewAdapter reviewAdapter;
    TextView mTxtTenMon,mTxtGia,mTxtDiaChi;
    ImageView imageViewChiTiet;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_monan_activity);
        initializeWidget();

        listViewReview = findViewById(R.id.lv_review_chitiet_monan);

        reviewArrayList = new ArrayList<>();

        reviewController = new ReviewController(this);
        monAnController = new MonAnController(this);



        reviewAdapter = new ReviewAdapter(reviewArrayList);
        listViewReview.setAdapter(reviewAdapter);

        Intent intent = getIntent();
        maMonAn = intent.getIntExtra("ID_MONAN",0); // Có mã món ăn

        MonAn monAn = monAnController.getMonById(maMonAn);

        mTxtTenMon.setText(monAn.getTenMonAn());
        mTxtGia.setText(monAn.getGia().toString());
        mTxtDiaChi.setText(monAn.getDiaChi());

        byte[] foodImage = monAn.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage,0,foodImage.length);
        imageViewChiTiet.setImageBitmap(bitmap);


        // Lấy mã món ăn từ list gửi qua để tý insert vào review
        GetListReviews();
        reviewAdapter.notifyDataSetChanged();





    }



    private void initializeWidget(){
        mTxtTenMon  = findViewById(R.id.txtTenMon_Chitiet);
        mTxtDiaChi = findViewById(R.id.txtDiaChi_Chitiet);
        mTxtGia = findViewById(R.id.txtGia_Chitiet);
        imageViewChiTiet = findViewById(R.id.img_ChiTiet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_review,menu);
        return true;
    }

    public void GetListReviews(){
        Cursor cursor = reviewController.getData("SELECT * FROM REVIEW");
        reviewArrayList.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tenNguoiReview = cursor.getString(1);
            String noidungReview = cursor.getString(2);
            byte []imageReview = cursor.getBlob(3);
            Integer idMonAn = cursor.getInt(4);

            reviewArrayList.add(new Review(id,idMonAn,tenNguoiReview,noidungReview,imageReview));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_review_menu:
                Intent i  = new Intent(getApplicationContext(),AddReviewActivity.class);
                i.putExtra("ID_MONAN_NEXT",maMonAn);
                startActivity(i);


        }
        return super.onOptionsItemSelected(item);
    }
}
