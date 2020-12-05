package com.example.android_food_review;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_food_review.adapter.MonAnAdapter;

import com.example.android_food_review.controllers.MonAnController;
import com.example.android_food_review.models.MonAn;

import java.util.ArrayList;

public class ListMonAnActivity extends AppCompatActivity {
    ArrayList<MonAn>monAnArrayList;
    ListView mListMonAn;

    MonAnAdapter monAnAdapter;

    MonAnController monAnController;

    Integer maQuan;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmonan_activity);

        mListMonAn=findViewById(R.id.lv_monan);
        monAnArrayList = new ArrayList<>();

        monAnController = new MonAnController(this);

        monAnAdapter = new MonAnAdapter(monAnArrayList);
        mListMonAn.setAdapter(monAnAdapter);

        Intent i = getIntent();
        maQuan=i.getIntExtra("ID_quan",0);


        GetListMonAns();
        monAnAdapter.notifyDataSetChanged();

        mListMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietMonAnActivity.class);
                MonAn monAn = (MonAn) monAnAdapter.getItem(i);
                intent.putExtra("ID_MONAN",monAn.get_id());
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Chọn món =>  " + monAn.getTenMonAn(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetListMonAns(){
        // Đem qua activity để dùng Adapter NotifyDataSetChanged
        Cursor cursor = monAnController.getData("SELECT * FROM MONAN WHERE QUAN = " + maQuan.toString());
        monAnArrayList.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            Integer price = cursor.getInt(3);
            byte[] image = cursor.getBlob(4);
            Integer quan = cursor.getInt(5);

            monAnArrayList.add(new MonAn(id,name,address,quan,price,image));
        }
    }








}
