package com.brightkidmont.brilla.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.brightkidmont.brilla.PreschoolDetailsActivity;
import com.brightkidmont.brilla.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class PSUserAdapter extends BaseAdapter {
    private List<String> result = new ArrayList<>();
    Context context;
    private static LayoutInflater inflater=null;

    private DatabaseReference mFirebaseDatabase;
    public static String psUserKey;

    public PSUserAdapter(PreschoolDetailsActivity preschoolDetailsActivity, List<String> prgmNameList) {
        result=prgmNameList;
        context=preschoolDetailsActivity;
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
    }
    public void updateAdapter(List<String> arrylst) {
        this.result= arrylst;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.preschool_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        holder.tv.setText(result.get(position));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.ps_user_details_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                final ProgressBar pbPSUserDetails = (ProgressBar) dialog.findViewById(R.id.pbPSUserDetails);
                pbPSUserDetails.setVisibility(View.VISIBLE);

                final TextView udChildName = (TextView) dialog.findViewById(R.id.udChildName);
                final TextView udDOB = (TextView) dialog.findViewById(R.id.udDOB);
                final TextView udPreSchool = (TextView) dialog.findViewById(R.id.udPreSchool);
                final TextView udLevel = (TextView) dialog.findViewById(R.id.udLevel);
                final TextView udLocation = (TextView) dialog.findViewById(R.id.udLocation);
                final TextView udFatherName = (TextView) dialog.findViewById(R.id.udFatherName);
                final TextView udMotherName = (TextView) dialog.findViewById(R.id.udMotherName);
                final TextView udPhone = (TextView) dialog.findViewById(R.id.udPhone);
                final TextView udEmail = (TextView) dialog.findViewById(R.id.udEmail);

                //showing user details
                mFirebaseDatabase.child("users")
                        .orderByChild("fatherName")
                        .equalTo(result.get(position))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                    //setting user details to display to SuperAdmin & getting "psUserKey" for further use
                                    psUserKey = childSnapshot.getKey();
                                    udChildName.setText(String.valueOf(childSnapshot.child("childName").getValue()));
                                    udDOB.setText(String.valueOf(childSnapshot.child("dob").getValue()));
                                    udPreSchool.setText(String.valueOf(childSnapshot.child("preSchool").getValue()));
                                    udLevel.setText(String.valueOf(childSnapshot.child("level").getValue()));
                                    udLocation.setText(String.valueOf(childSnapshot.child("location").getValue()));
                                    udFatherName.setText(String.valueOf(childSnapshot.child("fatherName").getValue()));
                                    udMotherName.setText(String.valueOf(childSnapshot.child("motherName").getValue()));
                                    udPhone.setText(String.valueOf(childSnapshot.child("phone").getValue()));
                                    udEmail.setText(String.valueOf(childSnapshot.child("email").getValue()));
                                    pbPSUserDetails.setVisibility(View.GONE);
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("SuperAdmin", "Failed to retrieve user details");
                            }
                        });
                dialog.show();
            }
        });
        return rowView;
    }

}