package com.example.android_food_review.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android_food_review.models.Quan;
import com.example.android_food_review.R;

import java.util.ArrayList;

public class QuanAdapter extends BaseAdapter {

    final ArrayList<Quan> listQuan;

    public QuanAdapter(ArrayList<Quan> listQuan) {
        this.listQuan = listQuan;
    }

    @Override
    public int getCount() {
        return listQuan.size();
    }

    @Override
    public Object getItem(int i) {
        return listQuan.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listQuan.get(i)._id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View viewQuan;
        if(view == null){
            viewQuan=View.inflate(viewGroup.getContext(), R.layout.quan_view,null);
        }else viewQuan=view;

        Quan quan =  (Quan)getItem(i);
        ((TextView) viewQuan.findViewById(R.id.ten_quan)).setText(String.format("%s",quan.tenQuan));
        ((TextView) viewQuan.findViewById(R.id.somon)).setText(String.format("%d món",quan.soLuongMon));

        return viewQuan;
    }
}
