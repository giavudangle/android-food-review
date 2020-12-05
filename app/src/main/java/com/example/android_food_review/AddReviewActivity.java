package com.example.android_food_review;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.android_food_review.R;
import com.example.android_food_review.controllers.ReviewController;
import com.example.android_food_review.models.Review;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddReviewActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    ReviewController reviewController;
    EditText edtTenNguoiReview,edtNoiDungReview;
    ImageButton imgChooseImage;
    ImageView imageReview;
    Button btnAddReview;
    Integer maMonAn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_review);
        initilizeWidget();
        getMaMonAnFromIntent();
        reviewController = new ReviewController(this);

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String ten  = edtTenNguoiReview.getText().toString().trim();
                    String noidung = edtNoiDungReview.getText().toString().trim();
                    Integer monan = maMonAn;
                    byte[]image = imageViewToByteArray(imageReview);
                    Review review = new Review(monan,ten,noidung,image);
                    reviewController.insertReview(review);
                    Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
                    refreshControl();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        imgChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddReviewActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }else {
                Toast.makeText(getApplicationContext(),"Cần cho phép ứng dụng có quyền đọc ảnh",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageReview.setImageBitmap(bitmap);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private byte[] imageViewToByteArray(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void refreshControl(){
        edtTenNguoiReview.setText("");
        edtNoiDungReview.setText("");
        imageReview.setImageResource(R.drawable.sg);
    }

    private void initilizeWidget(){
        edtTenNguoiReview = findViewById(R.id.edt_tennguoireview);
        edtNoiDungReview = findViewById(R.id.edt_noidungreview);
        imageReview = findViewById(R.id.imageAddMonAn_AddReview);
        imgChooseImage= findViewById(R.id.image_button_addreview);
        btnAddReview  =findViewById(R.id.btn_add_review);
    }

    private void getMaMonAnFromIntent(){
        Intent intent  = getIntent();

        maMonAn =  intent.getIntExtra("MAMONAN",0);
    }
}
