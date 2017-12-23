package com.brightkidmont.brilla.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AuditoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> imageId;
    private String[] imageNames;
    private static LayoutInflater inflater=null;
    public AuditoryAdapter(Context cntxt, ArrayList<String> alImage1, String[] nameList) {
        context=cntxt;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageId = alImage1;
        imageNames = nameList;
    }
    @Override
    public int getCount() {
        return imageId.size();
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
        TextView name;
        ImageView img;
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.horizontal_listview_items, null);
        holder.name = (TextView) rowView.findViewById(R.id.imageName);
        holder.name.setText(imageNames[position]);
        holder.img=(ImageView) rowView.findViewById(R.id.imageItems);
        Picasso.with(context).load(imageId.get(position)).error(R.drawable.bright_kid_bg).into(holder.img);
        return rowView;
    }

}