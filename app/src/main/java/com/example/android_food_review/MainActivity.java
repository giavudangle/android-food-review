package com.example.android_food_review;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_food_review.adapter.QuanAdapter;
import com.example.android_food_review.helpers.QuanDbManager;
import com.example.android_food_review.models.Quan;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Quan> listQuan;
    QuanAdapter quanAdapter ;
    ListView listView;
    QuanDbManager quanDbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quanDbManager = new QuanDbManager(this);
        //initalDataForQuan(); -> Chỉ chạy lần đầu để thêm các quận vào DB

        listQuan = new ArrayList<>();
        getDataQuan(); // Lấy data từ db
        quanAdapter = new QuanAdapter(listQuan);

        listView = findViewById(R.id.lv_quan);
        listView.setAdapter(quanAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Quan quan = (Quan) quanAdapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(),ListMonAnActivity.class);
                // Gửi ID quận qua activity list để có thể truy vấn lấy danh sách món ăn theo quận ( khoá là mã quận )
                intent.putExtra("ID_quan",quan._id);
                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"Click quan =>  " + quan.tenQuan,Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initalDataForQuan(){
//        quanDbManager.insertQuan(new Quan(2,"Quận 2",20));
//        quanDbManager.insertQuan(new Quan(3,"Quận 3",30));
//        quanDbManager.insertQuan(new Quan(4,"Quận 4",5));
//        quanDbManager.insertQuan(new Quan(5,"Quận 5",24));
//        quanDbManager.insertQuan(new Quan(6,"Quận 6",99));
//        quanDbManager.insertQuan(new Quan(11,"Quận Bình Thạnh",154));
//        quanDbManager.insertQuan(new Quan(12,"Quận Thủ Đức",152));
    }

    private void getDataQuan(){
        listQuan.clear();
        listQuan.addAll(quanDbManager.getAllQuan());
    }
}
