package com.example.android_food_review.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android_food_review.R;
import com.example.android_food_review.models.MonAn;

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
        View viewListMonAn;
        if(view == null){
            viewListMonAn= View.inflate(viewGroup.getContext(), R.layout.monan_view,null);
        } else {
            viewListMonAn=view;
        }

        MonAn monAn=(MonAn)getItem(i);
        ((TextView) viewListMonAn.findViewById(R.id.ten_mon)).setText(String.format("%s",monAn.getTenMonAn()));
        ((TextView) viewListMonAn.findViewById(R.id.diachi_mon)).setText(String.format("%s",monAn.getDiaChi()));
        ((RatingBar) viewListMonAn.findViewById(R.id.rating_list_mon)).setRating(4);

        return viewListMonAn;
    }
}
