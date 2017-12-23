package com.brightkidmont.brilla.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brightkidmont.brilla.AdminActivity;
import com.brightkidmont.brilla.ApprovalActivity;
import com.brightkidmont.brilla.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.brightkidmont.brilla.AdminActivity.adminProgress;

public class UsersAdapter extends BaseAdapter {
    private List<String> result = new ArrayList<>();
    Context context;
    private static LayoutInflater inflater=null;

    private DatabaseReference mFirebaseDatabase;
    public static String userKey;

    public UsersAdapter(AdminActivity adminActivity, List<String> prgmNameList) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=adminActivity;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    private class Holder {
        TextView tv;
    }
    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.users_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        holder.tv.setText(result.get(position));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminProgress.setVisibility(View.VISIBLE);
                //get user key based on fatherName for the use of displaying user details
                mFirebaseDatabase.child("users")
                        .orderByChild("fatherName")
                        .equalTo(result.get(position))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                    userKey = childSnapshot.getKey();
                                }
                                context.startActivity(new Intent(context, ApprovalActivity.class));
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("Details", "details not received");
                            }
                        });
            }
        });

        return rowView;
    }

}