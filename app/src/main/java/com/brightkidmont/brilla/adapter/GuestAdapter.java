package com.brightkidmont.brilla.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.GuestUsersListActivity;
import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.utils.GuestUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.brightkidmont.brilla.GuestUsersListActivity.guestList;

public class GuestAdapter extends BaseAdapter {
    private List<String> result = new ArrayList<>();
    Context context;
    private static LayoutInflater inflater=null;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    public static String guestKey;

    public GuestAdapter(GuestUsersListActivity guestUsersListActivity, List<String> prgmNameList) {
        result=prgmNameList;
        context=guestUsersListActivity;
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
    private class Holder
    {
        TextView tv;
    }
    //updating list
    private void updateAdapter(List<String> arrylst) {
        this.result= arrylst;
        notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GuestAdapter.Holder holder=new GuestAdapter.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.preschool_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        holder.tv.setText(result.get(position));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.guest_user_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                final ProgressBar pbGuestUser = (ProgressBar) dialog.findViewById(R.id.pbGuestUser);
                pbGuestUser.setVisibility(View.VISIBLE);

                final TextView guName = (TextView) dialog.findViewById(R.id.guName);
                final TextView guPhone = (TextView) dialog.findViewById(R.id.guPhone);
                final TextView guEmail = (TextView) dialog.findViewById(R.id.guEmail);
                final TextView guLocation = (TextView) dialog.findViewById(R.id.guLocation);

                //Setting values to textView for specific guest users
                mFirebaseDatabase.child("guest")
                        .orderByChild("guestName")
                        .equalTo(result.get(position))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                    guestKey = childSnapshot.getKey();
                                    guName.setText(String.valueOf(childSnapshot.child("guestName").getValue()));
                                    guPhone.setText(String.valueOf(childSnapshot.child("guestPhone").getValue()));
                                    guEmail.setText(String.valueOf(childSnapshot.child("guestEmail").getValue()));
                                    guLocation.setText(String.valueOf(childSnapshot.child("guestLocation").getValue()));
                                    pbGuestUser.setVisibility(View.GONE);
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                dialog.show();

                //deleting guest user from database
                Button btnDeleteGuestUser = (Button) dialog.findViewById(R.id.btnDeleteGU);
                btnDeleteGuestUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pbGuestUser.setVisibility(View.VISIBLE);
                        mFirebaseDatabase.child("guest").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(final DataSnapshot dataSnapshot) {
                                try {
                                    mFirebaseDatabase.child("guest").child(guestKey).removeValue();
                                    notifyDataSetChanged();

                                    //updating guest list
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            mFirebaseDatabase.child("guest").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    guestList.clear();
                                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                        GuestUser guestUser = snapshot.getValue(GuestUser.class);
                                                        String name =  guestUser.guestName;
                                                        guestList.add(name);
                                                    }
                                                    Collections.sort(guestList);
                                                    updateAdapter(guestList);
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                }
                                            });
                                            Toast.makeText(context, guName.getText().toString()+" details are deleted from database", Toast.LENGTH_SHORT).show();
                                            pbGuestUser.setVisibility(View.GONE);
                                            dialog.dismiss();
                                        }
                                    },2000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    pbGuestUser.setVisibility(View.GONE);
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
            }
        });

        return rowView;
    }

}