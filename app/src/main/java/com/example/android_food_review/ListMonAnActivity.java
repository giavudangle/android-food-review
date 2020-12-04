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




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmonan_activity);

        mListMonAn=findViewById(R.id.lv_monan);
        monAnArrayList = new ArrayList<>();

        monAnController = new MonAnController(this);

        monAnAdapter = new MonAnAdapter(monAnArrayList);
        mListMonAn.setAdapter(monAnAdapter);


        // ***
        Cursor cursor = monAnController.getData("SELECT * FROM MONAN");
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
        monAnAdapter.notifyDataSetChanged();

        mListMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                navigateToChiTietMonAn();
                MonAn monAn = (MonAn) monAnAdapter.getItem(i);
                Toast.makeText(getApplicationContext(),"Click quan =>  " + monAn.getTenMonAn(),Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_monan,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_monan_menu:
                Intent i  = new Intent(getApplicationContext(),AddMonAnActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    private void navigateToChiTietMonAn() {
        Intent intent = new Intent(getApplicationContext(),ChiTietMonAnActivity.class);
        startActivity(intent);
    }

    private void getDataMonAn(){
        monAnArrayList.clear();
        monAnArrayList.addAll(monAnController.getAllMonAn());
    }
}
