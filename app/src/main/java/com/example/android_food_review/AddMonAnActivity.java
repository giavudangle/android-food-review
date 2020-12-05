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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.example.android_food_review.controllers.MonAnController;
import com.example.android_food_review.controllers.QuanController;
import com.example.android_food_review.models.MonAn;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class AddMonAnActivity extends AppCompatActivity {
    EditText edtTenMonAn,edtDiaChi,edtGia;
    Spinner spinnerQuan;
    QuanController quanController;
    MonAnController monAnController;
    Button btnChooseImage,btnAddFood;
    ImageView imageAddMonAn;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_monan);

        quanController  = new QuanController(this);
        monAnController = new MonAnController(this);



        List<String> data = quanController.getTenQuan();
        initilizeWidget();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,data);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerQuan.setAdapter(adapter);


        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String spinnerQuanName = spinnerQuan.getSelectedItem().toString();
                    int idQuanFromSpinner = quanController.getMaQuanByTenQuan(spinnerQuanName); // Lấy mã quận để insert vào DB khi có tên quận từ Spinner
                    String vl = edtGia.getText().toString();
                    int gia = Integer.parseInt(vl); // Parse giá tiền
                    String tenMon = edtTenMonAn.getText().toString().trim();
                    String diaChi = edtDiaChi.getText().toString().trim();
                    MonAn monAn = new MonAn(tenMon,diaChi,
                            idQuanFromSpinner,gia,imageViewToByteArray(imageAddMonAn));
                    monAnController.insertMonAn(monAn);
                    Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
                    refreshControl();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddMonAnActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }

    private byte[] imageViewToByteArray(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void refreshControl(){
        edtTenMonAn.setText("");
        edtGia.setText("");
        edtDiaChi.setText("");
        imageAddMonAn.setImageResource(R.drawable.sg);
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
                imageAddMonAn.setImageBitmap(bitmap);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initilizeWidget(){
        spinnerQuan = findViewById(R.id.spinnerListQuan_AddMonAn);
        btnAddFood = findViewById(R.id.btnAddMonAn);
        btnChooseImage = findViewById(R.id.btnChooseImage_AddMonAn);
        imageAddMonAn= findViewById(R.id.image_choose_addmonan);
        edtTenMonAn = findViewById(R.id.edt_tenmonan_addmonan);
        edtDiaChi = findViewById(R.id.edt_diachi_addmonan);
        edtGia = findViewById(R.id.edt_gia_addmonan);


    }

}
