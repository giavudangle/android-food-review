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

import com.example.android_food_review.adapter.QuanAdapter;
import com.example.android_food_review.controllers.QuanController;
import com.example.android_food_review.models.Quan;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Quan> listQuan;
    QuanAdapter quanAdapter ;
    ListView listView;
    QuanController quanController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quanController = new QuanController(this);

        /* QUAN TRỌNG */
        //initalDataForQuan(); //-> Chỉ chạy lần đầu để thêm các quận vào DB
        /*NẾU KO COMMENT LẠI Ở LẦN CHẠY THỨ 2 THÌ SẼ INSERT NHIỀU LẦN*/

        getDataQuan();
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
                Toast.makeText(getApplicationContext(),"Click quan =>  " + quan.tenQuan,Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initalDataForQuan(){
        quanController.insertQuan(new Quan("Quận 1",10));
        quanController.insertQuan(new Quan("Quận 2",20));
        quanController.insertQuan(new Quan("Quận 3",30));
        quanController.insertQuan(new Quan("Quận 4",5));
        quanController.insertQuan(new Quan("Quận 5",52));
        quanController.insertQuan(new Quan("Quận 6",15));
        quanController.insertQuan(new Quan("Quận 7",45));
        quanController.insertQuan(new Quan("Quận 8",45));
        quanController.insertQuan(new Quan("Quận 9",45));
        quanController.insertQuan(new Quan("Quận 10",145));
        quanController.insertQuan(new Quan("Quận 11",55));
        quanController.insertQuan(new Quan("Quận 12",15));
        quanController.insertQuan(new Quan("Quận Bình Thạnh",115));
        quanController.insertQuan(new Quan("Quận Thủ Đức ",53));
        quanController.insertQuan(new Quan("Quận Tân Phú ",24));
        quanController.insertQuan(new Quan("Quận Bình Tân ",60));

    }

    private void getDataQuan(){
        listQuan = new ArrayList<>();
        listQuan.clear();
        listQuan.addAll(quanController.getAllQuan());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_monan,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_monan_menu:
                Intent i = new Intent(getApplicationContext(), AddMonAnActivity.class);
                startActivity(i);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
