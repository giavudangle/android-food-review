package com.example.android_food_review.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android_food_review.R;
import com.example.android_food_review.models.MonAn;
import com.example.android_food_review.models.Quan;

import java.util.ArrayList;

public class MonAnAdapter extends BaseAdapter {

    final ArrayList<MonAn> monAnArrayList;

    public MonAnAdapter(ArrayList<MonAn> monAnArrayList) {
        this.monAnArrayList = monAnArrayList;
    }

    @Override
    public int getCount() {
        return monAnArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return monAnArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return monAnArrayList.get(i).get_id();
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewMonAn;

       if(view == null){
            viewMonAn  = View.inflate(viewGroup.getContext(),R.layout.monan_view,null);
       }else {
          viewMonAn = view;
       }

       MonAn monAn = (MonAn)getItem(i);

        ((TextView) viewMonAn.findViewById(R.id.ten_mon)).setText(String.format("%s",monAn.getTenMonAn()));
        ((TextView) viewMonAn.findViewById(R.id.diachi_mon)).setText(String.format("%s ",monAn.getDiaChi()));
        ((TextView) viewMonAn.findViewById(R.id.txt_giamon_listmonan)).setText(String.format("%d VND ",monAn.getGia()));
       byte[] foodImage = monAn.getImage();

       Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage,0,foodImage.length);
        ((ImageView) viewMonAn.findViewById(R.id.monan_list_image)).setImageBitmap(bitmap);
       return viewMonAn;
    }
}
