package com.brightkidmont.brilla.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brightkidmont.brilla.PreschoolDetailsActivity;
import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.SuperAdminActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PreschoolAdapter extends BaseAdapter {
    private List<String> result = new ArrayList<>();
    Context context;
    private static LayoutInflater inflater=null;
    public static String preSchoolKey;

    public PreschoolAdapter(SuperAdminActivity superAdminActivity, List<String> prgmNameList) {
        result=prgmNameList;
        context=superAdminActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    private class Holder
    {
        TextView tv;
        ImageView img;
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.preschool_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.tv.setText(result.get(position));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preSchoolKey = result.get(position);
                context.startActivity(new Intent(context, PreschoolDetailsActivity.class));
            }
        });
        return rowView;
    }

}