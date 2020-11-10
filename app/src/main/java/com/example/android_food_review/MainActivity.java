package com.example.android_food_review;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_food_review.adapter.QuanAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Quan> listQuan;
    QuanAdapter quanAdapter ;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_review);
//        listQuan = new ArrayList<>();
//        listQuan.add(new Quan(1,"Quận 1",15));
//        listQuan.add(new Quan(2,"Quận 2",20));
//        listQuan.add(new Quan(3,"Quận 3",30));
//        listQuan.add(new Quan(4,"Quận 4",5));
//        listQuan.add(new Quan(5,"Quận 5",24));
//        listQuan.add(new Quan(6,"Quận 6",99));
//        listQuan.add(new Quan(7,"Quận 7",44));
//        listQuan.add(new Quan(8,"Quận 8",114));
//        listQuan.add(new Quan(9,"Quận 9",15));
//        listQuan.add(new Quan(10,"Quận 10",144));
//        listQuan.add(new Quan(11,"Quận Bình Thạnh",154));
//        listQuan.add(new Quan(12,"Quận Thủ Đức",152));
//
//        quanAdapter = new QuanAdapter(listQuan);
//
//        listView = findViewById(R.id.lv_quan);
//        listView.setAdapter(quanAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Quan quan = (Quan) quanAdapter.getItem(i);
//                Toast.makeText(getApplicationContext(),"Click quan =>  " + quan.tenQuan,Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}
