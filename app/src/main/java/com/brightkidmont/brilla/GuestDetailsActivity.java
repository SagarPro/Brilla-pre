package com.brightkidmont.brilla;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.utils.GuestUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.brightkidmont.brilla.adapter.GuestAdapter.guestKey;

public class GuestDetailsActivity extends AppCompatActivity {

    private TextView gName, gPhone, gEmail, gLocation;
    private DatabaseReference mFirebaseDatabase;
    private ProgressBar pbGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_details_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        pbGuest = (ProgressBar) findViewById(R.id.pbGuest);
        pbGuest.setVisibility(View.VISIBLE);
        gName = (TextView) findViewById(R.id.gName);
        gPhone = (TextView) findViewById(R.id.gPhone);
        gEmail = (TextView) findViewById(R.id.gEmail);
        gLocation = (TextView) findViewById(R.id.gLocation);

        //directing to guest node in database
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("guest").child(guestKey);

        //Setting guest user details based on guestKey
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GuestUser guestUser = dataSnapshot.getValue(GuestUser.class);
                gName.setText(guestUser.guestName);
                gPhone.setText(guestUser.guestPhone);
                gEmail.setText(guestUser.guestEmail);
                gLocation.setText(guestUser.guestLocation);
                pbGuest.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("GUEST", "Failed to fetch data");
            }
        });

        Button btnDeleteGuest = (Button) findViewById(R.id.btnDeleteGuest);
        //Deleting guest user from database
        btnDeleteGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbGuest.setVisibility(View.VISIBLE);
                mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            mFirebaseDatabase.removeValue();
                            Toast.makeText(getBaseContext(), gName.getText().toString()+" User Details Is Deleted From Database", Toast.LENGTH_SHORT).show();
                            openAdmin();
                        } catch (Exception e) {
                            e.printStackTrace();
                            pbGuest.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("GUEST","Failed to delete guest user details");
                    }
                });
            }
        });
    }

    //After deletion open guest users list for superAdmin
    public void openAdmin(){
        pbGuest.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GuestUsersListActivity.getInstance().finish();
                startActivity(new Intent(getBaseContext(), GuestUsersListActivity.class));
                finish();
                pbGuest.setVisibility(View.GONE);
            }
        },2000);
    }
}
