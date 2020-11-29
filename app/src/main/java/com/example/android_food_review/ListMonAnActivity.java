package com.example.android_food_review;

import android.content.Intent;
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
import com.example.android_food_review.models.MonAn;

import java.util.ArrayList;

public class ListMonAnActivity extends AppCompatActivity {
    ArrayList<MonAn>monAnArrayList;
    ListView mListMonAn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmonan_activity);
        monAnArrayList = new ArrayList<>();
        monAnArrayList.add(new MonAn(1,"Gà rán KFC","67 huỳnh tấn phát", (long) 100000));
        monAnArrayList.add(new MonAn(2,"Gà rán POPEYS","67 huỳnh tấn phát", (long) 100000));
        monAnArrayList.add(new MonAn(4,"Gà nướng","67 huỳnh tấn phát", (long) 100000));
        monAnArrayList.add(new MonAn(5,"Gà rán KFC","67 huỳnh tấn phát", (long) 100000));
        monAnArrayList.add(new MonAn(5,"Gà rán KFC","67 huỳnh tấn phát", (long) 100000));
        monAnArrayList.add(new MonAn(5,"Gà rán KFC","67 huỳnh tấn phát", (long) 100000));


        final MonAnAdapter monAnAdapter = new MonAnAdapter(monAnArrayList);
        mListMonAn=findViewById(R.id.lv_monan);
        mListMonAn.setAdapter(monAnAdapter);

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

    private void navigateToChiTietMonAn() {
        Intent intent = new Intent(getApplicationContext(),ChiTietMonAnActivity.class);
        startActivity(intent);
    }
}
